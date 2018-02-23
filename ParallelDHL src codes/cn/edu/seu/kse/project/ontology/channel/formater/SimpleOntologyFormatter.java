package cn.edu.seu.kse.project.ontology.channel.formater;

import java.util.Set;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.owl.OWLOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.api.SimpleOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;

public class SimpleOntologyFormatter {
	
	public static void formate(
			OWLSimpleConnector connector,
			OWLOntologyBase newOwlOntoBase,
			SimpleOntologyBase simpleOntoBase) {
		
		SimpleOntologyFormattingVisitor formattingVisitor = 
				new SimpleOntologyFormattingVisitor(connector, newOwlOntoBase);
		
		
		Set<SimpleAxiom> simpleAxioms = 
				simpleOntoBase.getFormatedAxioms();
		
		for(SimpleAxiom axiom : simpleAxioms) {
			axiom.accept(formattingVisitor);
		}		
	}

}
