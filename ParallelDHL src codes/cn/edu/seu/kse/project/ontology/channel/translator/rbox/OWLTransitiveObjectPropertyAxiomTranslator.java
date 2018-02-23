package cn.edu.seu.kse.project.ontology.channel.translator.rbox;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleTransitiveObjectPropertyAxiom;

public class OWLTransitiveObjectPropertyAxiomTranslator {
	
	private OWLSimpleConnector connector;
	
	
	public OWLTransitiveObjectPropertyAxiomTranslator(OWLSimpleConnector connector){
		this.connector = connector;
	}
	

	public void translate(OWLTransitiveObjectPropertyAxiom transitiveObjectPropertyAxiom){
		OWLObjectPropertyExpression objectProperty = 
				transitiveObjectPropertyAxiom.getProperty();
		
		Integer PropertyInt = connector.getAllocater().allocatePropertyIntegerEntry(objectProperty);
		
		SimpleTransitiveObjectPropertyAxiom simpleTransitiveObjectPropertyAxiom = 
				connector.getSimpleAxiomFactory().getSimpleTransitiveObjectPropertyAxiom(PropertyInt);
		
		connector.register(simpleTransitiveObjectPropertyAxiom);
	}


	public void register(OWLTransitiveObjectPropertyAxiom transitiveObjectPropertyAxiom) {
		
	}

}
