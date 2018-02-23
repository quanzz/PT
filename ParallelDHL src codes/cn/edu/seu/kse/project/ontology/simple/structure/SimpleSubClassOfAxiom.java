package cn.edu.seu.kse.project.ontology.simple.structure;


public class SimpleSubClassOfAxiom implements SimpleAxiom{
	
	protected Integer subConcept = 0;
	protected Integer superConcept = 0;
	
	public SimpleSubClassOfAxiom(){
		
	}
	
	public SimpleSubClassOfAxiom(
			Integer subConcept,
			Integer superConcept){
		
		this.subConcept = subConcept;
		this.superConcept = superConcept;
		
	}
	
	
	public Integer getSubConcept() {
		return subConcept;
	}

	public Integer getSuperConcept() {
		return superConcept;
	}

	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

}
