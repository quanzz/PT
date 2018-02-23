package cn.edu.seu.kse.project.ontology.owl.statistic;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.SWRLRule;

import cn.edu.seu.kse.project.ontology.exception.OntoException;
import cn.edu.seu.kse.project.toolkit.Tool;



public class OWLOntologyStatisticer {
	
	private OWLOntology ontology;
	
	private int atomicConcepts = 0;
	private int objectProperties = 0;
	private int dataProperties = 0;
	private int individuals = 0;
	
	private OWLOntologyStatisticerVisitor statisticerVisitor = null;
	
	public OWLOntologyStatisticer(OWLOntology ontology){
		this.ontology = ontology;
	}
	
	private void statistic() throws OntoException{
		if(ontology == null) {
			throw new OntoException("No ontology loaded.");
		}
		
		atomicConcepts = ontology.getClassesInSignature().size();
		objectProperties = ontology.getObjectPropertiesInSignature().size();
		dataProperties = ontology.getDataPropertiesInSignature().size();
		individuals = ontology.getIndividualsInSignature().size();
		
		statisticerVisitor =
				new OWLOntologyStatisticerVisitor();
		
		Set<OWLAxiom> axioms = ontology.getAxioms();
		for(OWLAxiom axiom : axioms) {
			axiom.accept(statisticerVisitor);
		}
		
	}
	
	public void printReport(){
		try {
			statistic();
		} catch (OntoException e) {
			e.printStackTrace();
		}
		
		Tool.pl("Statistics:");
		Tool.pl("------------------------------");
		Tool.pl("Atomic concepts: " + atomicConcepts);
		Tool.pl("Object properties: " + objectProperties);
		Tool.pl("Data properties: " + dataProperties);
		Tool.pl("Individuals: " + individuals);
		Tool.pl("TBox");
		Tool.pl("SubClassOfAxiom: " + statisticerVisitor.SubClassOfAxiom);
		Tool.pl("DisjointClassesAxiom: " + statisticerVisitor.DisjointClassesAxiom);
		Tool.pl("ObjectPropertyDomainAxiom: " + statisticerVisitor.ObjectPropertyDomainAxiom);
		Tool.pl("ObjectPropertyRangeAxiom: " + statisticerVisitor.ObjectPropertyRangeAxiom);
		Tool.pl("DisjointUnionAxiom: " + statisticerVisitor.DisjointUnionAxiom);
		Tool.pl("EquivalentClassesAxiom: " + statisticerVisitor.EquivalentClassesAxiom);
		Tool.pl("RBox");
		Tool.pl("AsymmetricObjectPropertyAxiom: " + statisticerVisitor.AsymmetricObjectPropertyAxiom);
		Tool.pl("SymmetricObjectPropertyAxiom: " + statisticerVisitor.SymmetricObjectPropertyAxiom);
		Tool.pl("ReflexiveObjectPropertyAxiom: " + statisticerVisitor.ReflexiveObjectPropertyAxiom);
		Tool.pl("IrreflexiveObjectPropertyAxiom: " + statisticerVisitor.IrreflexiveObjectPropertyAxiom);
		Tool.pl("EquivalentObjectPropertiesAxiom: " + statisticerVisitor.EquivalentObjectPropertiesAxiom);
		Tool.pl("SubObjectPropertyOfAxiom: " + statisticerVisitor.SubObjectPropertyOfAxiom);
		Tool.pl("DisjointObjectPropertiesAxiom: " + statisticerVisitor.DisjointObjectPropertiesAxiom);
		Tool.pl("FunctionalObjectPropertyAxiom: " + statisticerVisitor.FunctionalObjectPropertyAxiom);
		Tool.pl("InverseFunctionalObjectPropertyAxiom: " + statisticerVisitor.InverseFunctionalObjectPropertyAxiom);
		Tool.pl("SubPropertyChainOfAxiom: " + statisticerVisitor.SubPropertyChainOfAxiom);
		Tool.pl("InverseObjectPropertiesAxiom: " + statisticerVisitor.InverseObjectPropertiesAxiom);
		Tool.pl("TransitiveObjectPropertyAxiom: " + statisticerVisitor.TransitiveObjectPropertyAxiom);
		Tool.pl("ABox");
		Tool.pl("NegativeObjectPropertyAssertionAxiom: " + statisticerVisitor.NegativeObjectPropertyAssertionAxiom);
		Tool.pl("DifferentIndividualsAxiom: " + statisticerVisitor.DifferentIndividualsAxiom);
		Tool.pl("ObjectPropertyAssertionAxiom: " + statisticerVisitor.ObjectPropertyAssertionAxiom);
		Tool.pl("ClassAssertionAxiom: " + statisticerVisitor.ClassAssertionAxiom);
		Tool.pl("SameIndividualAxiom: " + statisticerVisitor.SameIndividualAxiom);
		Tool.pl("------------------------------");
		
	}
	
	
	class OWLOntologyStatisticerVisitor implements OWLAxiomVisitor {

		// TBox
		private int SubClassOfAxiom = 0;
		private int DisjointClassesAxiom = 0;
		private int ObjectPropertyDomainAxiom = 0;
		private int ObjectPropertyRangeAxiom = 0;
		private int DisjointUnionAxiom = 0;
		private int EquivalentClassesAxiom = 0;
		// RBox
		private int AsymmetricObjectPropertyAxiom = 0;
		private int SymmetricObjectPropertyAxiom = 0;
		private int ReflexiveObjectPropertyAxiom = 0;
		private int IrreflexiveObjectPropertyAxiom = 0;
		private int EquivalentObjectPropertiesAxiom = 0;
		private int SubObjectPropertyOfAxiom = 0;
		private int DisjointObjectPropertiesAxiom = 0;
		private int FunctionalObjectPropertyAxiom = 0;
		private int InverseFunctionalObjectPropertyAxiom = 0;
		private int SubPropertyChainOfAxiom = 0;
		private int InverseObjectPropertiesAxiom = 0;
		private int TransitiveObjectPropertyAxiom = 0;
		
		// ABox
		private int NegativeObjectPropertyAssertionAxiom = 0;
		private int DifferentIndividualsAxiom = 0;
		private int ObjectPropertyAssertionAxiom = 0;
		private int ClassAssertionAxiom = 0;
		private int SameIndividualAxiom = 0;
		
		
		//-------------- TBox --------------//

		@Override
		public void visit(OWLSubClassOfAxiom arg0) {
			SubClassOfAxiom++;
		}
		
		
		/**
		 * subclassOf(intersectionOf(A,B), bottom)
		 */
		@Override
		public void visit(OWLDisjointClassesAxiom arg0) {
			DisjointClassesAxiom++;
						
		}
		
		/**
		 * subClassOf(Top, allValusefrom(inverseOf(R),A))
		 */
		@Override
		public void visit(OWLObjectPropertyDomainAxiom arg0) {
			ObjectPropertyDomainAxiom++;
				
		}
		
		/**
		 * subClassOf(Top, allValusefrom(R,A))
		 */
		@Override
		public void visit(OWLObjectPropertyRangeAxiom arg0) {
			ObjectPropertyRangeAxiom++;
		
			
		}
		
		@Override
		public void visit(OWLDisjointUnionAxiom arg0) {
			DisjointUnionAxiom++;
			
		
		}
		
		@Override
		public void visit(OWLEquivalentClassesAxiom arg0) {
			EquivalentClassesAxiom++;
			
			
		}
		

		
		//-------------- RBox --------------//
		
		/**
		 * AsymmetricObjectProperty( a:parentOf )
		 */
		@Override
		public void visit(OWLAsymmetricObjectPropertyAxiom arg0) {
			AsymmetricObjectPropertyAxiom++;
			
			
		}
		
		@Override
		public void visit(OWLSymmetricObjectPropertyAxiom arg0) {
			SymmetricObjectPropertyAxiom++;
		
			
		}

		@Override
		public void visit(OWLReflexiveObjectPropertyAxiom arg0) {
			ReflexiveObjectPropertyAxiom ++;
			
			
		}
		
		@Override
		public void visit(OWLIrreflexiveObjectPropertyAxiom arg0) {
			IrreflexiveObjectPropertyAxiom++;
			
			
		}

		@Override
		public void visit(OWLEquivalentObjectPropertiesAxiom arg0) {		
			EquivalentObjectPropertiesAxiom++;
		
		}
		
		@Override
		public void visit(OWLSubObjectPropertyOfAxiom arg0) {
			SubObjectPropertyOfAxiom++;
			
			
		}
		
		@Override
		public void visit(OWLDisjointObjectPropertiesAxiom arg0) {
			DisjointObjectPropertiesAxiom++;
		
		}
		
		@Override
		public void visit(OWLFunctionalObjectPropertyAxiom arg0) {
			FunctionalObjectPropertyAxiom++;
			
			
		}
		
		@Override
		public void visit(OWLInverseFunctionalObjectPropertyAxiom arg0) {
			InverseFunctionalObjectPropertyAxiom++;
		}
			
		@Override
		public void visit(OWLTransitiveObjectPropertyAxiom arg0) {
			TransitiveObjectPropertyAxiom++;
			
			
		}

		@Override
		public void visit(OWLSubPropertyChainOfAxiom arg0) {
			SubPropertyChainOfAxiom++;
		
			
		}
		
		@Override
		public void visit(OWLInverseObjectPropertiesAxiom arg0) {
			InverseObjectPropertiesAxiom++;
			
			
		}
		
		
		//-------------- ABox --------------//
		
		/**
		 * NegativeObjectPropertyAssertion( a:hasSon a:Peter a:Meg )   
		 * Meg is not a son of Peter.
		 */
		@Override
		public void visit(OWLNegativeObjectPropertyAssertionAxiom arg0) {
			
			NegativeObjectPropertyAssertionAxiom++;
		}		

		@Override
		public void visit(OWLDifferentIndividualsAxiom arg0) {
			DifferentIndividualsAxiom ++;
		}
			
		@Override
		public void visit(OWLObjectPropertyAssertionAxiom arg0) {
			ObjectPropertyAssertionAxiom++;
		}
		
		@Override
		public void visit(OWLClassAssertionAxiom arg0) {
			ClassAssertionAxiom++;
		}
		
		@Override
		public void visit(OWLSameIndividualAxiom arg0) {
			SameIndividualAxiom++;
		}
		
		
		
		//-------------- DBox --------------//
		
		@Override
		public void visit(OWLDataPropertyDomainAxiom arg0) {
			
		}

		@Override
		public void visit(OWLNegativeDataPropertyAssertionAxiom arg0) {
		
		}

		@Override
		public void visit(OWLDisjointDataPropertiesAxiom arg0) {
			
		}

		@Override
		public void visit(OWLDataPropertyRangeAxiom arg0) {
			
		}

		@Override
		public void visit(OWLFunctionalDataPropertyAxiom arg0) {
			
		}

		@Override
		public void visit(OWLEquivalentDataPropertiesAxiom arg0) {
			
		}

		@Override
		public void visit(OWLDataPropertyAssertionAxiom arg0) {
			
		}

		@Override
		public void visit(OWLSubDataPropertyOfAxiom arg0) {
			
		}

		@Override
		public void visit(OWLDatatypeDefinitionAxiom arg0) {
			
		}
		

		// annotation and others.

		@Override
		public void visit(OWLAnnotationAssertionAxiom arg0) {}

		@Override
		public void visit(OWLSubAnnotationPropertyOfAxiom arg0) {}

		@Override
		public void visit(OWLAnnotationPropertyDomainAxiom arg0) {}

		@Override
		public void visit(OWLAnnotationPropertyRangeAxiom arg0) {}
		
		@Override
		public void visit(OWLDeclarationAxiom arg0) {}

		@Override
		public void visit(OWLHasKeyAxiom arg0) {}

		@Override
		public void visit(SWRLRule arg0) {}
		
	}
	
	
}
