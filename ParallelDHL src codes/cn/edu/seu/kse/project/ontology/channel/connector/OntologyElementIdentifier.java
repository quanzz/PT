package cn.edu.seu.kse.project.ontology.channel.connector;


public abstract class OntologyElementIdentifier {
	
	/**
	 * object constructors using in class expression.
	 */
	
	public static final int OWLObjectIntersectionOf = 100;
	
	public static final int OWLObjectUnionOf = 101;
	
	public static final int OWLObjectComplementOf = 102;
	

	public static final int OWLObjectSomeValuesFrom = 103;
	
	public static final int OWLObjectAllValuesFrom = 104;
	
	public static final int OWLObjectHasValue = 105;
	
	public static final int OWLObjectExistentialRestriction = 122;
	
	public static final int OWLObjectFullExistentialRestriction = 123;
	
	
	public static final int OWLObjectMinCardinality = 106;
	
	public static final int OWLObjectExactCardinality = 107;
	
	public static final int OWLObjectMaxCardinality = 108;
	
	public static final int OWLObjectMinCardinalityEQ0 = 109;
	
	public static final int OWLObjectMinCardinalityGT1 = 110;
	
	public static final int OWLObjectMinCardinalityLET1 = 111;
	
	public static final int OWLObjectExactCardinalityEQ0 = 112;
	
	public static final int OWLObjectExactCardinalityGT1 = 113;
	
	public static final int OWLObjectExactCardinalityLET1 = 114;
	
	public static final int OWLObjectMaxCardinalityEQ0 = 115;
	
	public static final int OWLObjectMaxCardinalityGT1 = 116;
	
	public static final int OWLObjectMaxCardinalityLET1 = 117;
	
	public static final int OWLObjectNumberRestriction = 127;
	
	public static final int OWLObjectQualifiedNumberRestriction = 128;
	
	
	public static final int OWLObjectHasSelf = 118;
	
	public static final int OWLObjectOneOf = 119;

	
	public static final int OWLObjectPropertyDomainAxiom = 120;
	
	public static final int OWLObjectPropertyRangeAxiom = 121;
	
	
	
	public static final int OWLThing = 124;
	
	public static final int OWLNothing = 125;
	
	public static final int OWLNominal = 126;
	
	
	
	/**
	 * Datatype constructors using in class expressions.
	 */
	
	public static final int OWLDataSomeValuesFrom = 231;
	
	public static final int OWLDataAllValuesFrom = 232;
	
	public static final int OWLDataHasValue = 233;
	
	public static final int OWLDataMinCardinality = 234;
	
	public static final int OWLDataExactCardinality = 235;
	
	public static final int OWLDataMaxCardinality = 236;
	
	
	/**
	 * class axiom statements.
	 */
	
	public static final int OWLDisjointClassesAxiom = 460;
	
	public static final int OWLSubClassOfAxiom = 461;
	
	public static final int OWLEquivalentClassesAxiom = 462;
	
	
	/**
	 * property statements.
	 */
	
	public static final int OWLObjectInverseOf = 340;
	
	public static final int OWLAsymmetricObjectPropertyAxiom = 341;
	
	public static final int OWLSymmetricObjectPropertyAxiom = 346;
	
	public static final int OWLReflexiveObjectPropertyAxiom = 342;
	
	public static final int OWLSubObjectPropertyOfAxiom = 343;
	
	public static final int OWLDisjointObjectPropertiesAxiom = 344;
	
	public static final int OWLFunctionalObjectPropertyAxiom = 345;
	
	public static final int OWLTransitiveObjectPropertyAxiom = 347;
	
	public static final int OWLIrreflexiveObjectPropertyAxiom = 348;
	
	public static final int OWLSubPropertyChainOfAxiom = 349;
	
	public static final int OWLEquivalentObjectPropertiesAxiom = 350;
	
	
	/**
	 * data property axiom.
	 */
	
	public static final int OWLDataPropertyDomainAxiom = 500;

	public static final int OWLNegativeDataPropertyAssertionAxiom = 501;

	public static final int OWLDisjointDataPropertiesAxiom = 502;

	public static final int OWLDataPropertyRangeAxiom = 503;

	public static final int OWLFunctionalDataPropertyAxiom = 504;

	public static final int OWLEquivalentDataPropertiesAxiom = 505;

	public static final int OWLDataPropertyAssertionAxiom = 506;

	public static final int OWLSubDataPropertyOfAxiom = 507;

	public static final int OWLDatatypeDefinitionAxiom = 508;
	


	public static int[] elementIndex = {
		OWLObjectIntersectionOf,
		OWLObjectUnionOf,
		OWLObjectComplementOf,
		OWLObjectSomeValuesFrom,
		OWLObjectAllValuesFrom,
		OWLObjectHasValue,
		
		OWLObjectExistentialRestriction,
		OWLObjectFullExistentialRestriction,
		
		OWLObjectMinCardinality,
		OWLObjectExactCardinality,
		OWLObjectMaxCardinality,
		
		OWLObjectMinCardinalityEQ0,
		OWLObjectMinCardinalityGT1,
		OWLObjectMinCardinalityLET1,
		OWLObjectExactCardinalityEQ0,
		OWLObjectExactCardinalityGT1,
		OWLObjectExactCardinalityLET1,
		OWLObjectMaxCardinalityEQ0,
		OWLObjectMaxCardinalityGT1,
		OWLObjectMaxCardinalityLET1,
		
		OWLObjectNumberRestriction,
		OWLObjectQualifiedNumberRestriction,
		
		OWLObjectHasSelf,
		OWLObjectOneOf,
		OWLObjectPropertyDomainAxiom,
		OWLObjectPropertyRangeAxiom,
		
		OWLThing,
		OWLNothing,
		OWLNominal,
		
		OWLDataSomeValuesFrom,
		OWLDataAllValuesFrom,
		OWLDataHasValue,
		OWLDataMinCardinality,
		OWLDataExactCardinality,
		OWLDataMaxCardinality,
		
		OWLDisjointClassesAxiom,
		OWLSubClassOfAxiom,
		OWLEquivalentClassesAxiom,
		
		OWLObjectInverseOf,
		OWLAsymmetricObjectPropertyAxiom,
		OWLSymmetricObjectPropertyAxiom,
		OWLReflexiveObjectPropertyAxiom,
		OWLSubObjectPropertyOfAxiom,
		OWLDisjointObjectPropertiesAxiom,
		OWLFunctionalObjectPropertyAxiom,
		OWLTransitiveObjectPropertyAxiom,
		OWLIrreflexiveObjectPropertyAxiom,
		OWLSubPropertyChainOfAxiom,
		OWLEquivalentObjectPropertiesAxiom,
		
		OWLDataPropertyDomainAxiom,
		OWLNegativeDataPropertyAssertionAxiom,
		OWLDisjointDataPropertiesAxiom,
		OWLDataPropertyRangeAxiom,
		OWLFunctionalDataPropertyAxiom,
		OWLEquivalentDataPropertiesAxiom,
		OWLDataPropertyAssertionAxiom,
		OWLSubDataPropertyOfAxiom,
		OWLDatatypeDefinitionAxiom
	};
	
	
	public String elementName(int id) {
		String name = "";
		
		switch(id) {
		
		case OWLObjectIntersectionOf: name = "conjunction"; break;
		
//		case OWLObjectUnionOf,
//		case OWLObjectComplementOf,
//		case OWLObjectSomeValuesFrom,
//		case OWLObjectAllValuesFrom,
//		case OWLObjectHasValue,
//		
//		case OWLObjectExistentialRestriction,
//		case OWLObjectFullExistentialRestriction,
//		
//		case OWLObjectMinCardinality,
//		case OWLObjectExactCardinality,
//		case OWLObjectMaxCardinality,
//		
//		case OWLObjectMinCardinalityEQ0,
//		case OWLObjectMinCardinalityGT1,
//		case OWLObjectMinCardinalityLET1,
//		case OWLObjectExactCardinalityEQ0,
//		case OWLObjectExactCardinalityGT1,
//		case OWLObjectExactCardinalityLET1,
//		case OWLObjectMaxCardinalityEQ0,
//		case OWLObjectMaxCardinalityGT1,
//		case OWLObjectMaxCardinalityLET1,
//		
//		case OWLObjectNumberRestriction,
//		case OWLObjectQualifiedNumberRestriction,
//		
//		case OWLObjectHasSelf,
//		case OWLObjectOneOf,
//		case OWLObjectPropertyDomainAxiom,
//		case OWLObjectPropertyRangeAxiom,
//		
//		case OWLThing,
//		case OWLNothing,
//		case OWLNominal,
//		
//		case OWLDataSomeValuesFrom,
//		case OWLDataAllValuesFrom,
//		case OWLDataHasValue,
//		case OWLDataMinCardinality,
//		case OWLDataExactCardinality,
//		case OWLDataMaxCardinality,
//		
//		case OWLDisjointClassesAxiom,
//		case OWLSubClassOfAxiom,
//		case OWLEquivalentClassesAxiom,
//		
//		case OWLObjectInverseOf,
//		case OWLAsymmetricObjectPropertyAxiom,
//		case OWLSymmetricObjectPropertyAxiom,
//		case OWLReflexiveObjectPropertyAxiom,
//		case OWLSubObjectPropertyOfAxiom,
//		case OWLDisjointObjectPropertiesAxiom,
//		case OWLFunctionalObjectPropertyAxiom,
//		case OWLTransitiveObjectPropertyAxiom,
//		case OWLIrreflexiveObjectPropertyAxiom,
//		case OWLSubPropertyChainOfAxiom,
//		case OWLEquivalentObjectPropertiesAxiom,
//		
//		case OWLDataPropertyDomainAxiom,
//		case OWLNegativeDataPropertyAssertionAxiom,
//		case OWLDisjointDataPropertiesAxiom,
//		case OWLDataPropertyRangeAxiom,
//		case OWLFunctionalDataPropertyAxiom,
//		case OWLEquivalentDataPropertiesAxiom,
//		case OWLDataPropertyAssertionAxiom,
//		case OWLSubDataPropertyOfAxiom,
//		case OWLDatatypeDefinitionAxiom
		
		default: name = "null"; break;
		}
		
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
