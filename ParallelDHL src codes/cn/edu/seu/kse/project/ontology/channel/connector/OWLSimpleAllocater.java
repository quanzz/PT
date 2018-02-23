package cn.edu.seu.kse.project.ontology.channel.connector;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import cn.edu.seu.kse.project.ontology.exception.OntoException;

public class OWLSimpleAllocater {
	
	private OWLSimpleIndex index;
	
	public OWLSimpleAllocater(OWLSimpleIndex index){
		this.index = index;
	}
	
	
	/**
	 * allocate an auxiliary concept for classExpression.
	 * @param classExpression this has to be a complex concept.
	 * @return an auxiliary concept.
	 */
	public OWLClass allocateOWLClass(OWLClassExpression classExpression){
		if(index.auxiliaryClassMap.containsKey(classExpression)) {
			return index.auxiliaryClassMap.get(classExpression);
		}
		
		OWLClass newClass = index.dataFactory.getOWLClass(
				IRI.create(
						OWLSimpleIndex.AUX_PREFIX_CLASS + index.auxiliaryClassIdAllocator.allocate()));
		index.auxiliaryClassMap.put(classExpression, newClass);
		return newClass;
	}
	
	public OWLObjectProperty allocateOWLObjectProperty(
			OWLObjectPropertyExpression propertyExpression) {
		if(index.auxiliaryObjectPropertyMap.containsKey(propertyExpression)) {
			return index.auxiliaryObjectPropertyMap.get(propertyExpression);
		}
		
		OWLObjectProperty newProperty = index.dataFactory.getOWLObjectProperty(
				IRI.create(
						OWLSimpleIndex.AUX_PREFIX_PROPERTY + index.auxiliaryObjectPropertyIdAllocator.allocate()));
		index.auxiliaryObjectPropertyMap.put(propertyExpression, newProperty);
		return newProperty;
	}
	
	public OWLObjectProperty allocateOWLObjectProperty() {	
		OWLObjectProperty newProperty = index.dataFactory.getOWLObjectProperty(
				IRI.create(
						OWLSimpleIndex.AUX_PREFIX_PROPERTY + index.auxiliaryObjectPropertyIdAllocator.allocate()));
		return newProperty;
	}
	
	/**
	 * allocate integer entry for all classes.
	 *  
	 * @param owlClass a named class
	 * @return its integer entry
	 */
	public Integer allocateClassIntegerEntry(OWLClass owlClass){
		if(index.invClassMap.containsKey(owlClass)) {
			return index.invClassMap.get(owlClass);
		
		} else {
			Integer entry = index.classIdentityAllocator.allocate();
			index.classMap.put(entry, owlClass);
			index.invClassMap.put(owlClass, entry);
			return entry;
			
		}
	}
	
	public Integer allocatePropertyIntegerEntry(OWLObjectPropertyExpression propertyExpression) {
		Integer entry = 0;
		
		if(index.invPropertyMap.containsKey(propertyExpression)) {
			entry = index.invPropertyMap.get(propertyExpression);
			
		} else {
			entry = index.propertyIdentityAllocator.allocate();
			index.propertyMap.put(entry, propertyExpression);
			index.invPropertyMap.put(propertyExpression, entry);
			
			OWLObjectPropertyExpression inverseProperty =
					propertyExpression.getInverseProperty();
			
			Integer inverseEntry = 0;
			if(index.invPropertyMap.containsKey(inverseProperty)) {
				inverseEntry = index.invPropertyMap.get(inverseProperty);
				
				
			} else {
				inverseEntry = index.propertyIdentityAllocator.allocate();
				index.propertyMap.put(inverseEntry, inverseProperty);
				index.invPropertyMap.put(inverseProperty, inverseEntry);
				
				addInverseProperty(entry,inverseEntry);
				addInverseProperty(inverseEntry,entry);
			}	
		}
		
		return entry;
	}
	
	private void addInverseProperty(Integer objectProperty,
			Integer objectInverseProperty){
		if(index.inversePropertyIntegerMap.containsKey(objectProperty)){
			return;	
		} else {
			index.inversePropertyIntegerMap.put(objectProperty, objectInverseProperty);
		}
	}
	
	
	public Integer allocateIndividualIntegerEntry(OWLIndividual individual){
		Integer entry = 0;
		
		if(index.invIndividualMap.containsKey(individual)) {
			entry = index.invIndividualMap.get(individual);
			
		} else {
			entry = index.individualIdentityAllocator.allocate();
			index.individualMap.put(entry, individual);
			index.invIndividualMap.put(individual, entry);
		}
		
		return entry;
	}

	public OWLClass getBottomClass(){
		return index.bottomClass;
	}
	
	public OWLClass getTopClass(){
		return index.topClass;
	}

	public OWLIndividual getOWLIndividualByInteger(Integer individualInteger) {
		if(!index.individualMap.containsKey(individualInteger)) {
			try {
				throw new OntoException("There is no OWLIndividual for "
						+ "the integer: " + individualInteger);
			} catch (OntoException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return index.individualMap.get(individualInteger);
		}
	}
	
	public OWLClass getOWLClassByInteger(Integer classInteger) {
		if(!index.classMap.containsKey(classInteger)) {
			try {
				throw new OntoException("There is no OWLClass for "
						+ "the integer: " + classInteger);
			} catch (OntoException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return index.classMap.get(classInteger);
		}
	}
	
	public OWLObjectPropertyExpression getOWLObjectPropertyExpressionByInteger(
			Integer objectPropertyInteger) {
		if(!index.propertyMap.containsKey(objectPropertyInteger)) {
			try {
				throw new OntoException("There is no OWLObjectPropertyExpression for "
						+ "the integer: " + objectPropertyInteger);
			} catch (OntoException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return index.propertyMap.get(objectPropertyInteger);
		}
	}
}
