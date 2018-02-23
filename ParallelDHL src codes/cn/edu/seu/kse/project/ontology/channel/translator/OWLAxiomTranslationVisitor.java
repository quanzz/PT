package cn.edu.seu.kse.project.ontology.channel.translator;

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

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.abox.OWLClassAssertionAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.abox.OWLObjectPropertyAssertionAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.rbox.OWLEquivalentObjectPropertiesAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.rbox.OWLInverseObjectPropertiesAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.rbox.OWLSubObjectPropertyOfAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.rbox.OWLSubPropertyChainOfAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.rbox.OWLTransitiveObjectPropertyAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLDisjointClassesAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLDisjointUnionAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLEquivalentClassesAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLObjectPropertyDomainAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLObjectPropertyRangeAxiomTranslator;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLSubClassOfAxiomTranslator;

/**
 * This class is an OWLAxiomVisitor implementation. <br/>
 * It visits each axioms and translate them into simple forms. <br/>
 * 
 * @author Zhangquan Zhou
 * 
 * @time 2016-3-31
 *
 */
public class OWLAxiomTranslationVisitor implements OWLAxiomVisitor {

	// TBox
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	private OWLDisjointClassesAxiomTranslator disjointClassesAxiomTranslator;
	private OWLObjectPropertyDomainAxiomTranslator objectPropertyDomainAxiomTranslator;
	private OWLObjectPropertyRangeAxiomTranslator objectPropertyRangeAxiomTranslator;
	private OWLDisjointUnionAxiomTranslator disjointUnionAxiomTranslator;
	private OWLEquivalentClassesAxiomTranslator equivalentClassesAxiomTranslator;
	
	// RBox
	private OWLEquivalentObjectPropertiesAxiomTranslator equivalentObjectPropertiesAxiomTranslator;
	private OWLSubObjectPropertyOfAxiomTranslator subObjectPropertyOfAxiomTranslator;
	private OWLTransitiveObjectPropertyAxiomTranslator transitiveObjectPropertyAxiomTranslator;
	private OWLSubPropertyChainOfAxiomTranslator subPropertyChainOfAxiomTranslator;
	private OWLInverseObjectPropertiesAxiomTranslator inverseObjectPropertiesAxiomTranslator;
	
	
	// ABox
	private OWLObjectPropertyAssertionAxiomTranslator objectPropertyAssertionAxiomTranslator;
	private OWLClassAssertionAxiomTranslator classAssertionAxiomTranslator;
	
	
	public OWLAxiomTranslationVisitor(OWLSimpleConnector connector) {
		
		super();

		/**
		 * TBox
		 */
		this.subClassOfAxiomTranslator = 
				new OWLSubClassOfAxiomTranslator(connector);
		
		this.disjointClassesAxiomTranslator = 
				new OWLDisjointClassesAxiomTranslator(connector, subClassOfAxiomTranslator);
		
		this.equivalentClassesAxiomTranslator = 
				new OWLEquivalentClassesAxiomTranslator(connector, subClassOfAxiomTranslator);
		
		this.disjointUnionAxiomTranslator = 
				new OWLDisjointUnionAxiomTranslator(connector,
						disjointClassesAxiomTranslator, equivalentClassesAxiomTranslator);
		
		this.objectPropertyDomainAxiomTranslator = 
				new OWLObjectPropertyDomainAxiomTranslator(connector, subClassOfAxiomTranslator);
		
		this.objectPropertyRangeAxiomTranslator = 
				new OWLObjectPropertyRangeAxiomTranslator(connector, subClassOfAxiomTranslator);
				
		
		/**
		 * RBox
		 */
		
		this.subObjectPropertyOfAxiomTranslator = 
				new OWLSubObjectPropertyOfAxiomTranslator(connector);
		
		this.equivalentObjectPropertiesAxiomTranslator = 
				new OWLEquivalentObjectPropertiesAxiomTranslator(connector, subObjectPropertyOfAxiomTranslator);	
	
		this.inverseObjectPropertiesAxiomTranslator = 
				new OWLInverseObjectPropertiesAxiomTranslator(connector, subObjectPropertyOfAxiomTranslator);
		
		this.transitiveObjectPropertyAxiomTranslator = 
				new OWLTransitiveObjectPropertyAxiomTranslator(connector);
	
		this.subPropertyChainOfAxiomTranslator = 
				new OWLSubPropertyChainOfAxiomTranslator(connector);
	
		
		/**
		 * ABox
		 */
		this.objectPropertyAssertionAxiomTranslator = 
				new OWLObjectPropertyAssertionAxiomTranslator(connector);
		
		this.classAssertionAxiomTranslator = 
				new OWLClassAssertionAxiomTranslator(connector);
	}
	
	
	
	
	//-------------- TBox --------------//

	@Override
	public void visit(OWLSubClassOfAxiom arg0) {
		this.subClassOfAxiomTranslator.translate(arg0);
	}
	
	
	/**
	 * subclassOf(intersectionOf(A,B), bottom)
	 */
	@Override
	public void visit(OWLDisjointClassesAxiom arg0) {
		this.disjointClassesAxiomTranslator.translate(arg0);
	}
	
	/**
	 * subClassOf(Top, allValusefrom(inverseOf(R),A))
	 */
	@Override
	public void visit(OWLObjectPropertyDomainAxiom arg0) {
		this.objectPropertyDomainAxiomTranslator.translate(arg0);
	}
	
	/**
	 * subClassOf(Top, allValusefrom(R,A))
	 */
	@Override
	public void visit(OWLObjectPropertyRangeAxiom arg0) {
		this.objectPropertyRangeAxiomTranslator.translate(arg0);
	}
	
	@Override
	public void visit(OWLDisjointUnionAxiom arg0) {
		this.disjointUnionAxiomTranslator.translate(arg0);
	}
	
	@Override
	public void visit(OWLEquivalentClassesAxiom arg0) {
		this.equivalentClassesAxiomTranslator.translate(arg0);
	}
	

	
	//-------------- RBox --------------//
	
	@Override
	public void visit(OWLEquivalentObjectPropertiesAxiom arg0) {		
		this.equivalentObjectPropertiesAxiomTranslator.translate(arg0);
	}
	
	@Override
	public void visit(OWLSubObjectPropertyOfAxiom arg0) {
		this.subObjectPropertyOfAxiomTranslator.translate(arg0);	
	}
	
	@Override
	public void visit(OWLTransitiveObjectPropertyAxiom arg0) {
		this.transitiveObjectPropertyAxiomTranslator.translate(arg0);
	}

	@Override
	public void visit(OWLSubPropertyChainOfAxiom arg0) {
		this.subPropertyChainOfAxiomTranslator.translate(arg0);
	}
	
	@Override
	public void visit(OWLInverseObjectPropertiesAxiom arg0) {
		this.inverseObjectPropertiesAxiomTranslator.translate(arg0);
	}
	
	/**
	 * AsymmetricObjectProperty( a:parentOf )
	 */
	@Override
	public void visit(OWLAsymmetricObjectPropertyAxiom arg0) {}
	
	@Override
	public void visit(OWLSymmetricObjectPropertyAxiom arg0) {}

	@Override
	public void visit(OWLReflexiveObjectPropertyAxiom arg0) {}
	
	@Override
	public void visit(OWLIrreflexiveObjectPropertyAxiom arg0) {}
	
	@Override
	public void visit(OWLDisjointObjectPropertiesAxiom arg0) {}
	
	@Override
	public void visit(OWLFunctionalObjectPropertyAxiom arg0) {}
	
	@Override
	public void visit(OWLInverseFunctionalObjectPropertyAxiom arg0) {}
		
	
	
	
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
	public void visit(OWLObjectPropertyAssertionAxiom arg0) {
		this.objectPropertyAssertionAxiomTranslator.translate(arg0);
		
	}
	
	@Override
	public void visit(OWLClassAssertionAxiom arg0) {
		this.classAssertionAxiomTranslator.translate(arg0);
		
	}
	
	@Override
	public void visit(OWLSameIndividualAxiom arg0) {}
	
	
	
	//-------------- DBox --------------//
	
	@Override
	public void visit(OWLDataPropertyDomainAxiom arg0) {}

	@Override
	public void visit(OWLNegativeDataPropertyAssertionAxiom arg0) {}

	@Override
	public void visit(OWLDisjointDataPropertiesAxiom arg0) {}

	@Override
	public void visit(OWLDataPropertyRangeAxiom arg0) {}

	@Override
	public void visit(OWLFunctionalDataPropertyAxiom arg0) {}

	@Override
	public void visit(OWLEquivalentDataPropertiesAxiom arg0) {}

	@Override
	public void visit(OWLDataPropertyAssertionAxiom arg0) {}

	@Override
	public void visit(OWLSubDataPropertyOfAxiom arg0) {}

	@Override
	public void visit(OWLDatatypeDefinitionAxiom arg0) {}
	

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
