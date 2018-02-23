package cn.edu.seu.kse.project.ontology.simple.structure;


public class SimpleAxiomFactory {
	
	
	public SimpleSubClassOfAxiom getSimpleObjectSubClassOfAxiom(
			Integer subClass,
			Integer superClass){
		
		return new SimpleSubClassOfAxiom(subClass, superClass);
		
	}
	
	public SimpleObjectIntersectionOfAxiom getSimpleObjectIntersectionOfAxiom(
			Integer subClass1,
			Integer subClass2,
			Integer superClass){
		
		return new SimpleObjectIntersectionOfAxiom(subClass1, subClass2, superClass);
		
	}
	
	public SimpleSomeValuesFromAxiom getSimpleSomeValuesFromAxiom(
			Integer subClass,
			Integer role,
			Integer superClass){
		
		return new SimpleSomeValuesFromAxiom(subClass, role, superClass);
		
	}
	
	public SimpleLeftExistentialAxiom getSimpleLeftExistentialAxiom(
			Integer role,
			Integer subClass,
			Integer superClass){
		
		return new SimpleLeftExistentialAxiom(role, subClass, superClass);
		
	}

	
	public SimpleSubObjectPropertyOfAxiom getSimpleSubObjectPropertyOfAxiom(
			Integer subProperty,
			Integer superProperty){
		
		return new SimpleSubObjectPropertyOfAxiom(
				subProperty, superProperty);
		
	}
	
	public SimpleSubPropertyChainOfAxiom getSimpleSubPropertyChainOfAxiom(
			Integer subProperty1,
			Integer subProperty2,
			Integer superProperty){
		
		return new SimpleSubPropertyChainOfAxiom(
				subProperty1, subProperty2, superProperty);
		
	}
	
	public SimpleTransitiveObjectPropertyAxiom getSimpleTransitiveObjectPropertyAxiom(
			Integer property){
		
		return new SimpleTransitiveObjectPropertyAxiom(property);
		
	}
	
	public SimpleObjectPropertyAssertion getSimpleObjectPropertyAssertion(
			Integer property,
			Integer subject,
			Integer object){
		
		return new SimpleObjectPropertyAssertion(property, subject, object);
		
	}
	
	public SimpleClassAssertion getSimpleClassAssertion(
			Integer concept,
			Integer member){
		
		return new SimpleClassAssertion(concept, member);
		
	}
	
}
