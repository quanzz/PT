package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLLeftExistentialAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLLeftExistentialRule implements DHLRule {

	@Override
	public void apply(
			DHLAxiom trigger, 
			DHLMaterializer materializer,
			DHLOntologyBase ontoBase) {
		
		if(!(trigger instanceof DHLObjectPropertyAssertion)) return;
		
		DHLBaseAccessor accessor = ontoBase.getBaseAccessor();
		DHLMaterializationController deriver = materializer.getMaterializationController();
		
		DHLObjectPropertyAssertion propertyAssertion = 
				(DHLObjectPropertyAssertion) trigger;
		
		Integer role = propertyAssertion.getProperty();
		Integer subject = propertyAssertion.getSubject();
		Integer object = propertyAssertion.getObject();
		
		Set<DHLAxiom> axioms = accessor.getLeftExistentialAxiomsBySubRole(role);
		
		if(axioms != null) {
			for(DHLAxiom axiom : axioms) {
				DHLLeftExistentialAxiom leftExistentialAxiom = 
						(DHLLeftExistentialAxiom) axiom;
				
				Integer subClass = leftExistentialAxiom.getSubConcept();
				Integer superClass = leftExistentialAxiom.getSuperConcept();
				
				if(accessor.containsClassAssertion(subClass, object)){
					if(!(accessor.containsClassAssertion(superClass, subject))) {
						deriver.deriveClassAssertion(superClass, subject);
					}
				} else {
					if(!accessor.containsClassAssertion(superClass, subject)) {
						deriver.correlateClassClassAssertions(
								subClass, object, superClass, subject);
					}
				}
			}
		}
		
	}

}
