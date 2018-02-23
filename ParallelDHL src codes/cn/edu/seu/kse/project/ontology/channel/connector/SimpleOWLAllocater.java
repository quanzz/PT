package cn.edu.seu.kse.project.ontology.channel.connector;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import cn.edu.seu.kse.project.ontology.exception.OntoException;

public class SimpleOWLAllocater {
	
	private OWLSimpleIndex index;
	
	public SimpleOWLAllocater(OWLSimpleIndex index){
		this.index = index;
	}
	
	public void addSimpleClass(Integer classId, String className) {
		if(!index.classMap.containsKey(classId)) {
			OWLClass newClass = index.dataFactory.getOWLClass(IRI.create(
						OWLSimpleIndex.SEU_PREFIX_CLASS + className));
			index.classMap.put(classId, newClass);
			index.invClassMap.put(newClass, classId);
		}
	}
	
	public void addSimpleProperty(Integer propertyId, String propertyName) {
		if(!index.propertyMap.containsKey(propertyId)) {
			OWLObjectPropertyExpression newRole = index.dataFactory.getOWLObjectProperty(
					IRI.create(OWLSimpleIndex.SEU_PREFIX_PROPERTY + propertyName));
			index.propertyMap.put(propertyId, newRole);
			index.invPropertyMap.put(newRole, propertyId);
		}
	}
	
	public void addSimpleIndividual(Integer individualId, String individualName) {
		if(!index.individualMap.containsKey(individualId)) {
			OWLIndividual newIndividual = index.dataFactory.getOWLNamedIndividual(
					IRI.create(OWLSimpleIndex.SEU_PREFIX_INDIVIDUAL + individualName));
			
			index.individualMap.put(individualId, newIndividual);
			index.invIndividualMap.put(newIndividual, individualId);
		}
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
