package cn.edu.seu.kse.project.ontology.channel.translator.tbox;

import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLObjectPropertyDomainAxiomTranslator {
	
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLObjectPropertyDomainAxiomTranslator(OWLSimpleConnector connector,
			
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
	}
	

	public void translate(OWLObjectPropertyDomainAxiom objectPropertyDomainAxiom){
		OWLSubClassOfAxiom subClassOfAxiom = objectPropertyDomainAxiom.asOWLSubClassOfAxiom();
		subClassOfAxiomTranslator.translate(subClassOfAxiom);
	}


	public void register(OWLObjectPropertyDomainAxiom objectPropertyDomainAxiom) {
		
	}

}
