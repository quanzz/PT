package cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLSubClassOfAxiomTranslator;

public class OWLObjectUnionOfReducer implements OWLClassExpressionReducer {

	private OWLSimpleConnector connector;
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLObjectUnionOfReducer(
			OWLSimpleConnector connector,
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.connector = connector;

		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
		
	}
	
	
	@Override
	public void reduceSubClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(subClassExpression instanceof OWLObjectUnionOf) {
			OWLObjectUnionOf unionOf = (OWLObjectUnionOf) subClassExpression;	
			Set<OWLClassExpression> unionClassExpressions =  unionOf.asDisjunctSet();
			
			
			for(OWLClassExpression disjunct : unionClassExpressions) {
				OWLSubClassOfAxiom newSubClassOfAxiom = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(disjunct, superClassExpression);
				
				subClassOfAxiomTranslator.translate(newSubClassOfAxiom);
			}
		}	
	}

	@Override
	public void reduceSuperClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		
		
	}

	@Override
	public void register(OWLSubClassOfAxiom subClassOfAxiom) {
		
		
		
	}
	

}
