package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;

public class DHLSubObjectPropertyOfAxiom extends SimpleSubObjectPropertyOfAxiom
												implements DHLAxiom{

	public DHLSubObjectPropertyOfAxiom(
			Integer subProperty,
			Integer superProperty
			){
		
		this.subProperty = subProperty;
		this.superProperty = superProperty;
		
	}
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
	

}
