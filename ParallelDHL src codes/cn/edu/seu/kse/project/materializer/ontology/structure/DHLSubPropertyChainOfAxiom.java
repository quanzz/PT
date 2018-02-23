package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;

public class DHLSubPropertyChainOfAxiom extends SimpleSubPropertyChainOfAxiom
										implements DHLAxiom{

	
	public DHLSubPropertyChainOfAxiom(
			Integer subProperty1,
			Integer subProperty2,
			Integer superProperty
			){
		
		this.subProperty1 = subProperty1;
		this.subProperty2 = subProperty2;
		this.superProperty = superProperty;
		
	}
	
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
	

}
