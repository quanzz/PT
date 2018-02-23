package cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLSubClassOfAxiomTranslator;
import cn.edu.seu.kse.project.ontology.exception.OntoException;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;


public class OWLObjectSomeValuesFromReducer implements OWLClassExpressionReducer {
	
	private OWLSimpleConnector connector;
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLObjectSomeValuesFromReducer(
			OWLSimpleConnector connector,
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.connector = connector;
		
		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
	}

	@Override
	public void reduceSubClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(subClassExpression instanceof OWLObjectSomeValuesFrom) {
			OWLObjectSomeValuesFrom objectSomeValuesFrom = (OWLObjectSomeValuesFrom) subClassExpression;
			
			OWLObjectPropertyExpression objectPropertyExpression = objectSomeValuesFrom.getProperty();
			OWLClassExpression restriction = objectSomeValuesFrom.getFiller();
			
			
			if(restriction instanceof OWLClass) {
				register(subClassOfAxiom);
			} else {
				
				OWLClass auxiliaryClass = connector.getAllocater().allocateOWLClass(restriction);
				OWLObjectSomeValuesFrom newObjectSomeValuesFrom = 
						connector.getOWLDataFactory().getOWLObjectSomeValuesFrom(objectPropertyExpression, auxiliaryClass);
				OWLSubClassOfAxiom newSubClassOfAxiom1 = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(newObjectSomeValuesFrom, superClassExpression);
				OWLSubClassOfAxiom newSubClassOfAxiom2 = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(restriction, auxiliaryClass);
				subClassOfAxiomTranslator.translate(newSubClassOfAxiom2);
				
				reduceSubClassExpression(newSubClassOfAxiom1);
				
			}	
					
		}
	}

	@Override
	public void reduceSuperClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {

		/*
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(superClassExpression instanceof OWLObjectSomeValuesFrom) {
			OWLObjectSomeValuesFrom objectSomeValuesFrom = (OWLObjectSomeValuesFrom) superClassExpression;
			
			OWLObjectPropertyExpression objectPropertyExpression = objectSomeValuesFrom.getProperty();
			OWLClassExpression restriction = objectSomeValuesFrom.getFiller();
			
			if(restriction instanceof OWLClass) {
				register(subClassOfAxiom);
			} else {
				
				OWLClass auxiliaryClass = connector.getAllocater().allocateOWLClass(restriction);
				OWLObjectSomeValuesFrom newObjectSomeValuesFrom = 
						connector.getOWLDataFactory().getOWLObjectSomeValuesFrom(objectPropertyExpression, auxiliaryClass);
				OWLSubClassOfAxiom newSubClassOfAxiom1 = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(subClassExpression, newObjectSomeValuesFrom);
				OWLSubClassOfAxiom newSubClassOfAxiom2 = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(auxiliaryClass, restriction);
				subClassOfAxiomTranslator.translate(newSubClassOfAxiom2);
				
				reduceSuperClassExpression(newSubClassOfAxiom1);
				
			}	
		}
		*/
	}

	@Override
	public void register(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(subClassExpression instanceof OWLObjectSomeValuesFrom) {
			OWLObjectSomeValuesFrom someValuesFrom = 
					(OWLObjectSomeValuesFrom) subClassExpression;
			
			OWLClassExpression restriction = someValuesFrom.getFiller();
			OWLObjectPropertyExpression propertyExpression = someValuesFrom.getProperty();
			
			if(restriction instanceof OWLClass
					&& superClassExpression instanceof OWLClass) {
				
				Integer subClassEntry = connector.getAllocater().allocateClassIntegerEntry(
						(OWLClass) restriction);
				Integer roleEntry = connector.getAllocater().allocatePropertyIntegerEntry(
						propertyExpression);
				Integer superClassEntry = connector.getAllocater().allocateClassIntegerEntry(
						(OWLClass) superClassExpression);
				
				SimpleLeftExistentialAxiom simpleLeftExistentialAxiom =
						connector.getSimpleAxiomFactory().getSimpleLeftExistentialAxiom(
								roleEntry, subClassEntry, superClassEntry);
				
				connector.register(simpleLeftExistentialAxiom);
			
			} else {
				try {
					throw new OntoException("There exists non-named class" +
							" in " + subClassOfAxiom.toString());
				} catch (OntoException e) {
					e.printStackTrace();
				}
			}
		} else if(superClassExpression instanceof OWLObjectSomeValuesFrom) {
			
			/*
			OWLObjectSomeValuesFrom someValuesFrom = 
					(OWLObjectSomeValuesFrom) superClassExpression;
			
			OWLClassExpression restriction = someValuesFrom.getFiller();
			OWLObjectPropertyExpression propertyExpression = someValuesFrom.getProperty();
			
			if(restriction instanceof OWLClass
					&& subClassExpression instanceof OWLClass) {
				Integer subClassEntry = connector.getAllocater().allocateClassIntegerEntry(
						(OWLClass) subClassExpression);
				Integer roleEntry = connector.getAllocater().allocatePropertyIntegerEntry(
						propertyExpression);
				Integer superClassEntry = connector.getAllocater().allocateClassIntegerEntry(
						(OWLClass) restriction);
				
				SimpleSomeValuesFromAxiom simpleSomeValuesFromAxiom = 
						connector.getSimpleAxiomFactory().getSimpleSomeValuesFromAxiom(subClassEntry, roleEntry, superClassEntry);
				
				connector.register(simpleSomeValuesFromAxiom);
				
			
			} else {
				try {
					throw new OntoException("There exists non-named class" +
							" in " + subClassOfAxiom.toString());
				} catch (OntoException e) {
					e.printStackTrace();
				}
			}
			*/
		} else {
			try {
				throw new OntoException("This axiom is not typed with left-existential: " 
							+ subClassOfAxiom.toString());
			} catch (OntoException e) {
				e.printStackTrace();
			}
		}
	}

}
