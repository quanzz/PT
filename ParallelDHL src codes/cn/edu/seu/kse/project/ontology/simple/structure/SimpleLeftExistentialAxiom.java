package cn.edu.seu.kse.project.ontology.simple.structure;

/**
 * 
 * This axiom is in the form of -] role.SubConcept [= superConcept
 * 
 * @author Zhangquan Zhou
 *
 */
public class SimpleLeftExistentialAxiom implements SimpleAxiom {
	
	protected Integer role = 0;
	protected Integer subConcept = 0;
	protected Integer superConcept = 0;
	
	public SimpleLeftExistentialAxiom(){
		
	}
	
	public SimpleLeftExistentialAxiom(
			Integer role,
			Integer subConcept,
			Integer superConcept){
		
		this.role = role;
		this.subConcept = subConcept;
		this.superConcept = superConcept;
		
	}
	
	public Integer getRole() {
		return role;
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
