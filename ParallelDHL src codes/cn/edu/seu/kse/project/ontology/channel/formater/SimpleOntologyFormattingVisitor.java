package cn.edu.seu.kse.project.ontology.channel.formater;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.connector.SimpleOWLAllocater;
import cn.edu.seu.kse.project.ontology.owl.OWLOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomVisitor;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleClassAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectPropertyAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSomeValuesFromAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleTransitiveObjectPropertyAxiom;

public class SimpleOntologyFormattingVisitor implements SimpleAxiomVisitor {
	
	private OWLOntologyBase owlOntoBase;
	//private OWLSimpleConnector connector;
	private SimpleOWLAllocater revAllocator;
	private OWLDataFactory datafactory;
	
	public SimpleOntologyFormattingVisitor(
			OWLSimpleConnector connector,
			OWLOntologyBase targetOntoBase) {
		//this.connector = connector;
		this.owlOntoBase = targetOntoBase;
		this.revAllocator = connector.getReverseAllocater();
		this.datafactory = connector.getOWLDataFactory();
	}
	
	// ABox
	
	@Override
	public void visit(SimpleClassAssertion arg) {
		Integer clasS = arg.getConcept();
		Integer member = arg.getMember();
		
		OWLClass owlClass = revAllocator.getOWLClassByInteger(clasS);
		OWLIndividual owlIndividual = revAllocator.getOWLIndividualByInteger(member);
		
		OWLClassAssertionAxiom classAssertion =
				datafactory.getOWLClassAssertionAxiom(
						owlClass, owlIndividual);
		
		owlOntoBase.addAxiom(classAssertion);
	}

	@Override
	public void visit(SimpleObjectPropertyAssertion arg) {
		
		Integer propertyInetger = arg.getProperty();
		Integer subjectInteger = arg.getSubject();
		Integer objectInteger = arg.getObject();
		
		OWLObjectPropertyExpression owlObjectProperty =
				revAllocator.getOWLObjectPropertyExpressionByInteger(propertyInetger);
		OWLIndividual owlSubject = revAllocator.getOWLIndividualByInteger(subjectInteger);
		OWLIndividual owlObject = revAllocator.getOWLIndividualByInteger(objectInteger);
		
		OWLObjectPropertyAssertionAxiom objectPropertyAssertionAxiom =
				datafactory.getOWLObjectPropertyAssertionAxiom(
						owlObjectProperty, owlSubject, owlObject);
		
		owlOntoBase.addAxiom(objectPropertyAssertionAxiom);
	}
	

	@Override
	public void visit(SimpleLeftExistentialAxiom arg) {
		
		Integer role = arg.getRole();
		Integer subClass = arg.getSubConcept();
		Integer superClass = arg.getSuperConcept();
		
		OWLObjectPropertyExpression owlProperty =
				revAllocator.getOWLObjectPropertyExpressionByInteger(role);
		OWLClass owlSubClass = 
				revAllocator.getOWLClassByInteger(subClass);
		OWLClass owlSuperClass = 
				revAllocator.getOWLClassByInteger(superClass);
		
		
		OWLObjectSomeValuesFrom someValuesFrom = 
				datafactory.getOWLObjectSomeValuesFrom(owlProperty, owlSubClass);
		
		OWLSubClassOfAxiom subClassOfAxiom = 
				datafactory.getOWLSubClassOfAxiom(someValuesFrom, owlSuperClass);
		
		owlOntoBase.addAxiom(subClassOfAxiom);
		
	}

	@Override
	public void visit(SimpleObjectIntersectionOfAxiom arg) {
		
		Integer subClass1 = arg.getSubFirstConcept();
		Integer subClass2 = arg.getSubSecondConcept();
		Integer superClass = arg.getSuperConcept();
		
		OWLClass owlSubClass1 = 
				revAllocator.getOWLClassByInteger(subClass1);
		OWLClass owlSubClass2 = 
				revAllocator.getOWLClassByInteger(subClass2);
		OWLClass owlSuperClass = 
				revAllocator.getOWLClassByInteger(superClass);
		
		OWLObjectIntersectionOf intersectionOf = 
				datafactory.getOWLObjectIntersectionOf(owlSubClass1,owlSubClass2);
		
		OWLSubClassOfAxiom subClassOfAxiom = 
				datafactory.getOWLSubClassOfAxiom(intersectionOf, owlSuperClass);
		
		owlOntoBase.addAxiom(subClassOfAxiom);	
		
	}

	@Override
	public void visit(SimpleSubClassOfAxiom arg) {
		Integer subClass = arg.getSubConcept();
		Integer superClass = arg.getSuperConcept();
		
		OWLClass owlSubClass = 
				revAllocator.getOWLClassByInteger(subClass);
		OWLClass owlSuperClass = 
				revAllocator.getOWLClassByInteger(superClass);
		
		OWLSubClassOfAxiom subClassOfAxiom = 
				datafactory.getOWLSubClassOfAxiom(owlSubClass, owlSuperClass);
		
		owlOntoBase.addAxiom(subClassOfAxiom);	
	}

	@Override
	public void visit(SimpleSubObjectPropertyOfAxiom arg) {
		Integer subRole = arg.getSubProperty();
		Integer superRole = arg.getSuperProperty();
		
		OWLObjectPropertyExpression owlSubRole = 
				revAllocator.getOWLObjectPropertyExpressionByInteger(subRole);
		OWLObjectPropertyExpression owlSuperRole = 
				revAllocator.getOWLObjectPropertyExpressionByInteger(superRole);
		
		OWLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom =
				datafactory.getOWLSubObjectPropertyOfAxiom(owlSubRole, owlSuperRole);
		
		owlOntoBase.addAxiom(subObjectPropertyOfAxiom);
	}

	@Override
	public void visit(SimpleSomeValuesFromAxiom arg) {
		
	}

	@Override
	public void visit(SimpleSubPropertyChainOfAxiom arg) {
		Integer subRole1 = arg.getSubProperty1();
		Integer subRole2 = arg.getSubProperty2();
		Integer superRole = arg.getSuperProperty();
		
		OWLObjectPropertyExpression owlSubRole1 = 
				revAllocator.getOWLObjectPropertyExpressionByInteger(subRole1);
		OWLObjectPropertyExpression owlSubRole2 =
				revAllocator.getOWLObjectPropertyExpressionByInteger(subRole2);
		OWLObjectPropertyExpression owlSuperRole = 
				revAllocator.getOWLObjectPropertyExpressionByInteger(superRole);
		
		List<OWLObjectPropertyExpression> chain =
				new ArrayList<OWLObjectPropertyExpression>();
		chain.add(owlSubRole1);
		chain.add(owlSubRole2);
		
		OWLSubPropertyChainOfAxiom chainOfAxiom = 
				datafactory.getOWLSubPropertyChainOfAxiom(chain, owlSuperRole);
		
		owlOntoBase.addAxiom(chainOfAxiom);
	}

	@Override
	public void visit(SimpleTransitiveObjectPropertyAxiom arg) {
		Integer role = arg.getProperty();
		
		OWLObjectPropertyExpression owlRole =
				revAllocator.getOWLObjectPropertyExpressionByInteger(role);
		
		OWLTransitiveObjectPropertyAxiom transitiveObjectPropertyAxiom =
				datafactory.getOWLTransitiveObjectPropertyAxiom(owlRole);
		
		owlOntoBase.addAxiom(transitiveObjectPropertyAxiom);
		
	}

	

}
