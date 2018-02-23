package cn.edu.seu.kse.project.materializer.ontology.structure;


public interface DHLAxiomVisitor {
	
	public void visit(DHLLeftExistentialAxiom arg);
	
	public void visit(DHLObjectIntersectionOfAxiom arg);
	
	public void visit(DHLSubClassOfAxiom arg);
	
	public void visit(DHLSubObjectPropertyOfAxiom arg);
	
	public void visit(DHLSubPropertyChainOfAxiom arg);
	
	public void visit(DHLTransitiveObjectPropertyAxiom arg);
	
	public void visit(DHLClassAssertion arg);
	
	public void visit(DHLObjectPropertyAssertion arg);

}
