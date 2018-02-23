package cn.edu.seu.kse.project.ontology.simple.structure;

public interface SimpleAxiomVisitor {
	
	public void visit(SimpleLeftExistentialAxiom arg);
	
	public void visit(SimpleObjectIntersectionOfAxiom arg);
	
	public void visit(SimpleSubClassOfAxiom arg);
	
	public void visit(SimpleSubObjectPropertyOfAxiom arg);
	
	public void visit(SimpleSomeValuesFromAxiom arg);
	
	public void visit(SimpleSubPropertyChainOfAxiom arg);
	
	public void visit(SimpleTransitiveObjectPropertyAxiom arg);
	
	public void visit(SimpleClassAssertion arg);
	
	public void visit(SimpleObjectPropertyAssertion arg);

}
