package cn.edu.seu.kse.project.ontology.channel.translator.abox;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLIndividual;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleClassAssertion;

public class OWLClassAssertionAxiomTranslator {
	
	private OWLSimpleConnector connector;
	
	public OWLClassAssertionAxiomTranslator(OWLSimpleConnector connector){

		this.connector = connector;
	}
	

	public void translate(OWLClassAssertionAxiom classAssertionAxiom){
		
		OWLClassExpression concept = classAssertionAxiom.getClassExpression();
		OWLIndividual member = classAssertionAxiom.getIndividual();
		
		Integer memberInt = connector.getAllocater().allocateIndividualIntegerEntry(member);
		
		Integer conceptInt =0;	
		if(concept instanceof OWLClass) {
			conceptInt = connector.getAllocater().allocateClassIntegerEntry((OWLClass)concept);
		
		} else {
			OWLClass auxiliaryClass = connector.getAllocater().allocateOWLClass(concept);
			conceptInt = connector.getAllocater().allocateClassIntegerEntry(auxiliaryClass);
		}
		
		SimpleClassAssertion simpleClassAssertion =
				connector.getSimpleAxiomFactory().getSimpleClassAssertion(conceptInt, memberInt);
		
		connector.register(simpleClassAssertion);
	}						

}
