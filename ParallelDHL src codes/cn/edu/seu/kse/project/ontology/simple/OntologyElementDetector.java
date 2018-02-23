package cn.edu.seu.kse.project.ontology.simple;

import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSomeValuesFromAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;

/**
 * 
 * This class is used for detecting in an ontology
 * whether a concept is simple, a role is simple and safe.
 * 
 * 
 * @author Zhangquan Zhou
 *
 */
public class OntologyElementDetector {
	
	private SimpleElementIndex elementIndex;
	
	private Set<Integer> unsimpleConceptSet;
	private Set<Integer> unsafeRoleSet;
	private Set<Integer> unsimpleRoleSet;
	
	public OntologyElementDetector(SimpleElementIndex elementIndex){
		this.elementIndex = elementIndex;
		
		unsimpleConceptSet = new HashSet<Integer>();
		unsafeRoleSet = new HashSet<Integer>();
		unsimpleRoleSet = new HashSet<Integer>();
		
	}
	
	
	public Set<Integer> getUnsimpleConceptSet() {
		return unsimpleConceptSet;
	}

	public Set<Integer> getUnsimpleRoleSet() {
		return unsimpleRoleSet;
	}


	public void unsafeRoleFinding(){
		
		Map<Integer, Set<SimpleAxiom>> roleIndexOfSomeValuesFromAxioms = 
				elementIndex.roleIndexOfSomeValuesFromAxiom;
		
		Map<Integer, Set<SimpleAxiom>> roleIndexOfAllValuesFromAxioms = 
				elementIndex.roleIndexOfAllValuesFromAxiom;
		
		Map<Integer, Set<SimpleAxiom>> leftIndexOfSubPropertyOfAxiom = 
				elementIndex.leftIndexOfSubPropertyOfAxioms;
		
		Map<Integer, Set<Integer>> subRoleIndexOfSubPropertyChainOfAxioms =
				elementIndex.subRoleIndexOfSubPropertyChainOfAxioms;
		
		for(Integer role : elementIndex.roleSet) {
			
			if(roleIndexOfSomeValuesFromAxioms.containsKey(role)) {
				if(leftIndexOfSubPropertyOfAxiom.containsKey(role)) {
					
					// propagation.
					Queue<Integer> currentVisitedRoles = new LinkedBlockingQueue<Integer>();
					currentVisitedRoles.offer(role);
					
					while(!currentVisitedRoles.isEmpty()) {
						Integer currentRole = currentVisitedRoles.poll();
						if(roleIndexOfAllValuesFromAxioms.containsKey(currentRole)) {
							unsafeRoleSet.add(role);
							break;
						}
						
						// propagation via subPropertyOf axioms.
						Set<SimpleAxiom> subPropertyOfAxioms = 
								leftIndexOfSubPropertyOfAxiom.get(role);
						for(SimpleAxiom axiom : subPropertyOfAxioms) {
							Integer superRole = 
									((SimpleSubObjectPropertyOfAxiom) axiom).getSuperProperty();
							currentVisitedRoles.offer(superRole);
						}
						
						// propagation via complex RIAs.
						if(subRoleIndexOfSubPropertyChainOfAxioms.containsKey(role)) {
							Set<Integer> superRoleSet = subRoleIndexOfSubPropertyChainOfAxioms.get(role);
							for(Integer superRoleInteger : superRoleSet) {
								currentVisitedRoles.offer(superRoleInteger);
							}
						}
					}
				}
			}
		}
	}
	
	
	public void simpleRolePropagation(){
		
		Queue<Integer> currentUnsimpleRoles = new LinkedBlockingQueue<Integer>();
		
		Set<SimpleSubPropertyChainOfAxiom> subPropertyChainOfAxioms = 
				elementIndex.subPropertyChainOfAxioms;
		Set<Integer> transitiveRoleSet = elementIndex.transitiveRoles.keySet();
		
		for(SimpleSubPropertyChainOfAxiom axiom : subPropertyChainOfAxioms) {
			Integer superProperty = axiom.getSuperProperty();
			currentUnsimpleRoles.offer(superProperty);
		}
		
		currentUnsimpleRoles.addAll(transitiveRoleSet);
		
		Map<Integer, Set<SimpleAxiom>> leftIndexOfSubPropertyOfAxiom = 
				elementIndex.leftIndexOfSubPropertyOfAxioms;
		
		while(!currentUnsimpleRoles.isEmpty()) {
			// do propagation.
			
			Integer anUnsimpleRole = currentUnsimpleRoles.poll();
			
			if(!unsimpleRoleSet.contains(anUnsimpleRole)) {
				// this role hasn't been visited,
				unsimpleRoleSet.add(anUnsimpleRole);
				
				// check subPropertyOf axioms.
				if(leftIndexOfSubPropertyOfAxiom.containsKey(anUnsimpleRole)) {
					Set<SimpleAxiom> subPropertyOfAxioms = leftIndexOfSubPropertyOfAxiom.get(anUnsimpleRole);
					for(SimpleAxiom axiom : subPropertyOfAxioms) {
						
						SimpleSubObjectPropertyOfAxiom subPropertyOfAxiom = 
								(SimpleSubObjectPropertyOfAxiom) axiom;
						Integer superRole = subPropertyOfAxiom.getSuperProperty();
						currentUnsimpleRoles.offer(superRole);
					}
				}
			}
		}	
	}
	
	public void simpleConceptPropagation(){
		
		Queue<Integer> currentUnsimpleConcepts = new LinkedBlockingQueue<Integer>();
		
		Set<SimpleAxiom> intersectionOfAxioms = 
				elementIndex.intersectionOfAxiomSet;
		
		for(SimpleAxiom axiom : intersectionOfAxioms) {
			SimpleObjectIntersectionOfAxiom intersectionOfAxiom = 
					(SimpleObjectIntersectionOfAxiom) axiom;
			
			Integer superConceptInteger = intersectionOfAxiom.getSuperConcept();
			
			currentUnsimpleConcepts.offer(superConceptInteger);
		}
		
		Map<Integer, Set<SimpleAxiom>> leftIndexOfSubClassOfAxiom = 
				elementIndex.leftIndexOfSubClassOfAxiom;
		Map<Integer, Set<SimpleAxiom>> leftIndexOfAllValuesFromAxioms = 
				elementIndex.leftIndexOfAllValuesFromAxiom;
		Map<Integer, Set<SimpleAxiom>> leftIndexOfSomeValuesFromAxioms = 
				elementIndex.leftIndexOfSomeValuesFromAxiom;
		
		while(!currentUnsimpleConcepts.isEmpty()) {
			// do propagation.
			
			Integer anUnsimpleConcept = currentUnsimpleConcepts.poll();
			
			if(!unsimpleConceptSet.contains(anUnsimpleConcept)) {
				// this concept hasn't been visited,
				unsimpleConceptSet.add(anUnsimpleConcept);
				
				// check subClassOf axioms.
				if(leftIndexOfSubClassOfAxiom.containsKey(anUnsimpleConcept)) {
					Set<SimpleAxiom> subClassOfAxioms = leftIndexOfSubClassOfAxiom.get(anUnsimpleConcept);
					for(SimpleAxiom axiom : subClassOfAxioms) {
						
						SimpleSubClassOfAxiom subClassOfAxiom = 
								(SimpleSubClassOfAxiom) axiom;
						Integer superConceptInteger = subClassOfAxiom.getSuperConcept();	
						currentUnsimpleConcepts.offer(superConceptInteger);
					}
				}
				
				
				// check allValuesFrom axioms.
				if(leftIndexOfAllValuesFromAxioms.containsKey(anUnsimpleConcept)) {
					Set<SimpleAxiom> allValuesFromOfAxioms = leftIndexOfAllValuesFromAxioms.get(anUnsimpleConcept);
					for(SimpleAxiom axiom : allValuesFromOfAxioms) {
				
						SimpleLeftExistentialAxiom allValuesFromAxiom = 
								(SimpleLeftExistentialAxiom) axiom;
						
						Integer superConceptInteger = allValuesFromAxiom.getSuperConcept();
						currentUnsimpleConcepts.offer(superConceptInteger);
					}
				}
				
				// check someValuesFrom axioms.
				if(leftIndexOfSomeValuesFromAxioms.containsKey(anUnsimpleConcept)) {
					Set<SimpleAxiom> someValuesFromOfAxioms = leftIndexOfSomeValuesFromAxioms.get(anUnsimpleConcept);
					for(SimpleAxiom axiom : someValuesFromOfAxioms) {
				
						SimpleSomeValuesFromAxiom someValuesFromAxiom = 
								(SimpleSomeValuesFromAxiom) axiom;
						
						Integer superConceptInteger = someValuesFromAxiom.getSuperConcept();
						currentUnsimpleConcepts.offer(superConceptInteger);
					}
				}
			}
		}	
	}
	
	

}
