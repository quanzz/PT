package cn.edu.seu.kse.project.ontology.simple.structure;

public class SimpleSubPropertyChainOfAxiom implements SimpleAxiom {
	
	protected Integer subProperty1;
	protected Integer subProperty2;
	protected Integer superProperty;
	
	public SimpleSubPropertyChainOfAxiom(){
		
	}
	
	public SimpleSubPropertyChainOfAxiom(
			Integer subProperty1,
			Integer subProperty2,
			Integer superProperty
			){
		
		this.subProperty1 = subProperty1;
		this.subProperty2 = subProperty2;
		this.superProperty = superProperty;
		
	}

	public Integer getSubProperty1() {
		return subProperty1;
	}
	
	public Integer getSubProperty2() {
		return subProperty2;
	}


	public Integer getSuperProperty() {
		return superProperty;
	}


	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
}
