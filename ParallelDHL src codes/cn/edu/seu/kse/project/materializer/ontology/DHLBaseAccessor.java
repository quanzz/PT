package cn.edu.seu.kse.project.materializer.ontology;

import java.util.Map;
import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;

public class DHLBaseAccessor {
	
	DHLIndex index;
	
	public DHLBaseAccessor(DHLIndex index) {
		this.index = index;
	}
	
	// subClassOfAxiom
	
	public Set<DHLAxiom> getSubClassOfAxiomsBySubClass(
			Integer subClass) {
		
		Set<DHLAxiom> resultSet = null;
		
		if(index.leftIndexOfSubClassOfAxioms.containsKey(subClass)) {
			resultSet = index.leftIndexOfSubClassOfAxioms.get(subClass);	
		}
		
		return resultSet;
	}
	
	// left existential
	
	public Set<DHLAxiom> getLeftExistentialAxiomsBySubRole(
			Integer subRole) {
		
		Set<DHLAxiom> resultSet = null;
		
		if(index.subRoleIndexOfLeftExistentialAxiom.containsKey(subRole)) {
			resultSet = index.subRoleIndexOfLeftExistentialAxiom.get(subRole);	
		}
		
		return resultSet;
	}
	
	
	// intersection
	
	public Set<DHLAxiom> getIntersectionOfAxiomsByLeftFirst(
			Integer leftFirst) {
		
		Set<DHLAxiom> resultSet = null;
		
		if(index.leftFirstIndexOfIntersectionOfAxioms.containsKey(leftFirst)) {
			resultSet = index.leftFirstIndexOfIntersectionOfAxioms.get(leftFirst);	
		}
		
		return resultSet;
	}
	
	public Set<DHLAxiom> getIntersectionOfAxiomsByLeftSecond(
			Integer leftSecond) {
		
		Set<DHLAxiom> resultSet = null;
		
		if(index.leftSecondIndexOfIntersectionOfAxioms.containsKey(leftSecond)) {
			resultSet = index.leftSecondIndexOfIntersectionOfAxioms.get(leftSecond);
		
		}
		
		return resultSet;
	}
	
	// subObjectPropertyOf
	
	public Set<DHLAxiom> getSubObjectPropertyOfAxiomsBySubProperty(Integer subProperty) {
		
		Set<DHLAxiom> resultSet = null;
		
		if(index.leftIndexOfSubPropertyOfAxioms.containsKey(subProperty)) {
			resultSet = index.leftIndexOfSubPropertyOfAxioms.get(subProperty);
		}
		
		return resultSet;
		
	}
	
	// transitivity
	
	public boolean isTransitive(Integer property){
		if(index.transitiveRoles.contains(property)) {
			return true;
		}
		return false;
	}
	
	// role chain
	
	public Set<DHLAxiom> getPropertyChainOfAxiomsByLeftFirst(
			Integer subProperty1){
		Set<DHLAxiom> result = null;
		
		if(index.leftFirstIndexOfSubPropertyChainOfAxioms.containsKey(subProperty1)) {
			result = index.leftFirstIndexOfSubPropertyChainOfAxioms.get(subProperty1);
		}

		return result;
	}
	
	public Set<DHLAxiom> getPropertyChainOfAxiomsByLeftSecond(
			Integer subProperty2){
		Set<DHLAxiom> result = null;
		
		if(index.leftSecondIndexOfSubPropertyChainOfAxioms.containsKey(subProperty2)) {
			result = index.leftSecondIndexOfSubPropertyChainOfAxioms.get(subProperty2);
		}

		return result;
	}
	
	// inverse property
	
	public Integer getInversePorperty(Integer property) {
		if(index.inversePropertyMap.containsKey(property)) {
			return index.inversePropertyMap.get(property);
		}
		return null;
	}
	
	
	// class assertion
	
	public Set<DHLAxiom> getAllClassAssertions(){
		return index.classAssertionSet;
	}
	
	public boolean containsClassAssertion(DHLClassAssertion assertion) {
		return containsClassAssertion(assertion.getConcept(),assertion.getMember());
	}
	
	public boolean containsClassAssertion(Integer concept, Integer member) {

		if(index.classMemberIndexOfClassAssertions.containsKey(concept)) {
			Map<Integer, Set<DHLAxiom>> subMap = 
					index.classMemberIndexOfClassAssertions.get(concept);
			
			if(subMap.containsKey(member)) {
				return true;
			}
		}
		
		return false;	
	}
	
	// role assertion
	
	public Set<DHLAxiom> getAllRoleAssertions(){
		return index.propertyAssertionSet;
	}
	
	
	public boolean containsRoleAssertion(
			Integer role, Integer subject, Integer object) {
		
		if(index.roleFirstSecondIndexOfPropertyAssertions.containsKey(role)) {
			
			Map<Integer, Map<Integer, Set<DHLAxiom>>> subMap =
					index.roleFirstSecondIndexOfPropertyAssertions.get(role);
			
			if(subMap.containsKey(subject)) {
				Map<Integer, Set<DHLAxiom>> subsubMap = subMap.get(subject);
				
				if(subsubMap.containsKey(object)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public Map<Integer, Set<DHLAxiom>> getObjectPropertyAssertionsByRoleAndSubject(
			Integer role, Integer subject){
		Map<Integer, Set<DHLAxiom>> map = null;
		
		if(index.roleFirstSecondIndexOfPropertyAssertions.containsKey(role)) {
			Map<Integer, Map<Integer, Set<DHLAxiom>>> subMap =
					index.roleFirstSecondIndexOfPropertyAssertions.get(role);
			
			if(subMap.containsKey(subject)) {
				map = subMap.get(subject);
			}
		}
		
		return map;
	}
	
	public Map<Integer, Set<DHLAxiom>> getObjectPropertyAssertionsByRoleAndObject(
			Integer role, Integer object){
		Map<Integer, Set<DHLAxiom>> map = null;
		
		if(index.roleSecondFirstIndexOfPropertyAssertions.containsKey(role)) {
			Map<Integer, Map<Integer, Set<DHLAxiom>>> subMap =
					index.roleSecondFirstIndexOfPropertyAssertions.get(role);
			
			if(subMap.containsKey(object)) {
				map = subMap.get(object);
			}
		}
		
		return map;
	}

}
