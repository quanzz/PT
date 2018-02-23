package cn.edu.seu.kse.project.ontology.channel.translator.rbox;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;

public class OWLSubObjectPropertyOfAxiomTranslator {
	
	private OWLSimpleConnector connector;
	
	
	public OWLSubObjectPropertyOfAxiomTranslator(OWLSimpleConnector connector){
		this.connector = connector;
	}
	

	public void translate(OWLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom){
		
		OWLObjectPropertyExpression subPropertyExpression = 
				subObjectPropertyOfAxiom.getSubProperty();
		OWLObjectPropertyExpression superPropertyExpression = 
				subObjectPropertyOfAxiom.getSuperProperty();

		Integer subPropertyInt = 
				connector.getAllocater().allocatePropertyIntegerEntry(subPropertyExpression);
		Integer superPropertyInt = 
				connector.getAllocater().allocatePropertyIntegerEntry(superPropertyExpression);
		
		
		SimpleSubObjectPropertyOfAxiom simpleObjectSubPropertyOfAxiom =
				connector.getSimpleAxiomFactory().getSimpleSubObjectPropertyOfAxiom(
						subPropertyInt, superPropertyInt);
		
		connector.register(simpleObjectSubPropertyOfAxiom);
		
	}


	public void register(OWLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom) {
		
	}

}
