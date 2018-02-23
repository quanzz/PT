package cn.edu.seu.kse.project.ontology.owl;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLOntology;

import cn.edu.seu.kse.project.ontology.exception.OntoException;
import cn.edu.seu.kse.project.toolkit.ProcessTracker;

public class OntologyScanner {
	
	
	private OWLOntology ontology = null;
	
	private OWLAxiomVisitor[] axiomVisitors = null;
	
	public void loadOntology(OWLOntology ontology) {
		this.ontology = ontology;
	}

	public void registerVisitors(OWLAxiomVisitor... axiomVisitors) {
		this.axiomVisitors = axiomVisitors;
	}
	
	public void scan() throws OntoException{
		
		if(ontology != null
				&& axiomVisitors != null) {
			
			Set<OWLAxiom> axiomSet = ontology.getAxioms();
			ProcessTracker processTracker = 
					new ProcessTracker("Ontology scanning", axiomSet.size());
			
			for(OWLAxiom axiom : axiomSet) {
				for(OWLAxiomVisitor axiomVisitor : axiomVisitors) {
					axiom.accept(axiomVisitor);
				}
			
				processTracker.processed(1);
			}
		
		} else {
			if(ontology == null)
				throw new OntoException("No ontology loaded.");
			
			if(axiomVisitors == null)
				throw new OntoException("No OWLAxiomVisitor loaded.");
		}
		
	}
	
	
	
	
	
}
