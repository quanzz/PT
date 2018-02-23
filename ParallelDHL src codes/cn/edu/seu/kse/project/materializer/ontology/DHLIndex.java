package cn.edu.seu.kse.project.materializer.ontology;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.toolkit.ConcurrentHashSet;

public class DHLIndex {

	// indexes used for TBox axioms.
	
	protected Set<DHLAxiom> subClassOfAxiomSet;
	protected Map<Integer, Set<DHLAxiom>> leftIndexOfSubClassOfAxioms;
	
	protected Set<DHLAxiom> leftExistentialAxiomSet;
	protected Map<Integer, Set<DHLAxiom>> subClassIndexOfLeftExistentialAxiom;
	protected Map<Integer, Set<DHLAxiom>> subRoleIndexOfLeftExistentialAxiom;

	protected Set<DHLAxiom> intersectionOfAxiomSet;
	protected Map<Integer, Set<DHLAxiom>> leftFirstIndexOfIntersectionOfAxioms;
	protected Map<Integer, Set<DHLAxiom>> leftSecondIndexOfIntersectionOfAxioms;

	
	// indexes used for RBox axioms.
	
	protected Set<DHLAxiom> subPropertyOfAxiomSet;
	protected Map<Integer, Set<DHLAxiom>> leftIndexOfSubPropertyOfAxioms;

	protected Set<DHLAxiom> subPropertyChainOfAxioms;
	protected Map<Integer, Set<DHLAxiom>> leftFirstIndexOfSubPropertyChainOfAxioms;
	protected Map<Integer, Set<DHLAxiom>> leftSecondIndexOfSubPropertyChainOfAxioms;
	
	protected Set<Integer> transitiveRoles;

	protected Map<Integer, Integer> inversePropertyMap;
	
	
	// indexes used for ABox axioms.
	protected Set<DHLAxiom> classAssertionSet;
	protected Map<Integer, Map<Integer, Set<DHLAxiom>>> classMemberIndexOfClassAssertions;
	
	protected Set<DHLAxiom> propertyAssertionSet;
	protected Map<Integer, Map<Integer, Map<Integer, Set<DHLAxiom>>>> roleFirstSecondIndexOfPropertyAssertions;
	protected Map<Integer, Map<Integer, Map<Integer, Set<DHLAxiom>>>> roleSecondFirstIndexOfPropertyAssertions;
	
	public DHLIndex(){
		
		// TBox
		
		subClassOfAxiomSet = new ConcurrentHashSet<DHLAxiom>();
		leftIndexOfSubClassOfAxioms = new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
		
		leftExistentialAxiomSet = new ConcurrentHashSet<DHLAxiom>() ;
		subClassIndexOfLeftExistentialAxiom = new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
		subRoleIndexOfLeftExistentialAxiom = new ConcurrentHashMap<Integer, Set<DHLAxiom>>();

		intersectionOfAxiomSet = new ConcurrentHashSet<DHLAxiom>();
		leftFirstIndexOfIntersectionOfAxioms = 
				new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
		leftSecondIndexOfIntersectionOfAxioms =
				new ConcurrentHashMap<Integer, Set<DHLAxiom>>();;

		
		// RBox
		
		subPropertyOfAxiomSet = new ConcurrentHashSet<DHLAxiom>();
		leftIndexOfSubPropertyOfAxioms = 
				new ConcurrentHashMap<Integer, Set<DHLAxiom>>();

		subPropertyChainOfAxioms = new ConcurrentHashSet<DHLAxiom>();
		leftFirstIndexOfSubPropertyChainOfAxioms =
				new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
		leftSecondIndexOfSubPropertyChainOfAxioms = 
				new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
		
		transitiveRoles = new ConcurrentHashSet<Integer>();
		inversePropertyMap = new ConcurrentHashMap<Integer, Integer>();
		
		
		// indexes used for ABox axioms.
		classAssertionSet = new ConcurrentHashSet<DHLAxiom>();
		classMemberIndexOfClassAssertions = 
				new ConcurrentHashMap<Integer, Map<Integer, Set<DHLAxiom>>>();
		
		propertyAssertionSet = new ConcurrentHashSet<DHLAxiom>();
		roleFirstSecondIndexOfPropertyAssertions = 
				new ConcurrentHashMap<Integer, Map<Integer, Map<Integer, Set<DHLAxiom>>>>();
		roleSecondFirstIndexOfPropertyAssertions = 
				new ConcurrentHashMap<Integer, Map<Integer, Map<Integer, Set<DHLAxiom>>>>();
	}

}
