package cn.edu.seu.kse.project.ontology.channel.translator.rbox;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLEquivalentObjectPropertiesAxiomTranslator {
	
	private OWLSimpleConnector connector;
	
	private OWLSubObjectPropertyOfAxiomTranslator subObjectPropertyOfAxiomTranslator;
	
	
	public OWLEquivalentObjectPropertiesAxiomTranslator(OWLSimpleConnector connector,

			OWLSubObjectPropertyOfAxiomTranslator subObjectPropertyOfAxiomTranslator){
		this.connector = connector;
		
		this.subObjectPropertyOfAxiomTranslator = 
				subObjectPropertyOfAxiomTranslator;
	}
	

	public void translate(OWLEquivalentObjectPropertiesAxiom equivalentObjectPropertiesAxiom){
		Set<OWLObjectPropertyExpression> properties = 
				equivalentObjectPropertiesAxiom.getProperties();
		
		
		for(OWLObjectPropertyExpression property1 :properties){
			for(OWLObjectPropertyExpression property2 :properties){
				OWLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom1 = 
						connector.getOWLDataFactory().getOWLSubObjectPropertyOfAxiom(property1, property2);
				
				OWLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom2 = 
						connector.getOWLDataFactory().getOWLSubObjectPropertyOfAxiom(property2, property1);
				
				subObjectPropertyOfAxiomTranslator.translate(subObjectPropertyOfAxiom1);
				
				subObjectPropertyOfAxiomTranslator.translate(subObjectPropertyOfAxiom2);
			}
		}
		
	}


	public void register(OWLEquivalentObjectPropertiesAxiom equivalentObjectPropertiesAxiom) {
		
	}

}
