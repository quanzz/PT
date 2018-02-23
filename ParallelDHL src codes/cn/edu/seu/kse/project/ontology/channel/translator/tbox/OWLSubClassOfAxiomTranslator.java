package cn.edu.seu.kse.project.ontology.channel.translator.tbox;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleAllocater;
import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer.OWLObjectAllValuesFromReducer;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer.OWLObjectComplementOfReducer;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer.OWLObjectIntersectionOfReducer;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer.OWLObjectSomeValuesFromReducer;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer.OWLObjectUnionOfReducer;
import cn.edu.seu.kse.project.ontology.exception.OntoException;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;

public class OWLSubClassOfAxiomTranslator {
	
	private OWLSimpleConnector connector;
	private OWLSimpleAllocater allocater;
	
	private OWLObjectIntersectionOfReducer intersectionOfReducer;
	private OWLObjectSomeValuesFromReducer someValuesFromReducer;
	private OWLObjectAllValuesFromReducer allValuesFromReducer;
	private OWLObjectUnionOfReducer unionOfReducer;
	private OWLObjectComplementOfReducer complementOfReducer;
	
	public OWLSubClassOfAxiomTranslator(OWLSimpleConnector connector){
		this.connector = connector;
		this.allocater = connector.getAllocater();
		
		this.intersectionOfReducer = 
				new OWLObjectIntersectionOfReducer(connector, this);
		
		this.someValuesFromReducer = 
				new OWLObjectSomeValuesFromReducer(connector, this);
		
		this.allValuesFromReducer = 
				new OWLObjectAllValuesFromReducer(connector, this);
		
		this.unionOfReducer = 
				new OWLObjectUnionOfReducer(connector, this);
		
		this.complementOfReducer =
				new OWLObjectComplementOfReducer(connector, this);
	}
	

	public void translate(OWLSubClassOfAxiom subClassOfAxiom){
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		// both of the subclass and the super-class
		// are not named classes.
		if(!(subClassExpression instanceof OWLClass)
				&& !(superClassExpression instanceof OWLClass)) {
			
			OWLClass auxiliaryClass = allocater.allocateOWLClass(subClassExpression);
			OWLSubClassOfAxiom subClassOfAxiom1 = 
					connector.getOWLDataFactory().getOWLSubClassOfAxiom(subClassExpression, auxiliaryClass);
			OWLSubClassOfAxiom subClassOfAxiom2 = 
					connector.getOWLDataFactory().getOWLSubClassOfAxiom(auxiliaryClass, superClassExpression);
			
			translate(subClassOfAxiom1);
			translate(subClassOfAxiom2);
			
		} else {
		
			if(!(subClassExpression instanceof OWLClass)) {
				
				if(subClassExpression instanceof OWLObjectIntersectionOf) {
					intersectionOfReducer.reduceSubClassExpression(subClassOfAxiom);
				
				} else if(subClassExpression instanceof OWLObjectSomeValuesFrom) {
					someValuesFromReducer.reduceSubClassExpression(subClassOfAxiom);
				
				} else if(subClassExpression instanceof OWLObjectAllValuesFrom) {
					// this could not be a Horn ontology.
					
				} else if(subClassExpression instanceof OWLObjectUnionOf) {
					unionOfReducer.reduceSubClassExpression(subClassOfAxiom);
				
				} else if(subClassExpression instanceof OWLObjectComplementOf) {
					// negative inclusions are not considered
				}
				
				
			
			} else if (!(superClassExpression instanceof OWLClass)) {
				
				if(superClassExpression instanceof OWLObjectIntersectionOf) {
					intersectionOfReducer.reduceSuperClassExpression(subClassOfAxiom);
				
				} else if(superClassExpression instanceof OWLObjectSomeValuesFrom) {
					// this case is not considered.
					// someValuesFromReducer.reduceSuperClassExpression(subClassOfAxiom);
				
				} else if(superClassExpression instanceof OWLObjectAllValuesFrom) {
					allValuesFromReducer.reduceSuperClassExpression(subClassOfAxiom);
				
				}  else if(superClassExpression instanceof OWLObjectUnionOf) {
					// this could not be a Horn ontology.
				
				}  else if(superClassExpression instanceof OWLObjectComplementOf) {
					complementOfReducer.reduceSuperClassExpression(subClassOfAxiom);
				}
				
				
			} else {
				// both of the subclass and the superclass
				// are named classes.
				register(subClassOfAxiom);
				
			}
		}
	}


	public void register(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(subClassExpression instanceof OWLClass
				&& superClassExpression instanceof OWLClass) {
			
			Integer subClassEntry = allocater.allocateClassIntegerEntry(
					(OWLClass) subClassExpression);
			Integer superClassEntry = allocater.allocateClassIntegerEntry(
					(OWLClass) superClassExpression);
			
			SimpleSubClassOfAxiom simpleObjectSubClassOfAxiom =
					connector.getSimpleAxiomFactory().getSimpleObjectSubClassOfAxiom(subClassEntry, superClassEntry);
			
			connector.register(simpleObjectSubClassOfAxiom);
			
		} else {
			try {
				throw new OntoException("There exists non-named class" +
						" in " + subClassOfAxiom.toString());
			} catch (OntoException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	

}
