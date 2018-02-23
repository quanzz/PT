package cn.edu.seu.kse.project.ontology.owl;

import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
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

public class OWLAxiomVisitorTemplate implements OWLAxiomVisitor{


	
	//-------------- TBox --------------//

	@Override
	public void visit(OWLSubClassOfAxiom arg0) {
		
	}
	
	
	/**
	 * subclassOf(intersectionOf(A,B), bottom)
	 */
	@Override
	public void visit(OWLDisjointClassesAxiom arg0) {

					
	}
	
	/**
	 * subClassOf(Top, allValusefrom(inverseOf(R),A))
	 */
	@Override
	public void visit(OWLObjectPropertyDomainAxiom arg0) {

			
	}
	
	/**
	 * subClassOf(Top, allValusefrom(R,A))
	 */
	@Override
	public void visit(OWLObjectPropertyRangeAxiom arg0) {
		
	
		
	}
	
	@Override
	public void visit(OWLDisjointUnionAxiom arg0) {
		
		
	
	}
	
	@Override
	public void visit(OWLEquivalentClassesAxiom arg0) {
		
		
		
	}
	

	
	//-------------- RBox --------------//
	
	/**
	 * AsymmetricObjectProperty( a:parentOf )
	 */
	@Override
	public void visit(OWLAsymmetricObjectPropertyAxiom arg0) {
		
		
		
	}
	
	@Override
	public void visit(OWLSymmetricObjectPropertyAxiom arg0) {

	
		
	}

	@Override
	public void visit(OWLReflexiveObjectPropertyAxiom arg0) {

		
		
	}
	
	@Override
	public void visit(OWLIrreflexiveObjectPropertyAxiom arg0) {

		
		
	}

	@Override
	public void visit(OWLEquivalentObjectPropertiesAxiom arg0) {		
		
	
	}
	
	@Override
	public void visit(OWLSubObjectPropertyOfAxiom arg0) {

		
		
	}
	
	@Override
	public void visit(OWLDisjointObjectPropertiesAxiom arg0) {
	
	
	}
	
	@Override
	public void visit(OWLFunctionalObjectPropertyAxiom arg0) {
		
		
		
	}
	
	@Override
	public void visit(OWLInverseFunctionalObjectPropertyAxiom arg0) {
		
	}
		
	@Override
	public void visit(OWLTransitiveObjectPropertyAxiom arg0) {

		
		
	}

	@Override
	public void visit(OWLSubPropertyChainOfAxiom arg0) {

	
		
	}
	
	@Override
	public void visit(OWLInverseObjectPropertiesAxiom arg0) {

		
		
	}
	
	
	//-------------- ABox --------------//
	
	/**
	 * NegativeObjectPropertyAssertion( a:hasSon a:Peter a:Meg )   
	 * Meg is not a son of Peter.
	 */
	@Override
	public void visit(OWLNegativeObjectPropertyAssertionAxiom arg0) {}		

	@Override
	public void visit(OWLDifferentIndividualsAxiom arg0) {}
		
	@Override
	public void visit(OWLObjectPropertyAssertionAxiom arg0) {}
	
	@Override
	public void visit(OWLClassAssertionAxiom arg0) {}
	
	@Override
	public void visit(OWLSameIndividualAxiom arg0) {}
	
	
	
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
