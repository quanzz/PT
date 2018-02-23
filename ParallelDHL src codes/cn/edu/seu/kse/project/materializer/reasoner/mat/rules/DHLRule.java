package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public interface DHLRule {
	
	public void apply(
			DHLAxiom trigger, 
			DHLMaterializer materializer,
			DHLOntologyBase ontoBase);

}
