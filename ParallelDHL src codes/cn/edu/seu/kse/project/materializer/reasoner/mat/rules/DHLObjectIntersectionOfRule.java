package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLObjectIntersectionOfRule implements DHLRule {

	@Override
	public void apply(
			DHLAxiom trigger, 
			DHLMaterializer materializer,
			DHLOntologyBase ontoBase) {
		
		if(!(trigger instanceof DHLClassAssertion)) return;
		
		DHLBaseAccessor accessor = ontoBase.getBaseAccessor();
		DHLMaterializationController controller = 
				materializer.getMaterializationController();
		
		DHLClassAssertion classAssertion = (DHLClassAssertion) trigger;	
		Integer concept = classAssertion.getConcept();
		Integer member = classAssertion.getMember();
		
		Set<DHLAxiom> intersectionOfAxiomsByLeftFirst =
				accessor.getIntersectionOfAxiomsByLeftFirst(concept);
		Set<DHLAxiom> intersectionOfAxiomsByLeftSecond =
				accessor.getIntersectionOfAxiomsByLeftSecond(concept);
		
		if(intersectionOfAxiomsByLeftFirst != null) {
			for(DHLAxiom axiom : intersectionOfAxiomsByLeftFirst) {
				DHLObjectIntersectionOfAxiom intersectionOfAxiom = 
						(DHLObjectIntersectionOfAxiom) axiom;
				
				Integer leftSecond = intersectionOfAxiom.getSubSecondConcept();
				Integer superClass = intersectionOfAxiom.getSuperConcept();
				
				if(accessor.containsClassAssertion(leftSecond, member)) {				
					if(!accessor.containsClassAssertion(superClass, member)) {
						controller.deriveClassAssertion(superClass, member);
					}
				} else {
					controller.correlateClassClassAssertions(
							leftSecond, member, superClass, member);
				}
			}
		}
		
		if(intersectionOfAxiomsByLeftSecond != null) {
			for(DHLAxiom axiom : intersectionOfAxiomsByLeftSecond) {
				DHLObjectIntersectionOfAxiom intersectionOfAxiom = 
						(DHLObjectIntersectionOfAxiom) axiom;
				
				Integer leftFirst = intersectionOfAxiom.getSubFirstConcept();
				Integer superClass = intersectionOfAxiom.getSuperConcept();
				
				if(accessor.containsClassAssertion(leftFirst, member)) {				
					if(!accessor.containsClassAssertion(superClass, member)) {
						controller.deriveClassAssertion(superClass, member);
					}
				} else {
					
					if(!accessor.containsClassAssertion(superClass, member)) {				
						controller.correlateClassClassAssertions(
								leftFirst, member, superClass, member);
					}
				}
			}
		}
	}

}
