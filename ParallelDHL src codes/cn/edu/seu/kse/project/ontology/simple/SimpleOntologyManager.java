package cn.edu.seu.kse.project.ontology.simple;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomFactory;

public class SimpleOntologyManager {
	
	public static SimpleOntology createSimpleOntology(){
		return new SimpleOntology();
	}
	
	public static SimpleAxiomFactory getSimpleElementFactory(){
		return new SimpleAxiomFactory();
	}

}
