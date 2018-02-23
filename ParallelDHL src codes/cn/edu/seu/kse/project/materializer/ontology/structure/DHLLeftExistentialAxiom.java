package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;

public class DHLLeftExistentialAxiom extends SimpleLeftExistentialAxiom implements DHLAxiom{

	
	public DHLLeftExistentialAxiom(
			Integer role,
			Integer subConcept,
			Integer superConcept){
		
		this.role = role;
		this.subConcept = subConcept;
		this.superConcept = superConcept;
		
	}
	
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

}
