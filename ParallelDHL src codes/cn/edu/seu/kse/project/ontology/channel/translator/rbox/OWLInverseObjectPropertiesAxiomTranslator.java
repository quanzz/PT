package cn.edu.seu.kse.project.ontology.channel.translator.rbox;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLInverseObjectPropertiesAxiomTranslator {


	
	private OWLSubObjectPropertyOfAxiomTranslator subObjectPropertyOfAxiomTranslator;
	
	
	public OWLInverseObjectPropertiesAxiomTranslator(OWLSimpleConnector connector,
			
			OWLSubObjectPropertyOfAxiomTranslator subObjectPropertyOfAxiomTranslator){
		
		this.subObjectPropertyOfAxiomTranslator = subObjectPropertyOfAxiomTranslator;
	}
	

	public void translate(OWLInverseObjectPropertiesAxiom inverseObjectPropertiesAxiom){
		Set<OWLSubObjectPropertyOfAxiom> subObjectPropertyOfAioms = 
				inverseObjectPropertiesAxiom.asSubObjectPropertyOfAxioms();
		
		for(OWLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom : subObjectPropertyOfAioms) {
			subObjectPropertyOfAxiomTranslator.translate(subObjectPropertyOfAxiom);
		}
	}


	public void register(OWLInverseObjectPropertiesAxiom inverseObjectPropertiesAxiom) {
		
	}
	
}
