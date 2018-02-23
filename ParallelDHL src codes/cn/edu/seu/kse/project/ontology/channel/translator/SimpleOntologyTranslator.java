package cn.edu.seu.kse.project.ontology.channel.translator;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.owl.OWLOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.api.SimpleOntologyBase;
import cn.edu.seu.kse.project.toolkit.ProcessReporter;

public class SimpleOntologyTranslator {
	
	public static void translate(
			OWLSimpleConnector owlSimpleConnector,
			OWLOntologyBase owlOntoBase,
			SimpleOntologyBase simpleOntoBase,
			Logger log
			) {
		
		// this visitor visits each axioms to translate them into
		// simple axioms.
		OWLAxiomTranslationVisitor axiomTranslationVisitor = 
				new OWLAxiomTranslationVisitor(owlSimpleConnector);
		
		// the target owl ontology
		OWLOntology ontology = owlOntoBase.getOntology();
		
		// translate.
		Set<OWLAxiom> owlAxioms = ontology.getAxioms();
		
		ProcessReporter report = 
				new ProcessReporter(owlAxioms.size(), log);
		
		for(OWLAxiom owlAxiom : owlAxioms) {
			owlAxiom.accept(axiomTranslationVisitor);
			report.anotherProcessed();
		}
		
	}

}
