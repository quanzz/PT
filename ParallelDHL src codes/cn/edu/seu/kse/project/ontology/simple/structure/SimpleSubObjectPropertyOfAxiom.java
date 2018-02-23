package cn.edu.seu.kse.project.ontology.simple.structure;


public class SimpleSubObjectPropertyOfAxiom implements SimpleAxiom {
	
	protected Integer subProperty = 0;
	protected Integer superProperty = 0;
	
	public SimpleSubObjectPropertyOfAxiom(){
		
	}
	
	public SimpleSubObjectPropertyOfAxiom(
			Integer subProperty,
			Integer superProperty
			){
		
		this.subProperty = subProperty;
		this.superProperty = superProperty;
		
	}
	
	
	public Integer getSubProperty() {
		return subProperty;
	}

	public Integer getSuperProperty() {
		return superProperty;
	}

	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

}
