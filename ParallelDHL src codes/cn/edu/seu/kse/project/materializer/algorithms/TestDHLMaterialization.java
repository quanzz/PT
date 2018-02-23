package cn.edu.seu.kse.project.materializer.algorithms;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.logger.SystemPort;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyStatisticer;
import cn.edu.seu.kse.project.materializer.reasoner.DHLReasoner;
import cn.edu.seu.kse.project.materializer.reasoner.DHLReasonerManager;
import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.connector.SimpleOntologyStatisticer;
import cn.edu.seu.kse.project.ontology.channel.formater.SimpleOntologyFormatter;
import cn.edu.seu.kse.project.ontology.owl.OWLOntologyBase;
import cn.edu.seu.kse.project.ontology.owl.statistic.OWLOntologyStatisticer;
import cn.edu.seu.kse.project.toolkit.Tool;

public class TestDHLMaterialization {
	
	public static void main(String[] args) {
		
		String dir = "C:\\Users\\Spring\\Desktop\\Work\\0_PT_J\\"
				+ "system\\ontologies\\";
		
		String fileName = "shanghai-subInversePropertyOf";
		
		String filePath = dir + fileName+".owl";
		
		String outFilePath = dir + fileName+"-result.owl";
		
		int numOfThreads = 1;
		
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
		
		//originalOntoBase.printAxioms(originalOntoBase.getOntology());
		
		// a test unit
		DHLOntologyBase simpleOntoBase = new DHLOntologyBase(log);
		
		// this is a connector between owl and smple ontologies.
		OWLSimpleConnector owlSimpleConnector = new OWLSimpleConnector(
				originalOntoBase, simpleOntoBase,log);
				
		// do translation.
		Tool.pl("translating...");
		owlSimpleConnector.translate();
		Tool.pl("translation done.");
				
		SimpleOntologyStatisticer simpleOntologyStatisticer = 
				new SimpleOntologyStatisticer(owlSimpleConnector);
		simpleOntologyStatisticer.printReport();	
		
		DHLOntologyStatisticer dhlOntologyStatisticer = 
				new DHLOntologyStatisticer(simpleOntoBase);
		dhlOntologyStatisticer.printReport();
		
		// do materialization.
		
		DHLReasoner reasoner = 
				DHLReasonerManager.createParallelDHLReasoner(simpleOntoBase,
						DHLReasoner.MATERIALIZER,numOfThreads,log);
		
		Tool.pl("reasoning...");
		if(reasoner != null) {
			reasoner.saturate();
		}
		Tool.pl("reasoning done.");
		
		dhlOntologyStatisticer.printReport();
		
		Tool.pl("writting...");
		OWLOntologyBase newOwlBase = new OWLOntologyBase(log);
		newOwlBase.createNewOntology();
		SimpleOntologyFormatter.formate(
				owlSimpleConnector, newOwlBase, simpleOntoBase);
		
		//newOwlBase.printAxioms(newOwlBase.getOntology());
		
		newOwlBase.write(outFilePath);
		Tool.pl("writting done.");
		
		
	}

}
