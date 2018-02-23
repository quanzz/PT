package cn.edu.seu.kse.project.ontology.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.toolkit.Tool;

public class OntologyParallelTractabilityDetector {
	
	private SimpleOntology simpleOntology;
	private int counterOfIntersectionOfAxioms = 0;
	private int counterOfUnderSimpleConceptRestriction = 0;
	private int counterOfOutOfSimpleConceptRestriction = 0;
	
	private int counterOfChainAxioms = 0;
	private int counterOfUnderSimpleRoleRestriction = 0;
	private int counterOfOutOfSimpleRoleRestriction = 0;
	
	public OntologyParallelTractabilityDetector(
			SimpleOntology simpleOntology) {
		this.simpleOntology = simpleOntology;
	}
	
	public void detect(){
		OntologyElementDetector elementDetector =
				new OntologyElementDetector(simpleOntology.index());
		
		elementDetector.simpleConceptPropagation();
		elementDetector.simpleRolePropagation();
		
		
		
		Set<Integer> unSimpleConceptSet = 
				elementDetector.getUnsimpleConceptSet();
		
		Set<SimpleAxiom> intersectionOfAxioms = 
				simpleOntology.index().getIntersectionOfAxiomSet();
		counterOfIntersectionOfAxioms = intersectionOfAxioms.size();
		
		for(SimpleAxiom axiom : intersectionOfAxioms) {
			SimpleObjectIntersectionOfAxiom intersectionOfAxiom =
					(SimpleObjectIntersectionOfAxiom) axiom;
			
			Integer subConcept1 = intersectionOfAxiom.getSubFirstConcept();
			Integer subConcept2 = intersectionOfAxiom.getSubSecondConcept();
			
			if(unSimpleConceptSet.contains(subConcept1)
					&& unSimpleConceptSet.contains(subConcept2)) {
				counterOfOutOfSimpleConceptRestriction ++ ;
			} else {
				counterOfUnderSimpleConceptRestriction ++ ;
			}
		}
		
		Set<Integer> unSimpleRoleSet = 
				elementDetector.getUnsimpleRoleSet();
		
		Set<SimpleSubPropertyChainOfAxiom> subPropertyChainOfAxioms = 
				simpleOntology.index().getSubPropertyChainOfAxioms();
		counterOfChainAxioms = subPropertyChainOfAxioms.size();
		
		for (SimpleSubPropertyChainOfAxiom subPropertyChainOfAxiom :
					subPropertyChainOfAxioms) {
			
			List<Integer> subRoles = new ArrayList<Integer>();
			subRoles.add(subPropertyChainOfAxiom.getSubProperty1());
			subRoles.add(subPropertyChainOfAxiom.getSubProperty2());
			
			int unSimpleRoles = 0;
			for(Integer subRole : subRoles) {
				if(unSimpleRoleSet.contains(subRole)) {
					unSimpleRoles ++ ;
				}
			}
			
			if(unSimpleRoles >= 2) {
				counterOfOutOfSimpleRoleRestriction ++ ;
			
			} else {
				counterOfUnderSimpleRoleRestriction ++;
			}
		}
	}
	
	public void printResult(){
		Tool.pl("Simple-concept, Simple-role restrictions detection statistics:");
		Tool.pl("------------------------------");
		Tool.pl("IntersectionOfAxioms: " + counterOfIntersectionOfAxioms);
		Tool.pl("under simple-concept restri.: " + counterOfUnderSimpleConceptRestriction);
		Tool.pl("out of simple-concept restri.: " + counterOfOutOfSimpleConceptRestriction);
		Tool.pl("SubPropertyChainOfAxioms: " + counterOfChainAxioms);
		Tool.pl("under simple-role restri.: " + counterOfUnderSimpleRoleRestriction);
		Tool.pl("out of simple-role restri.: " + counterOfOutOfSimpleRoleRestriction);
		Tool.pl("------------------------------");
	}

}
