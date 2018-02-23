package cn.edu.seu.kse.project.ontology.simple.structure;



public class SimpleTransitiveObjectPropertyAxiom implements SimpleAxiom {
	
	protected Integer property;
	
	public SimpleTransitiveObjectPropertyAxiom(){
		
	}
	
	public SimpleTransitiveObjectPropertyAxiom(Integer property){
		
		this.property = property;
		
	}
	
	public Integer getProperty() {
		return property;
	}

	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
}