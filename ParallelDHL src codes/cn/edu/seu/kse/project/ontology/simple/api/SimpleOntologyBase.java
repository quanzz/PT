package cn.edu.seu.kse.project.ontology.simple.api;

import java.util.Set;

import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiom;

public interface SimpleOntologyBase {
	
	public Set<SimpleAxiom> getFormatedAxioms();
	
	public void registerSimpleAxiom(SimpleAxiom axiom);
	
	public void registerInverseProperties(Integer role, Integer inverseRole);

}
