package cn.edu.seu.kse.project.ontology.channel.translator.abox;

import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectPropertyAssertion;

public class OWLObjectPropertyAssertionAxiomTranslator {
	
	private OWLSimpleConnector connector;
	
	public OWLObjectPropertyAssertionAxiomTranslator(OWLSimpleConnector connector){

		this.connector = connector;
	}
	

	public void translate(OWLObjectPropertyAssertionAxiom objectPropertyAssertionAxiom){
		
		OWLObjectPropertyExpression property = objectPropertyAssertionAxiom.getProperty();
		OWLIndividual subject = objectPropertyAssertionAxiom.getSubject();
		OWLIndividual object = objectPropertyAssertionAxiom.getObject();
		
		Integer propertyInt = 
				connector.getAllocater().allocatePropertyIntegerEntry(property);
		
		Integer subjectInt =
				connector.getAllocater().allocateIndividualIntegerEntry(subject);
		
		Integer objectInt =
				connector.getAllocater().allocateIndividualIntegerEntry(object);
		
		
		SimpleObjectPropertyAssertion simpleObjectPropertyAssertion =
				connector.getSimpleAxiomFactory().getSimpleObjectPropertyAssertion(
						propertyInt, subjectInt, objectInt);
		
		connector.register(simpleObjectPropertyAssertion);
	}

}
