package cn.edu.seu.kse.project.ontology.channel.connector;

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

public class SimpleOntologyStatisticer {
	
	private OWLSimpleConnector connector;
	private OWLSimpleIndex index;
	
	private int numOfAuxiliaryClasses = 0;
	private int numOfAuxiliaryProperties = 0;
	
	private int numOfAllClasses = 0;
	private int numOfAllProperties = 0;
	private int numOfAllIndividuals = 0;
	
	private SimpleAxiomStatisticVisitor simpleAxiomStatisticVisitor;
	
	public SimpleOntologyStatisticer(OWLSimpleConnector connector) {
		this.connector = connector;
		this.index = this.connector.index;
		this.simpleAxiomStatisticVisitor = new SimpleAxiomStatisticVisitor();
	}
	
	private void statistic(){
		numOfAuxiliaryClasses = index.auxiliaryClassMap.keySet().size();
		numOfAuxiliaryProperties = index.auxiliaryObjectPropertyMap.keySet().size();
		
		numOfAllClasses = index.classMap.keySet().size();
		numOfAllProperties = index.propertyMap.keySet().size();
		numOfAllIndividuals = index.individualMap.keySet().size();
		
		for(SimpleAxiom axiom : index.simpleAxiomSet) {
			axiom.accept(simpleAxiomStatisticVisitor);
		}
	}
	
	public void printReport(){
		statistic();
		
		Tool.pl("Statistics:");
		Tool.pl("------------------------------");
		Tool.pl("numOfAuxiliaryClasses: " + numOfAuxiliaryClasses);
		Tool.pl("numOfAuxiliaryProperties: " + numOfAuxiliaryProperties);
		Tool.pl("");
		Tool.pl("numOfAllClasses: " + numOfAllClasses);
		Tool.pl("numOfAllProperties: " + numOfAllProperties);
		Tool.pl("numOfAllIndividuals: " + numOfAllIndividuals);
		Tool.pl("");
		Tool.pl("TBox");
		Tool.pl("numOfSimpleSubClassOfAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleSubClassOfAxiom);
		Tool.pl("numOfSimpleLeftExistentialAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleLeftExistentialAxiom);
		Tool.pl("numOfSimpleObjectIntersectionOfAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleObjectIntersectionOfAxiom);
		Tool.pl("numOfSimpleSomeValuesFromAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleSomeValuesFromAxiom);
		Tool.pl("RBox");
		Tool.pl("numOfSimpleObjectSubPropertyOfAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleObjectSubPropertyOfAxiom);
		Tool.pl("numOfSimpleSubPropertyChainOfAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleSubPropertyChainOfAxiom);
		Tool.pl("numOfSimpleTransitiveObjectPropertyAxiom: " + simpleAxiomStatisticVisitor.numOfSimpleTransitiveObjectPropertyAxiom);
		Tool.pl("ABox");
		Tool.pl("numOfSimpleClassAssertion: " + simpleAxiomStatisticVisitor.numOfSimpleClassAssertion);
		Tool.pl("numOfSimpleObjectPropertyAssertion: " + simpleAxiomStatisticVisitor.numOfSimpleObjectPropertyAssertion);
		Tool.pl("------------------------------");
	}
	
	class SimpleAxiomStatisticVisitor implements SimpleAxiomVisitor {
		
		private int numOfSimpleLeftExistentialAxiom = 0;
		private int numOfSimpleObjectIntersectionOfAxiom = 0;
		private int numOfSimpleSubClassOfAxiom = 0;
		private int numOfSimpleObjectSubPropertyOfAxiom = 0;
		private int numOfSimpleSomeValuesFromAxiom = 0;
		private int numOfSimpleSubPropertyChainOfAxiom = 0;
		private int numOfSimpleTransitiveObjectPropertyAxiom = 0;
		private int numOfSimpleClassAssertion = 0;
		private int numOfSimpleObjectPropertyAssertion = 0;

		@Override
		public void visit(SimpleLeftExistentialAxiom arg) {
			numOfSimpleLeftExistentialAxiom ++;		
		}

		@Override
		public void visit(SimpleObjectIntersectionOfAxiom arg) {
			
			numOfSimpleObjectIntersectionOfAxiom++;
		}

		@Override
		public void visit(SimpleSubClassOfAxiom arg) {
			numOfSimpleSubClassOfAxiom++;
			
		}

		@Override
		public void visit(SimpleSubObjectPropertyOfAxiom arg) {
			numOfSimpleObjectSubPropertyOfAxiom++;
			
		}

		@Override
		public void visit(SimpleSomeValuesFromAxiom arg) {
			numOfSimpleSomeValuesFromAxiom++;
			
		}

		@Override
		public void visit(SimpleSubPropertyChainOfAxiom arg) {
			
			numOfSimpleSubPropertyChainOfAxiom++;
		}

		@Override
		public void visit(SimpleTransitiveObjectPropertyAxiom arg) {
			numOfSimpleTransitiveObjectPropertyAxiom++;
			
		}

		@Override
		public void visit(SimpleClassAssertion arg) {
			numOfSimpleClassAssertion++;
			
		}

		@Override
		public void visit(SimpleObjectPropertyAssertion arg) {
			numOfSimpleObjectPropertyAssertion++;
			
		}
		
	}

}
