package cn.edu.seu.kse.project.ontology.channel.translator.tbox;

import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLDisjointUnionAxiomTranslator {
	
	
	private OWLDisjointClassesAxiomTranslator disjointClassesAxiomTranslator;
	private OWLEquivalentClassesAxiomTranslator equivalentClassesAxiomTranslator;
	
	
	public OWLDisjointUnionAxiomTranslator(
			OWLSimpleConnector connector,
			
			OWLDisjointClassesAxiomTranslator disjointClassesAxiomTranslator,
		    OWLEquivalentClassesAxiomTranslator equivalentClassesAxiomTranslator
		    
			){
		
		this.disjointClassesAxiomTranslator = disjointClassesAxiomTranslator;
		this.equivalentClassesAxiomTranslator = equivalentClassesAxiomTranslator;
	}
	

	public void translate(OWLDisjointUnionAxiom disjointUnionAxiom){
		
		OWLDisjointClassesAxiom disjointClassesAxiom = 
				disjointUnionAxiom.getOWLDisjointClassesAxiom();
		OWLEquivalentClassesAxiom equivalentClassesAxiom = 
				disjointUnionAxiom.getOWLEquivalentClassesAxiom();
		
		this.disjointClassesAxiomTranslator.translate(disjointClassesAxiom);
		
		this.equivalentClassesAxiomTranslator.translate(equivalentClassesAxiom);
	}


	public void register(OWLDisjointUnionAxiom disjointUnionAxiom) {
		
	}

}
