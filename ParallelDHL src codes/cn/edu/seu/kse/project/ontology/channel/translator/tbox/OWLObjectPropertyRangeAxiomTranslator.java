package cn.edu.seu.kse.project.ontology.channel.translator.tbox;

import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLObjectPropertyRangeAxiomTranslator {
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	
	public OWLObjectPropertyRangeAxiomTranslator(OWLSimpleConnector connector,
			
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
	}
	

	public void translate(OWLObjectPropertyRangeAxiom objectPropertyRangeAxiom){
		OWLSubClassOfAxiom subClassOfAxiom = objectPropertyRangeAxiom.asOWLSubClassOfAxiom();
		subClassOfAxiomTranslator.translate(subClassOfAxiom);
	}


	public void register(OWLObjectPropertyRangeAxiom objectPropertyRangeAxiom) {
		
	}

}
