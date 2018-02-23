package cn.edu.seu.kse.project.ontology.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomVisitor;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleClassAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectPropertyAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSomeValuesFromAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleTransitiveObjectPropertyAxiom;
import cn.edu.seu.kse.project.toolkit.Tool;

public class SimpleElementIndex {
	
	// indexes used for concepts	
	private Set<Integer> conceptSet;
	
	// indexes used for TBox axioms.
	protected Set<SimpleAxiom> subClassOfAxiomSet;
	protected Map<Integer, Set<SimpleAxiom>> leftIndexOfSubClassOfAxiom;	
	private Map<Integer, Set<SimpleAxiom>> rightIndexOfSubClassOfAxiom;
	private int counterOfSubClassOfAxioms = 0;
	
	private Set<SimpleAxiom> someValuesFromAxiomSet;
	protected Map<Integer, Set<SimpleAxiom>> leftIndexOfSomeValuesFromAxiom;
	private Map<Integer, Set<SimpleAxiom>> rightIndexOfSomeValuesFromAxiom;
	protected Map<Integer, Set<SimpleAxiom>> roleIndexOfSomeValuesFromAxiom;
	private int counterOfSomeValuesFromAxioms = 0;
	
	private Set<SimpleAxiom> allValuesFromAxiomSet;
	protected Map<Integer, Set<SimpleAxiom>> leftIndexOfAllValuesFromAxiom;
	private Map<Integer, Set<SimpleAxiom>> rightIndexOfAllValuesFromAxiom;
	protected Map<Integer, Set<SimpleAxiom>> roleIndexOfAllValuesFromAxiom;
	private int counterOfAllValuesFromAxioms = 0;
	
	protected Set<SimpleAxiom> intersectionOfAxiomSet;
	protected Map<Integer , Set<SimpleAxiom>> leftFirstIndexOfIntersectionOfAxioms;	
	private Map<Integer , Set<SimpleAxiom>> leftSecondIndexOfIntersectionOfAxioms;
	private Map<Integer , Set<SimpleAxiom>> rightIndexOfIntersectionOfAxioms;
	private int counterOfIntersectionOfAxioms = 0;
	
	// indexes used for roles.
	protected Set<Integer> roleSet;
	
	// indexes used for RBox axioms.
	private Set<SimpleAxiom> subPropertyOfAxiomSet;
	protected Map<Integer, Set<SimpleAxiom>> leftIndexOfSubPropertyOfAxioms;
	private Map<Integer, Set<SimpleAxiom>> rightIndexOfSubPropertyOfAxioms;
	private int counterOfSubPropertyOfAxioms = 0;
	
	protected Set<SimpleSubPropertyChainOfAxiom> subPropertyChainOfAxioms;
	protected Map<Integer, Set<Integer>> subRoleIndexOfSubPropertyChainOfAxioms;
	private int counterOfSubPropertyChainOfAxioms = 0;
	protected Map<Integer, Set<SimpleAxiom>> transitiveRoles;
	private int counterOfTransitiveRoles = 0;
	
	private SimpleAdditionVisitor indexVisitorForAddition;
	
	public SimpleElementIndex(){
		
		conceptSet = new HashSet<Integer>();
		
		subClassOfAxiomSet = new HashSet<SimpleAxiom>();
		leftIndexOfSubClassOfAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		rightIndexOfSubClassOfAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		
		someValuesFromAxiomSet = new HashSet<SimpleAxiom>();
		leftIndexOfSomeValuesFromAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		rightIndexOfSomeValuesFromAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		roleIndexOfSomeValuesFromAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		
		allValuesFromAxiomSet = new HashSet<SimpleAxiom>();
		leftIndexOfAllValuesFromAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		rightIndexOfAllValuesFromAxiom = new HashMap<Integer, Set<SimpleAxiom>>();
		roleIndexOfAllValuesFromAxiom = new HashMap<Integer, Set<SimpleAxiom>>();	
		
		intersectionOfAxiomSet = new HashSet<SimpleAxiom>();
		leftFirstIndexOfIntersectionOfAxioms = new HashMap<Integer , Set<SimpleAxiom>>();	
		leftSecondIndexOfIntersectionOfAxioms = new HashMap<Integer , Set<SimpleAxiom>>();	
		rightIndexOfIntersectionOfAxioms = new HashMap<Integer , Set<SimpleAxiom>>();	

		roleSet = new HashSet<Integer>();
	
		subPropertyOfAxiomSet = new HashSet<SimpleAxiom>();
		leftIndexOfSubPropertyOfAxioms = new HashMap<Integer, Set<SimpleAxiom>>();
		rightIndexOfSubPropertyOfAxioms = new HashMap<Integer, Set<SimpleAxiom>>();
		
		subPropertyChainOfAxioms = new HashSet<SimpleSubPropertyChainOfAxiom>();
		subRoleIndexOfSubPropertyChainOfAxioms = new HashMap<Integer, Set<Integer>>();
		
		transitiveRoles = new HashMap<Integer, Set<SimpleAxiom>>();
		
		indexVisitorForAddition = new SimpleAdditionVisitor();
	}

	public void addAxiom(SimpleAxiom simpleAxiom){
		simpleAxiom.accept(indexVisitorForAddition);
	}
	
	public void printInfo(){
		
		Tool.pl("Simple Ontology Statistics:");
		Tool.pl("------------------------------");
		Tool.pl("Concepts: " + conceptSet.size());
		Tool.pl("role: " + roleSet.size());
		Tool.pl("transitive roles: " + counterOfTransitiveRoles);
		Tool.pl("");
		Tool.pl("SubClassOfAxioms: " + counterOfSubClassOfAxioms);
		Tool.pl("AllValuesFromAxioms: " + counterOfAllValuesFromAxioms);
		Tool.pl("SomeValuesFromAxioms: " + counterOfSomeValuesFromAxioms);
		Tool.pl("IntersectionOfAxioms: " + counterOfIntersectionOfAxioms);
		Tool.pl("SubPropertyOfAxioms: " + counterOfSubPropertyOfAxioms);
		Tool.pl("SubPropertyChainOfAxioms: " + counterOfSubPropertyChainOfAxioms);
		Tool.pl("------------------------------");
		
	}
	
	class SimpleAdditionVisitor implements SimpleAxiomVisitor {

		@Override
		public void visit(SimpleLeftExistentialAxiom arg) {
			
			allValuesFromAxiomSet.add(arg);
			
			counterOfAllValuesFromAxioms ++ ;

			addElement(arg.getSubConcept(), conceptSet);
			addElement(arg.getSuperConcept(), conceptSet);
			addElement(arg.getRole(), roleSet);
			
			addElement(arg.getSubConcept(), arg, leftIndexOfAllValuesFromAxiom);
			addElement(arg.getSuperConcept(), arg, rightIndexOfAllValuesFromAxiom);
			addElement(arg.getRole(), arg, roleIndexOfAllValuesFromAxiom);
			
		}

		@Override
		public void visit(SimpleObjectIntersectionOfAxiom arg) {
			
			intersectionOfAxiomSet.add(arg);
			
			counterOfIntersectionOfAxioms ++ ;

			addElement(arg.getSubFirstConcept(), conceptSet);
			addElement(arg.getSubSecondConcept(), conceptSet);
			addElement(arg.getSuperConcept(), conceptSet);
			
			addElement(arg.getSubFirstConcept(), arg, leftFirstIndexOfIntersectionOfAxioms);
			addElement(arg.getSubSecondConcept(), arg, leftSecondIndexOfIntersectionOfAxioms);
			addElement(arg.getSuperConcept(), arg, rightIndexOfIntersectionOfAxioms);
			
		}

		@Override
		public void visit(SimpleSubClassOfAxiom arg) {
			
			subClassOfAxiomSet.add(arg);
			
			counterOfSubClassOfAxioms ++ ;
			
			addElement(arg.getSubConcept(), conceptSet);
			addElement(arg.getSuperConcept(), conceptSet);
			
			addElement(arg.getSubConcept(), arg, leftIndexOfSubClassOfAxiom);
			addElement(arg.getSuperConcept(), arg, rightIndexOfSubClassOfAxiom);
			
		}

		@Override
		public void visit(SimpleSubObjectPropertyOfAxiom arg) {
			
			subPropertyOfAxiomSet.add(arg);
			
			counterOfSubPropertyOfAxioms ++ ;
			
			addElement(arg.getSubProperty(), roleSet);
			addElement(arg.getSuperProperty(), roleSet);

			addElement(arg.getSubProperty(), arg, leftIndexOfSubPropertyOfAxioms);
			addElement(arg.getSuperProperty(), arg, rightIndexOfSubPropertyOfAxioms);
			
		}

		@Override
		public void visit(SimpleSomeValuesFromAxiom arg) {
			
			someValuesFromAxiomSet.add(arg);
			
			counterOfSomeValuesFromAxioms ++ ;
			
			addElement(arg.getSubConcept(), conceptSet);
			addElement(arg.getSuperConcept(), conceptSet);
			addElement(arg.getRole(), roleSet);

			addElement(arg.getSubConcept(), arg, leftIndexOfSomeValuesFromAxiom);
			addElement(arg.getSuperConcept(), arg, rightIndexOfSomeValuesFromAxiom);
			addElement(arg.getRole(), arg, roleIndexOfSomeValuesFromAxiom);
			
		}

		@Override
		public void visit(SimpleSubPropertyChainOfAxiom arg) {
			
			counterOfSubPropertyChainOfAxioms ++ ;
			Integer superRole = arg.getSuperProperty();
			
			List<Integer> chain = new ArrayList<Integer>();
			chain.add(arg.getSubProperty1());
			chain.add(arg.getSubProperty2());
			
			for(Integer subRoleInteger : chain) {
				addElement(subRoleInteger, roleSet);
				addElement(subRoleInteger, superRole, 
						subRoleIndexOfSubPropertyChainOfAxioms);
			}
			addElement(arg.getSuperProperty(), roleSet);

			subPropertyChainOfAxioms.add(arg);
			
		}

		@Override
		public void visit(SimpleTransitiveObjectPropertyAxiom arg) {
			
			counterOfTransitiveRoles ++ ;

			addElement(arg.getProperty(), roleSet);
			addElement(arg.getProperty(), arg, transitiveRoles);
			
		}
		
		public void addElement(
				Integer key, 
				SimpleAxiom axiom, 
				Map<Integer, Set<SimpleAxiom>> axiomMap) {
			
			if(axiomMap.containsKey(key)) {
				Set<SimpleAxiom> axiomSet = axiomMap.get(key);
				axiomSet.add(axiom);
			} else {
				Set<SimpleAxiom> axiomSet = new HashSet<SimpleAxiom>();
				axiomSet.add(axiom);
				axiomMap.put(key, axiomSet);
			}
		}
		
		public void addElement(
				Integer key, 
				Integer value, 
				Map<Integer, Set<Integer>> map) {
			
			if(map.containsKey(key)) {
				Set<Integer> valueSet = map.get(key);
				valueSet.add(value);
			} else {
				Set<Integer> valueSet = new HashSet<Integer>();
				valueSet.add(value);
				map.put(key, valueSet);
			}
		}
		
		public void addElement(Integer element, Set<Integer> elementSet){
			elementSet.add(element);
		}

		@Override
		public void visit(SimpleClassAssertion arg) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(SimpleObjectPropertyAssertion arg) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public Set<SimpleAxiom> getIntersectionOfAxiomSet() {
		return intersectionOfAxiomSet;
	}
	
	public Set<SimpleSubPropertyChainOfAxiom> getSubPropertyChainOfAxioms() {
		return subPropertyChainOfAxioms;
	}
	
	
}
