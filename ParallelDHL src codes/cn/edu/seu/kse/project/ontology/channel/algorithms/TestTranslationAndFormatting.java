package cn.edu.seu.kse.project.ontology.channel.algorithms;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.logger.SystemPort;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.connector.SimpleOntologyStatisticer;
import cn.edu.seu.kse.project.ontology.owl.OWLOntologyBase;
import cn.edu.seu.kse.project.ontology.owl.statistic.OWLOntologyStatisticer;
import cn.edu.seu.kse.project.ontology.simple.api.SimpleOntologyBase;
import cn.edu.seu.kse.project.toolkit.Tool;

public class TestTranslationAndFormatting {
	
	public static void main(String[] args) {
		
		String filePath = "C:\\Users\\Spring\\Desktop\\Work\\0_PT_J\\system\\ontologies\\wine.owl";
		
		Logger log = new SystemPort(Logger.DEBUG);
		
		// create an OWL ontology for a given path.
		OWLOntologyBase originalOntoBase = new OWLOntologyBase(log);
		Tool.pl("loading...");
		originalOntoBase.loadLocalOntologyFile(filePath);
		Tool.pl("loading done.");

		// print the statistics of the given owl ontology.
		OWLOntologyStatisticer owlOntologyStatisticer =
				new OWLOntologyStatisticer(originalOntoBase.getOntology());
		owlOntologyStatisticer.printReport();
		
		// a test unit
		SimpleOntologyBase simpleOntoBase = new DHLOntologyBase(log);
		
		// this is a connector between owl and smple ontologies.
		OWLSimpleConnector owlSimpleConnector = 
				new OWLSimpleConnector(
						originalOntoBase, simpleOntoBase,log);
				
		// do translation.
		Tool.pl("translating...");
		owlSimpleConnector.translate();
		Tool.pl("translation done.");
		
		SimpleOntologyStatisticer simpleOntologyStatisticer = 
				new SimpleOntologyStatisticer(owlSimpleConnector);
		simpleOntologyStatisticer.printReport();
		
		/*
		// create a new owl ontology for formatting
		OWLOntologyBase targetOntoBase = new OWLOntologyBase();
		targetOntoBase.createNewOntology();
		
		// do formatting
		SimpleOntologyFormatter.formate(
				owlSimpleConnector, targetOntoBase, simpleOntoBase);
		 */
		
	}

}
