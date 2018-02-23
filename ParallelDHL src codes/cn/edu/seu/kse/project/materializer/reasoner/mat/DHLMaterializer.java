package cn.edu.seu.kse.project.materializer.reasoner.mat;

import java.util.HashSet;
import java.util.Set;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.parallel.ParallelExecutor;
import cn.edu.seu.kse.project.materializer.parallel.ParallelMaster;
import cn.edu.seu.kse.project.materializer.reasoner.DHLReasoner;

public class DHLMaterializer implements DHLReasoner {
	
	private String name = this.getClass().getName()+">";
	
	// this is a parallel running platform
	private ParallelMaster parallelMaster;
	
	// this controls the states transferring
	private DHLMaterializationController controller;
	
	private DHLOntologyBase ontoBase;
	
	private int numOfThreads = 1;
	
	private Logger log;
	
	public DHLMaterializer(DHLOntologyBase ontoBase,Logger log){
		this.controller = new DHLMaterializationController(ontoBase);
		this.ontoBase = ontoBase;
		this.parallelMaster = new ParallelMaster(controller,log);
		this.log = log;
	}

	@Override
	public void saturate() {
		controller.initialize();
		log.debug(name+"the ParallelController has been setup.");
		
		Set<ParallelExecutor> executors =
				new HashSet<ParallelExecutor>();
		for(int _i = 0; _i < numOfThreads; _i++) {
			executors.add(
					new DHLMaterializationExecutor(this,ontoBase));
		}
		
		parallelMaster.setup(executors);
		parallelMaster.process();
		parallelMaster.shutdown();
	}
	
	public DHLMaterializationController getMaterializationController(){
		return controller;
	}
	
	public ParallelMaster getParallelMaster(){
		return this.parallelMaster;
	}
	
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}


}
