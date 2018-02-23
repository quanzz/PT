package cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer;


import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLSubClassOfAxiomTranslator;

public class OWLObjectComplementOfReducer implements OWLClassExpressionReducer {

	private OWLSimpleConnector connector;
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLObjectComplementOfReducer(
			OWLSimpleConnector connector,
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.connector = connector;
		
		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
		
	}
	
	
	@Override
	public void reduceSubClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		
	}

	@Override
	public void reduceSuperClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		

		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(superClassExpression instanceof OWLObjectComplementOf) {
			OWLObjectComplementOf complementOf = (OWLObjectComplementOf) superClassExpression;
			
			OWLClassExpression operand = complementOf.getOperand();
			
			OWLObjectIntersectionOf intersectionOf = 
					connector.getOWLDataFactory().getOWLObjectIntersectionOf(subClassExpression, operand);
			OWLClass bottomClass = connector.getAllocater().getBottomClass();
			
			OWLSubClassOfAxiom newSubClassOfAxiom = 
					connector.getOWLDataFactory().getOWLSubClassOfAxiom(intersectionOf, bottomClass);
			subClassOfAxiomTranslator.translate(newSubClassOfAxiom);
			
		}	
		
	}

	@Override
	public void register(OWLSubClassOfAxiom subClassOfAxiom) {
		
		
		
	}
	

}
