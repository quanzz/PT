package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;


public interface DHLAxiom extends SimpleAxiom {
	
	public void accept(DHLAxiomVisitor axiomVisitor);

}
