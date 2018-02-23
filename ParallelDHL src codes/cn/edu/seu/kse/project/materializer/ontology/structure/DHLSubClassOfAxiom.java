package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;

public class DHLSubClassOfAxiom extends SimpleSubClassOfAxiom implements DHLAxiom{
	
	
	public DHLSubClassOfAxiom(
			Integer subConcept,
			Integer superConcept){
		
		this.subConcept = subConcept;
		this.superConcept = superConcept;
		
	}

	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}
	
	

}
