package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Map;
import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLPropertyChianOfRule implements DHLRule{

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
		
		Set<DHLAxiom> propertyChainOfAxiomsByFirst =
				accessor.getPropertyChainOfAxiomsByLeftFirst(role);
		Set<DHLAxiom> propertyChainOfAxiomsBySecond =
				accessor.getPropertyChainOfAxiomsByLeftSecond(role);
		
		if(propertyChainOfAxiomsByFirst != null) {
			for(DHLAxiom axiom : propertyChainOfAxiomsByFirst) {
				DHLSubPropertyChainOfAxiom propertyChainOfAxiom =
						(DHLSubPropertyChainOfAxiom) axiom;
				
				Integer subProperty2 = propertyChainOfAxiom.getSubProperty2();
				Integer superRole = propertyChainOfAxiom.getSuperProperty();
				
				Map<Integer, Set<DHLAxiom>> map =
						accessor.getObjectPropertyAssertionsByRoleAndSubject(subProperty2, object);
				
				if(map != null) {
					Set<Integer> oobjects = map.keySet();
					
					for(Integer oobject : oobjects) {
						if(!accessor.containsRoleAssertion(superRole, subject, oobject)) {
							deriver.deriveRoleAssertion(superRole, subject, oobject);
						}
					}
				}
				
			}
		}
		
		if(propertyChainOfAxiomsBySecond != null) {
			for(DHLAxiom axiom : propertyChainOfAxiomsBySecond) {
				DHLSubPropertyChainOfAxiom propertyChainOfAxiom =
						(DHLSubPropertyChainOfAxiom) axiom;
				
				Integer subProperty1 = propertyChainOfAxiom.getSubProperty1();
				Integer superRole = propertyChainOfAxiom.getSuperProperty();
				
				Map<Integer, Set<DHLAxiom>> map =
						accessor.getObjectPropertyAssertionsByRoleAndObject(subProperty1, subject);
				
				if(map != null) {
					Set<Integer> ssubjects = map.keySet();
					
					for(Integer ssubject : ssubjects) {
						if(!accessor.containsRoleAssertion(superRole, ssubject, object)) {
							deriver.deriveRoleAssertion(superRole, ssubject, object);
						}
					}
				}
			}
		}
		
	}

}
