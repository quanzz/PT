package cn.edu.seu.kse.project.ontology.simple.structure;


public class SimpleSomeValuesFromAxiom implements SimpleAxiom {
	
	protected Integer subConcept = 0;
	protected Integer role = 0;
	protected Integer superConcept = 0;
	
	public SimpleSomeValuesFromAxiom(){
		
	}
	
	public SimpleSomeValuesFromAxiom(
			Integer subConcept,
			Integer role,
			Integer superConcept){
		
		this.subConcept = subConcept;
		this.role = role;
		this.superConcept = superConcept;
		
	}
	
	public Integer getSubConcept() {
		return subConcept;
	}

	public Integer getRole() {
		return role;
	}

	public Integer getSuperConcept() {
		return superConcept;
	}

	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

}
