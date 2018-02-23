package cn.edu.seu.kse.project.ontology.channel.connector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import cn.edu.seu.kse.project.ontology.simple.api.SimpleOntologyFactory;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomFactory;

public class OWLSimpleIndex {
	
	/**
	 * used for auxiliary classes and properties.
	 */
	protected static String AUX_PREFIX_CLASS =
			"http://onto.kse.seu.edu.cn/AuxiliaryClass#";
	protected static String AUX_PREFIX_PROPERTY = 
			"http://onto.kse.seu.edu.cn/AuxiliaryObjectProperty#";
	
	protected static String SEU_PREFIX_CLASS =
			"http://onto.kse.seu.edu.cn/SEUClass#";
	protected static String SEU_PREFIX_PROPERTY = 
			"http://onto.kse.seu.edu.cn/SEUObjectProperty#";
	protected static String SEU_PREFIX_INDIVIDUAL = 
			"http://onto.kse.seu.edu.cn/SEUIndividual#";
	
	
	// the integer id for ontology elements
	protected static Integer TOP_CONCEPT = 1;
	
	protected static Integer BOTTOM_CONCEPT = 0;
	
	
	// axiom types
	protected static Integer CONCEPT_SUBSUME = 0;
	
	protected static Integer INTERSECTION_CONCEPT_SUBSUME = 1;
	
	protected static Integer SOMEVALUES_CONCEPT_SUBSUME = 2;
	
	protected static Integer ALLVALUES_SUBSUME = 3;
	
	protected static Integer ROLE_SUBSUME = 4;
	
	protected static Integer CHAINOF_ROLE_SUBSUME = 5;
	
	
	// top concept
	protected OWLClass topClass;
	
	// bottom concept
	protected OWLClass bottomClass;
	
	
	
	// create new class for complex concepts
	protected Map<OWLClassExpression, OWLClass> auxiliaryClassMap;
	
	// create new property for complex properties
	protected Map<OWLObjectPropertyExpression, OWLObjectProperty> auxiliaryObjectPropertyMap;
	
	
	// allocate id to auxiliary class
	protected IdentityAllocator auxiliaryClassIdAllocator;
	
	// allocate id to auxiliary property
	protected IdentityAllocator auxiliaryObjectPropertyIdAllocator;
	
	

	// allocate id to all classes including new classes
	protected IdentityAllocator classIdentityAllocator;
	
	// allocate id to all properties including new properties
	protected IdentityAllocator propertyIdentityAllocator;
	
	protected IdentityAllocator individualIdentityAllocator;
	
	
	// the entry-index for named classes
	protected Map<Integer, OWLClass> classMap;
	
	// the inverse entry-index for named classes
	protected Map<OWLClass, Integer> invClassMap;
	
	// the entry-index for named properties
	protected Map<Integer, OWLObjectPropertyExpression> propertyMap;
	
	// the inverse entry-index for named properties
	protected Map<OWLObjectPropertyExpression, Integer> invPropertyMap;
	
	// inverse property id map
	protected Map<Integer, Integer> inversePropertyIntegerMap;
	
	// the entry-index for individual
	protected Map<Integer, OWLIndividual> individualMap;
		
	// the inverse entry-index for named classes
	protected Map<OWLIndividual, Integer> invIndividualMap;
	
	protected OWLDataFactory dataFactory = null;
	
	protected SimpleAxiomFactory simpleAxiomFactory = null; 
	
	protected Set<SimpleAxiom> simpleAxiomSet;
	
	protected OWLSimpleConnector connector;
	
	public OWLSimpleIndex(OWLSimpleConnector connector) {
		this.connector = connector;
		this.dataFactory = this.connector.getOWLDataFactory();
		this.simpleAxiomFactory = 
				SimpleOntologyFactory.getSimpleAxiomFactory();
		
		this.auxiliaryClassIdAllocator = new IdentityAllocator(0);
		this.auxiliaryClassMap = new HashMap<OWLClassExpression, OWLClass>();
		
		this.auxiliaryObjectPropertyIdAllocator = new IdentityAllocator(0);
		this.auxiliaryObjectPropertyMap = new HashMap<OWLObjectPropertyExpression, OWLObjectProperty>();
		
		this.classIdentityAllocator = new IdentityAllocator(2);
		this.propertyIdentityAllocator = new IdentityAllocator(0);
		this.individualIdentityAllocator = new IdentityAllocator(0);
		
		this.classMap = new HashMap<Integer, OWLClass>();
		this.invClassMap = new HashMap<OWLClass, Integer>();
		
		this.propertyMap = new HashMap<Integer, OWLObjectPropertyExpression>();
		this.invPropertyMap = new HashMap<OWLObjectPropertyExpression, Integer>();
		this.inversePropertyIntegerMap = new HashMap<Integer, Integer>();
		
		this.individualMap = new HashMap<Integer, OWLIndividual>();
		this.invIndividualMap = new HashMap<OWLIndividual, Integer>();
		
		this.topClass = dataFactory.getOWLThing();
		this.bottomClass = dataFactory.getOWLNothing();
		
		classMap.put(TOP_CONCEPT, topClass);
		invClassMap.put(topClass, TOP_CONCEPT);
		classMap.put(BOTTOM_CONCEPT, bottomClass);
		invClassMap.put(bottomClass, BOTTOM_CONCEPT);
		
		simpleAxiomSet = new HashSet<SimpleAxiom>();	
	}

}
