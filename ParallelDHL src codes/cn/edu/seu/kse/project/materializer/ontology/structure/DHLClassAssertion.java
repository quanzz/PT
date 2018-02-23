package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleClassAssertion;

public class DHLClassAssertion extends SimpleClassAssertion implements DHLAxiom {


	public DHLClassAssertion(Integer concept, Integer member){
		this.concept = concept;
		this.member = member;
	}
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

}
