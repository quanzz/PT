package cn.edu.seu.kse.project.ontology.simple.structure;


public class SimpleObjectIntersectionOfAxiom implements SimpleAxiom{
	
	protected Integer subConcept1 = 0;
	protected Integer subConcept2 = 0;
	protected Integer superConcept = 0;
	
	public SimpleObjectIntersectionOfAxiom(){
		
	}
	
	public SimpleObjectIntersectionOfAxiom(
			Integer subConcept1,
			Integer subConcept2,
			Integer superConcept){
		
		this.subConcept1 = subConcept1;
		this.subConcept2 = subConcept2;
		this.superConcept = superConcept;
		
	}
	
	
	public Integer getSubFirstConcept() {
		return subConcept1;
	}

	public Integer getSubSecondConcept() {
		return subConcept2;
	}

	public Integer getSuperConcept() {
		return superConcept;
	}

	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

}
