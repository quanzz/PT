package cn.edu.seu.kse.project.materializer.ontology.structure;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleClassAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectPropertyAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleTransitiveObjectPropertyAxiom;



public class DHLAxiomFactory {
	
	/* TBox */
	
	public DHLSubClassOfAxiom getDHLSubClassOfAxiom(
			Integer subClass,
			Integer superClass){
		
		return new DHLSubClassOfAxiom(subClass, superClass);
		
	}
	
	public DHLSubClassOfAxiom getDHLSubClassOfAxiom(
			SimpleSubClassOfAxiom simpleSubClassOfAxiom){
		
		return new DHLSubClassOfAxiom(
				simpleSubClassOfAxiom.getSubConcept(), 
				simpleSubClassOfAxiom.getSuperConcept());
		
	}
	
	//
	
	public DHLObjectIntersectionOfAxiom getDHLObjectIntersectionOfAxiom(
			Integer subClass1,
			Integer subClass2,
			Integer superClass){
		
		return new DHLObjectIntersectionOfAxiom(subClass1, subClass2, superClass);
		
	}
	
	public DHLObjectIntersectionOfAxiom getDHLObjectIntersectionOfAxiom(
			SimpleObjectIntersectionOfAxiom simpleObjectIntersectionOfAxiom){
		
		return new DHLObjectIntersectionOfAxiom(
				simpleObjectIntersectionOfAxiom.getSubFirstConcept(), 
				simpleObjectIntersectionOfAxiom.getSubSecondConcept(), 
				simpleObjectIntersectionOfAxiom.getSuperConcept());
		
	}

	//
	
	public DHLLeftExistentialAxiom getDHLLeftExistentialAxiom(
			Integer role,
			Integer subClass,
			Integer superClass){
		
		return new DHLLeftExistentialAxiom(role, subClass, superClass);
		
	}
	
	public DHLLeftExistentialAxiom getDHLLeftExistentialAxiom(
			SimpleLeftExistentialAxiom simpleLeftExistentialAxiom){
		
		return new DHLLeftExistentialAxiom(simpleLeftExistentialAxiom.getRole(), 
				simpleLeftExistentialAxiom.getSubConcept(), 
				simpleLeftExistentialAxiom.getSuperConcept());
		
	}

	//
	
	public DHLSubObjectPropertyOfAxiom getDHLSubObjectPropertyOfAxiom(
			Integer subProperty,
			Integer superProperty){
		
		return new DHLSubObjectPropertyOfAxiom(
				subProperty, superProperty);
		
	}
	
	public DHLSubObjectPropertyOfAxiom getDHLSubObjectPropertyOfAxiom(
			SimpleSubObjectPropertyOfAxiom simpleSubObjectPropertyOfAxiom){
		
		return new DHLSubObjectPropertyOfAxiom(
				simpleSubObjectPropertyOfAxiom.getSubProperty(),
				simpleSubObjectPropertyOfAxiom.getSuperProperty());
		
	}
	
	//
	
	public DHLSubPropertyChainOfAxiom getDHLSubPropertyChainOfAxiom(
			Integer subProperty1,
			Integer subProperty2,
			Integer superProperty){
		
		return new DHLSubPropertyChainOfAxiom(
				subProperty1, subProperty2, superProperty);
		
	}
	
	public DHLSubPropertyChainOfAxiom getDHLSubPropertyChainOfAxiom(
			SimpleSubPropertyChainOfAxiom simpleSubPropertyChainOfAxiom){
		
		return new DHLSubPropertyChainOfAxiom(
				simpleSubPropertyChainOfAxiom.getSubProperty1(), 
				simpleSubPropertyChainOfAxiom.getSubProperty2(), 
				simpleSubPropertyChainOfAxiom.getSuperProperty());
		
	}
	
	//
	
	public DHLTransitiveObjectPropertyAxiom getDHLTransitiveObjectPropertyAxiom(
			Integer property){
		
		return new DHLTransitiveObjectPropertyAxiom(property);
		
	}
	
	public DHLTransitiveObjectPropertyAxiom getDHLTransitiveObjectPropertyAxiom(
			SimpleTransitiveObjectPropertyAxiom simpleTransitiveObjectPropertyAxiom){
		
		return new DHLTransitiveObjectPropertyAxiom(simpleTransitiveObjectPropertyAxiom.getProperty());
		
	}
	
	//
	
	public DHLObjectPropertyAssertion getDHLObjectPropertyAssertion(
			Integer property,
			Integer subject,
			Integer object){
		
		return new DHLObjectPropertyAssertion(property, subject, object);
		
	}
	
	public DHLObjectPropertyAssertion getDHLObjectPropertyAssertion(
			SimpleObjectPropertyAssertion simpleObjectPropertyAssertion){
		
		return new DHLObjectPropertyAssertion(simpleObjectPropertyAssertion.getProperty(), 
				simpleObjectPropertyAssertion.getSubject(), 
				simpleObjectPropertyAssertion.getObject());
		
	}
	
	//
	
	public DHLClassAssertion getDHLClassAssertion(
			Integer concept,
			Integer member){
		
		return new DHLClassAssertion(concept, member);
		
	}
	
	public DHLClassAssertion getDHLClassAssertion(
			SimpleClassAssertion simpleClassAssertion){
		
		return new DHLClassAssertion(simpleClassAssertion.getConcept(), 
				simpleClassAssertion.getMember());
		
	}

}
