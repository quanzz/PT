package cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLSubClassOfAxiomTranslator;


public class OWLObjectAllValuesFromReducer implements OWLClassExpressionReducer {
	
	
	private OWLSimpleConnector connector;
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLObjectAllValuesFromReducer(
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
		
		if(superClassExpression instanceof OWLObjectAllValuesFrom) {
			
			OWLObjectAllValuesFrom objectAllValuesFrom = (OWLObjectAllValuesFrom) superClassExpression;
			
			OWLObjectPropertyExpression objectPropertyExpression = objectAllValuesFrom.getProperty();
			
			OWLClassExpression restriction = objectAllValuesFrom.getFiller();
			
			OWLObjectPropertyExpression inverseProperty = objectPropertyExpression.getInverseProperty();
			
			OWLObjectSomeValuesFrom newObjectSomeValuesFrom = 
					connector.getOWLDataFactory().getOWLObjectSomeValuesFrom(inverseProperty, subClassExpression);
			
			OWLSubClassOfAxiom newSubClassOfAxiom = 
					connector.getOWLDataFactory().getOWLSubClassOfAxiom(newObjectSomeValuesFrom, restriction);
			
			subClassOfAxiomTranslator.translate(newSubClassOfAxiom);
			
		}
	}

	@Override
	public void register(OWLSubClassOfAxiom subClassOfAxiom) {
		
		
	}

}
