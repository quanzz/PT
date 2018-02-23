package cn.edu.seu.kse.project.materializer.reasoner.mat;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAdditor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiomFactory;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.materializer.parallel.ParallelController;

public class DHLMaterializationController implements ParallelController {
	
	private DHLBaseAdditor additor;
	private DHLBaseAccessor accessor;
	private DHLAxiomFactory factory;
	
	private ConcurrentLinkedQueue<DHLAxiom> workingQueue;
	private SWDGraph graph;
	
	public DHLMaterializationController(
			DHLOntologyBase ontologyBase){
		
		this.additor = ontologyBase.getBaseAdditor();
		this.accessor = ontologyBase.getBaseAccessor();
		this.factory = ontologyBase.getAxiomFactory();
		
		this.workingQueue = new ConcurrentLinkedQueue<DHLAxiom>();
		this.graph = new SWDGraph();
	}
	
	/**
	 * put all original assertions in the workingQueue.
	 */
	public void initialize(){
		Set<DHLAxiom> classAssertions = 
				accessor.getAllClassAssertions();
		if(classAssertions!=null) {
			for(DHLAxiom axiom : classAssertions) {
				workingQueue.offer(axiom);
			}
		}
		
		Set<DHLAxiom> roleAssertions = 
				accessor.getAllRoleAssertions();
		if(roleAssertions!=null) {
			for(DHLAxiom axiom : roleAssertions) {
				workingQueue.offer(axiom);
			}
		}
	}
	
	// functions for parallelism
	public void put(DHLAxiom axiom){
		workingQueue.offer(axiom);
	}
	
	public DHLMaterializationTask getNewParallelTask(){
		DHLMaterializationTask task = null;
		
		DHLAxiom axiom = null;	
		if(!workingQueue.isEmpty()){
			axiom = workingQueue.poll();
		}
		
		if(axiom!= null) {
			task = new DHLMaterializationTask(axiom);
		}
		
		return task;	
	}

	@Override
	public boolean hasParallelTask() {
		if(workingQueue.isEmpty()) 
			return false;
		return true;
	}	
	
	
	// correlater
	
	public void correlateClassClassAssertions(
			Integer class1, Integer member1,
			Integer class2, Integer member2) {
		
		DHLClassAssertion parent = factory.getDHLClassAssertion(class1, member1);
		DHLClassAssertion child = factory.getDHLClassAssertion(class2, member2);
		
		this.correlate(parent, child);
		
	}
	
	public void correlate(DHLClassAssertion parent, 
			DHLClassAssertion child){
		this.graph.addEdge(parent, child);
	}
	
	public Set<DHLClassAssertion> transfer(DHLClassAssertion assertion) {
		return graph.transfer(assertion);
	}
	
	public Set<DHLClassAssertion> getChildren(DHLClassAssertion assertion){
		return graph.getChildren(assertion);
	}


	
	// dreiver
	
	public void deriveClassAssertion(
			Integer concept, 
			Integer member) {
		
		DHLAxiom axiom = factory.getDHLClassAssertion(concept, member);
		additor.addAxiom(axiom);
		this.put(axiom);
	}
	
	public void deriveClassAssertion(
			DHLClassAssertion classAssertion) {
		
		additor.addAxiom(classAssertion);
		this.put(classAssertion);
	}
	
	public void deriveRoleAssertion(
			Integer role, 
			Integer subject, 
			Integer object) {
		DHLAxiom axiom = factory.getDHLObjectPropertyAssertion(role, subject, object);
		additor.addAxiom(axiom);
		this.put(axiom);
	}


}
