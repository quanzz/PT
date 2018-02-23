package cn.edu.seu.kse.project.ontology.channel.translator.rbox;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;

public class OWLSubPropertyChainOfAxiomTranslator {
	
	
	private OWLSimpleConnector connector;
	
	public OWLSubPropertyChainOfAxiomTranslator(OWLSimpleConnector connector){
		this.connector = connector;
	}
	

	public void translate(OWLSubPropertyChainOfAxiom subPropertyChainOfAxiom){
		
		OWLObjectPropertyExpression superPropertyExpression = 
				subPropertyChainOfAxiom.getSuperProperty();
		
		List<OWLObjectPropertyExpression> chain = 
				subPropertyChainOfAxiom.getPropertyChain();
		
		if(chain.size() == 2) {
			
			Integer subProperty1 = connector.getAllocater()
					.allocatePropertyIntegerEntry(chain.get(0));		
			Integer subProperty2 = connector.getAllocater()
					.allocatePropertyIntegerEntry(chain.get(1));		
			Integer superPropertyInt = connector.getAllocater()
					.allocatePropertyIntegerEntry(superPropertyExpression);
			
			
			SimpleSubPropertyChainOfAxiom simpleSubPropertyChainOfAxiom = 
					connector.getSimpleAxiomFactory().getSimpleSubPropertyChainOfAxiom(
							subProperty1, subProperty2, superPropertyInt);
		
			connector.register(simpleSubPropertyChainOfAxiom);
			
		
		} else if(chain.size() > 2) {
			OWLObjectPropertyExpression subProperty1 = chain.get(0);
			OWLObjectPropertyExpression subProperty2 = chain.get(1);
	
			List<OWLObjectPropertyExpression> subChain = new ArrayList<OWLObjectPropertyExpression>();
			subChain.add(0, subProperty1);
			subChain.add(1, subProperty2);
			
			OWLObjectPropertyExpression auxiliaryProperty =
					connector.getAllocater().allocateOWLObjectProperty();
			
			OWLSubPropertyChainOfAxiom newSubPropertyChainAxiom1 =
					connector.getOWLDataFactory().getOWLSubPropertyChainOfAxiom(subChain, auxiliaryProperty);
			
			this.translate(newSubPropertyChainAxiom1);
			
			chain.remove(0);
			chain.remove(1);
			chain.add(0, auxiliaryProperty);
			
			OWLSubPropertyChainOfAxiom newSubPropertyChainAxiom2 =
					connector.getOWLDataFactory().getOWLSubPropertyChainOfAxiom(chain, superPropertyExpression);
			
			this.translate(newSubPropertyChainAxiom2);
		}
	
	}


	public void register(OWLSubPropertyChainOfAxiom subPropertyChainOfAxiom) {
		
	}

}
