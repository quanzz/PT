package cn.edu.seu.kse.project.ontology.simple.api;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomFactory;


public class SimpleOntologyFactory {
	
	public static SimpleAxiomFactory getSimpleAxiomFactory(){
		return new SimpleAxiomFactory();
	}

}
