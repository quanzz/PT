package cn.edu.seu.kse.project.ontology.simple.structure;

public class SimpleClassAssertion implements SimpleAxiom {

	protected Integer concept = 0;
	protected Integer member = 0;
	
	public SimpleClassAssertion(){
		
	}
	
	public SimpleClassAssertion(
			Integer concept,
			Integer member){
		
		this.concept = concept;
		this.member = member;
		
	}
	
	
	public Integer getConcept() {
		return concept;
	}

	public Integer getMember() {
		return member;
	}

	
	@Override
	public void accept(SimpleAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
}
