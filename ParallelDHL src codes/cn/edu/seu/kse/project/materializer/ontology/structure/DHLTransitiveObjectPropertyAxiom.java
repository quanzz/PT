package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleTransitiveObjectPropertyAxiom;

public class DHLTransitiveObjectPropertyAxiom extends SimpleTransitiveObjectPropertyAxiom
												implements DHLAxiom{

	
	public DHLTransitiveObjectPropertyAxiom(Integer property){
		
		this.property = property;
		
	}
	
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
	
	

}
