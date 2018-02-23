package cn.edu.seu.kse.project.ontology.channel.translator.tbox;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleAllocater;
import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;

public class OWLDisjointClassesAxiomTranslator {
	
	private OWLSimpleConnector connector;
	private OWLSimpleAllocater allocator;
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLDisjointClassesAxiomTranslator(
			OWLSimpleConnector connector,
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		this.connector = connector;
		this.allocator = connector.getAllocater();
		
		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
	}
	

	public void translate(OWLDisjointClassesAxiom disjointClassesAxiom){
		
		Set<OWLDisjointClassesAxiom> disjointClassesAxioms = disjointClassesAxiom.asPairwiseAxioms();
		OWLClass bottomClass = allocator.getBottomClass();

		for(OWLDisjointClassesAxiom pairwiseAxiom : disjointClassesAxioms) {
			Set<OWLClassExpression> classExpressions = pairwiseAxiom.getClassExpressions();
			
			OWLObjectIntersectionOf intersectionOf = 
					connector.getOWLDataFactory().getOWLObjectIntersectionOf(classExpressions);
			
			OWLSubClassOfAxiom subClassOfAxiom = 
					connector.getOWLDataFactory().getOWLSubClassOfAxiom(intersectionOf, bottomClass);
			
			subClassOfAxiomTranslator.translate(subClassOfAxiom);
		}
	}


	public void register(OWLDisjointClassesAxiom disjointClassesAxiom) {
		
	}

}
