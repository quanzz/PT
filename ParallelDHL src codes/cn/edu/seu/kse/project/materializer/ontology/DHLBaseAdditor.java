package cn.edu.seu.kse.project.materializer.ontology;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiomVisitor;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLLeftExistentialAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubClassOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLTransitiveObjectPropertyAxiom;
import cn.edu.seu.kse.project.materializer.ontology.util.AdditionUtil;

public class DHLBaseAdditor implements DHLAxiomVisitor {
	
	
	private DHLIndex index;
	
	public DHLBaseAdditor(DHLOntologyBase base){
		this.index = base.getIndex();
	}

	public void addAxiom(DHLAxiom newAxiom) {
		newAxiom.accept(this);
	}
	
	public void addInverseProperty(Integer role, Integer inverseRole) {
		this.index.inversePropertyMap.put(role, inverseRole);
	}

	// TBox
	
	@Override
	public void visit(DHLSubClassOfAxiom arg) {
		AdditionUtil.addElement(arg, index.subClassOfAxiomSet);
		
		AdditionUtil.addElement(arg.getSubConcept(), arg, 
				index.leftIndexOfSubClassOfAxioms);
	}
	
	@Override
	public void visit(DHLLeftExistentialAxiom arg) {
		AdditionUtil.addElement(arg, index.leftExistentialAxiomSet);
		
		AdditionUtil.addElement(arg.getSubConcept(), arg, 
				index.subClassIndexOfLeftExistentialAxiom);
		
		AdditionUtil.addElement(arg.getRole(), arg, 
				index.subRoleIndexOfLeftExistentialAxiom);
		
	}

	@Override
	public void visit(DHLObjectIntersectionOfAxiom arg) {
		AdditionUtil.addElement(arg, index.intersectionOfAxiomSet);
		
		AdditionUtil.addElement(arg.getSubFirstConcept(), arg, 
				index.leftFirstIndexOfIntersectionOfAxioms);
		
		AdditionUtil.addElement(arg.getSubSecondConcept(), arg, 
				index.leftSecondIndexOfIntersectionOfAxioms);
		
	}

	
	// RBox

	@Override
	public void visit(DHLSubObjectPropertyOfAxiom arg) {
		AdditionUtil.addElement(arg, index.subPropertyOfAxiomSet);
		
		AdditionUtil.addElement(arg.getSubProperty(), arg, 
				index.leftIndexOfSubPropertyOfAxioms);
		
	}

	@Override
	public void visit(DHLSubPropertyChainOfAxiom arg) {
		AdditionUtil.addElement(arg, index.subPropertyChainOfAxioms);
		
		AdditionUtil.addElement(arg.getSubProperty1(), arg, 
				index.leftFirstIndexOfSubPropertyChainOfAxioms);
		
		AdditionUtil.addElement(arg.getSubProperty2(), arg, 
				index.leftSecondIndexOfSubPropertyChainOfAxioms);
			
	}

	@Override
	public void visit(DHLTransitiveObjectPropertyAxiom arg) {
		AdditionUtil.addElement(arg.getProperty(), index.transitiveRoles);
		
	}
	
	// ABox

	@Override
	public void visit(DHLClassAssertion arg) {
		AdditionUtil.addElement(arg, index.classAssertionSet);
		
		AdditionUtil.addElement(arg.getConcept(), arg.getMember(), arg, 
				index.classMemberIndexOfClassAssertions);
		
	}

	@Override
	public void visit(DHLObjectPropertyAssertion arg) {
		AdditionUtil.addElement(arg, index.propertyAssertionSet);
		
		AdditionUtil.addElement(arg.getProperty(), arg.getSubject(), 
				arg.getObject(), arg, index.roleFirstSecondIndexOfPropertyAssertions);
		
		AdditionUtil.addElement(arg.getProperty(), arg.getObject(), 
				arg.getSubject(), arg, index.roleSecondFirstIndexOfPropertyAssertions);
		
		
	}

}
