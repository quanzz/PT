package cn.edu.seu.kse.project.materializer.reasoner.mat;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.parallel.ParallelTask;

public class DHLMaterializationTask implements ParallelTask {
	
	private DHLAxiom processedAxiom;
	
	DHLMaterializationTask(DHLAxiom processedAxiom) {
		this.processedAxiom = processedAxiom;
	}
	
	public DHLAxiom getTargetAxiom(){
		return processedAxiom;
	}

}
