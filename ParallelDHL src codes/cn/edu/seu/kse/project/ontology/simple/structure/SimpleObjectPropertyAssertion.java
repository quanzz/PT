package cn.edu.seu.kse.project.ontology.simple.structure;

public class SimpleObjectPropertyAssertion implements SimpleAxiom {
	
	protected Integer property = 0;
	protected Integer subject = 0;
	protected Integer object = 0;
	
	public SimpleObjectPropertyAssertion(){
		
	}
	
	public SimpleObjectPropertyAssertion(
			Integer property,
			Integer subject,
			Integer object){
		
		this.property = property;
		this.subject = subject;
		this.object = object;
		
	}
	
	
	public Integer getProperty() {
		return property;
	}

	public Integer getSubject() {
		return subject;
	}

	public Integer getObject() {
		return object;
	}

	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}


}
