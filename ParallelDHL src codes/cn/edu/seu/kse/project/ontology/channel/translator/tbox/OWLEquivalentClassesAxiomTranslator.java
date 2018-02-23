package cn.edu.seu.kse.project.ontology.channel.translator.tbox;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLEquivalentClassesAxiomTranslator {
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	
	public OWLEquivalentClassesAxiomTranslator(OWLSimpleConnector connector,
			
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
	}
	

	public void translate(OWLEquivalentClassesAxiom equivalentClassesAxiom){
		
		Set<OWLSubClassOfAxiom> subClassOfAxioms = 
				equivalentClassesAxiom.asOWLSubClassOfAxioms();
		
		for(OWLSubClassOfAxiom subClassOfAxiom : subClassOfAxioms) {
			subClassOfAxiomTranslator.translate(subClassOfAxiom);
		}
		
		/*List<OWLClassExpression> classExpressions = 
				equivalentClassesAxiom.getClassExpressionsAsList();
		
		for(int i = 0; i < classExpressions.size(); i ++) {
			if(i == classExpressions.size() - 1) break;
			for(int j = i + 1; j < classExpressions.size(); j ++) {
				OWLClassExpression classExpression1 = classExpressions.get(i);
				OWLClassExpression classExpression2 = classExpressions.get(j);
				
				OWLSubClassOfAxiom subClassAxiom1 = 
						dataFactory.getOWLSubClassOfAxiom(classExpression1, classExpression2);
				OWLSubClassOfAxiom subClassAxiom2 = 
						dataFactory.getOWLSubClassOfAxiom(classExpression2, classExpression1);
				
				subClassOfAxiomTranslator.translate(subClassAxiom1);
				subClassOfAxiomTranslator.translate(subClassAxiom2);
			}
		}*/
	}


	public void register(OWLEquivalentClassesAxiom equivalentClassesAxiom) {
		
	}

}
