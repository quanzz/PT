package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;

public class DHLObjectIntersectionOfAxiom extends SimpleObjectIntersectionOfAxiom
											implements DHLAxiom{

	
	public DHLObjectIntersectionOfAxiom(
			Integer subConcept1,
			Integer subConcept2,
			Integer superConcept) {
		
		this.subConcept1 = subConcept1;
		this.subConcept2 = subConcept2;
		this.superConcept = superConcept;
		
	}
	
	@Override
	public void accept(DHLAxiomVisitor axiomVisitor) {
		axiomVisitor.visit(this);
	}

	
	
}
