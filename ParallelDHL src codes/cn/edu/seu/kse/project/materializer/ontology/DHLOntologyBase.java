package cn.edu.seu.kse.project.materializer.ontology;

import java.util.HashSet;
import java.util.Set;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiomFactory;
import cn.edu.seu.kse.project.ontology.simple.api.SimpleOntologyBase;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;

public class DHLOntologyBase implements SimpleOntologyBase {

	//private String name = this.getClass().getName()+">";

	private DHLIndex index;
	private DHLBaseAdditor additor;
	private DHLBaseAccessor accessor;
	
	private DHLAxiomFactory axiomFactory;
	private SimpleDHLTranslator simpleDHLtranslator;
	
	//private Logger log;
	
	
	public DHLOntologyBase(Logger log){
		this.index = new DHLIndex();
		
		this.additor = new DHLBaseAdditor(this);
		this.accessor = new DHLBaseAccessor(index);
		
		this.axiomFactory = new DHLAxiomFactory();
		this.simpleDHLtranslator = new SimpleDHLTranslator(this);
		
		//this.log = log;
	}
	
	public DHLIndex getIndex(){
		return index;
	}
	
	public DHLAxiomFactory getAxiomFactory(){
		return axiomFactory;
	}
	
	public DHLBaseAdditor getBaseAdditor(){
		return additor;
	}
	
	public DHLBaseAccessor getBaseAccessor(){
		return accessor;
	}
	
	@Override
	public Set<SimpleAxiom> getFormatedAxioms() {
		
		Set<SimpleAxiom> results = new HashSet<SimpleAxiom>();
		
		if(index != null) {
			results.addAll(index.classAssertionSet);
			results.addAll(index.propertyAssertionSet);
		}
		
		return results;
	}

	@Override
	public void registerSimpleAxiom(SimpleAxiom axiom) {
		axiom.accept(simpleDHLtranslator);
	}

	@Override
	public void registerInverseProperties(
			Integer role, Integer inverseRole) {
		additor.addInverseProperty(role, inverseRole);
	}
	
	

}
