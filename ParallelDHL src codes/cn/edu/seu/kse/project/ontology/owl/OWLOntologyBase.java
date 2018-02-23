package cn.edu.seu.kse.project.ontology.owl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import cn.edu.seu.kse.project.logger.Logger;

public class OWLOntologyBase {
	
	private String name = this.getClass().getName()+">";
	
	private OWLOntologyManager manager = null;
	private OWLOntology ontology;
	private OWLDataFactory dataFactory;
	
	private Logger log;
	
	public OWLOntologyBase(Logger log){
		this.manager =  OWLManager.createOWLOntologyManager();	
		this.dataFactory = OWLManager.getOWLDataFactory();
		
		this.log = log;
	}
	
	public void createNewOntology(){
		try {
			this.ontology = manager.createOntology();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
	
	public void loadLocalOntologyFile(String filePath){
		OWLOntology ontology = null;
		
		File ontologyFile = new File(filePath);
		try {
			 ontology = 
					 manager.loadOntologyFromOntologyDocument(ontologyFile);	
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		
		this.ontology = ontology;
		log.debug(name+"an ontology has been loaded from: "+filePath);
	}
	
	public OWLDataFactory getDataFactory(){
		return dataFactory;
	}
	
	public OWLOntology getOntology(){
		return ontology;
	}
	
	
	public void printAxioms(OWLOntology ontology){
		System.out.println("#axioms: " + ontology.getAxiomCount());
		
		Set<OWLAxiom> axiomSet = ontology.getAxioms();
		for(OWLAxiom axiom : axiomSet) {
			System.out.println(axiom.toString());
		}
	}
	
	public void addAxiom(OWLAxiom axiom) {
		manager.addAxiom(ontology, axiom);
	}
	
	public void write(String outputFile) {
		
		File outFile = new File(outputFile);
		FileOutputStream fos;
		try {
			if(!outFile.exists()) {
				outFile.createNewFile();
			}
			fos = new FileOutputStream(outFile);
			manager.saveOntology(ontology, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
