package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubClassOfAxiom;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLSubClassOfRule implements DHLRule{

	@Override
	public void apply(
			DHLAxiom trigger, 
			DHLMaterializer materializer,
			DHLOntologyBase ontoBase) {

		if(!(trigger instanceof DHLClassAssertion)) return;		
		
		DHLBaseAccessor accessor = ontoBase.getBaseAccessor();
		DHLMaterializationController controller = materializer.getMaterializationController();
		
		DHLClassAssertion classAssertion = (DHLClassAssertion) trigger;	
		Integer concept = classAssertion.getConcept();
		Integer member = classAssertion.getMember();
		
		Set<DHLAxiom> subClassOfAxioms = 
				accessor.getSubClassOfAxiomsBySubClass(concept);
		
		if(subClassOfAxioms != null) {
			for(DHLAxiom axiom : subClassOfAxioms) {
				DHLSubClassOfAxiom subClassOfAxiom = (DHLSubClassOfAxiom) axiom;
				
				Integer superClass = subClassOfAxiom.getSuperConcept();
				
				if(!accessor.containsClassAssertion(superClass, member)) {
					controller.deriveClassAssertion(superClass, member);
				}
			}
		}
	}
	
}
