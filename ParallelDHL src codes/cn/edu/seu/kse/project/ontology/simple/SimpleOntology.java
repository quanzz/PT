package cn.edu.seu.kse.project.ontology.simple;

import java.util.Set;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;


public class SimpleOntology {
	
	private SimpleElementIndex elementIndex;
	
	
	public SimpleOntology(){
		elementIndex = new SimpleElementIndex();
	}
	
	public void addSimpleAxioms(Set<SimpleAxiom> simpleAxioms) {
		for(SimpleAxiom simpleAxiom : simpleAxioms){
			elementIndex.addAxiom(simpleAxiom);
		}
	}
	
	public void printInfo(){
		elementIndex.printInfo();
	}
	
	public SimpleElementIndex index(){
		return elementIndex;
	}
	
	
}
