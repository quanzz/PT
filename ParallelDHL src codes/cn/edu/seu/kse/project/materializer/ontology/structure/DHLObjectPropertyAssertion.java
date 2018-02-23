package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectPropertyAssertion;

public class DHLObjectPropertyAssertion extends SimpleObjectPropertyAssertion implements DHLAxiom{

	
	public DHLObjectPropertyAssertion(
			Integer property,
			Integer subject,
			Integer object){
		
		this.property = property;
		this.subject = subject;
		this.object = object;
		
	}
	
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
	

}
