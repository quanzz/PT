package cn.edu.seu.kse.project.ontology.channel.connector;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLDataFactory;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.ontology.channel.translator.SimpleOntologyTranslator;
import cn.edu.seu.kse.project.ontology.owl.OWLOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.api.SimpleOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomFactory;

/**
 * 
 * This class is used to do translation between owl ontologies
 * and "simple ontologies" defined in this system.
 * 
 * @author Zhangquan Zhou
 * 
 * @time 2017-4-14
 *
 */
public class OWLSimpleConnector {
	
	// the class designed for owl ontology management.
	private OWLOntologyBase owlOntoBase;
	private OWLDataFactory dataFactory;
	
	// the class designed for simple ontology management.
	private SimpleOntologyBase simpleOntoBase;
	
	// supporting creating indexes for simple ontologies.
	private OWLSimpleAllocater allocater = null;
	
	private SimpleOWLAllocater revAllocater = null;
	
	// saving indexes.
	protected OWLSimpleIndex index = null;
	private SimpleAxiomFactory simpleAxiomFactory;
	
	private Logger log;
	
	public OWLSimpleConnector(
			OWLOntologyBase owlBase, 
			SimpleOntologyBase simpleBase,
			Logger log) {
		
		this.owlOntoBase = owlBase;
		this.dataFactory = this.owlOntoBase.getDataFactory();
		
		this.simpleOntoBase = simpleBase;
		
		this.index = new OWLSimpleIndex(this);
		this.simpleAxiomFactory = index.simpleAxiomFactory;
		
		this.allocater = new OWLSimpleAllocater(index);
		
		this.revAllocater = new SimpleOWLAllocater(index);
		
		this.log = log;
		
	}
	
	public void register(SimpleAxiom simpleAxiom){
		
		this.index.simpleAxiomSet.add(simpleAxiom);	
		this.simpleOntoBase.registerSimpleAxiom(simpleAxiom);
		
	}
	
	public void translate(){
		SimpleOntologyTranslator.translate(
				this, owlOntoBase, simpleOntoBase,log);
		
		Set<Integer> roles = index.inversePropertyIntegerMap.keySet();
		for(Integer role : roles) {
			Integer inverseRole = 
					index.inversePropertyIntegerMap.get(role);
			simpleOntoBase.registerInverseProperties(role, inverseRole);
		}
	}

	public OWLSimpleAllocater getAllocater() {
		return allocater;
	}
	
	public SimpleOWLAllocater getReverseAllocater(){
		return revAllocater;
	}
	
	public OWLDataFactory getOWLDataFactory() {
		return dataFactory;
	}
	
	public SimpleAxiomFactory getSimpleAxiomFactory(){
		return simpleAxiomFactory;
	}
	
}
