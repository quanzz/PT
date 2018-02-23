package cn.edu.seu.kse.project.materializer.reasoner.mat;

import java.util.ArrayList;
import java.util.List;

import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.parallel.ParallelExecutor;
import cn.edu.seu.kse.project.materializer.parallel.ParallelMaster;
import cn.edu.seu.kse.project.materializer.parallel.ParallelTask;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLInversePropertyRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLLeftExistentialRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLObjectIntersectionOfRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLPropertyChianOfRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLSubClassOfRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLSubObjectPropertyOfRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLTransferRule;
import cn.edu.seu.kse.project.materializer.reasoner.mat.rules.DHLTransitivePropertyRule;
import cn.edu.seu.kse.project.ontology.exception.OntoException;

public class DHLMaterializationExecutor implements ParallelExecutor {
	
	private DHLMaterializer materializer;
	private DHLOntologyBase ontoBase;
	
	private DHLMaterializationTask task;
	private ParallelMaster parallelMaster;
	//private DHLMaterializationController controller;
	
	private List<DHLRule> DHLRuleSet;
	
	public DHLMaterializationExecutor(
			DHLMaterializer materializer,
			DHLOntologyBase ontoBase) {
		
		this.materializer = materializer;
		this.ontoBase = ontoBase;
		
		this.parallelMaster = 
				materializer.getParallelMaster();
		//this.controller = 
		//		materializer.getMaterializationController();
		
		this.DHLRuleSet = new ArrayList<DHLRule>();
		DHLRuleSet.add(new DHLObjectIntersectionOfRule());
		DHLRuleSet.add(new DHLSubClassOfRule());
		DHLRuleSet.add(new DHLSubObjectPropertyOfRule());
		DHLRuleSet.add(new DHLTransitivePropertyRule());
		DHLRuleSet.add(new DHLPropertyChianOfRule());
		DHLRuleSet.add(new DHLLeftExistentialRule());
		
		DHLRuleSet.add(new DHLInversePropertyRule());	
		DHLRuleSet.add(new DHLTransferRule());	
	}
	

	@Override
	public void setNewParallelTask(ParallelTask task) {
		if(task instanceof DHLMaterializationTask) {
			this.task = (DHLMaterializationTask) task;
		} else {
			try {
				throw new OntoException("The new task is not "
						+ "a materialization task.");
			} catch (OntoException e) {
				e.printStackTrace();
			}
		}	
	}


	@Override
	public void setVacant() {
		parallelMaster.setVacant(this);
	}

	@Override
	public void run() {
		
		DHLAxiom processedAxiom = task.getTargetAxiom();
		
		for(DHLRule rule : DHLRuleSet) {
			rule.apply(processedAxiom, materializer, ontoBase);
		}

		setVacant();
	}
	
}
