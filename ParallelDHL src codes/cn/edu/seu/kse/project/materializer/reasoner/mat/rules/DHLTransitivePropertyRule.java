package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Map;
import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLTransitivePropertyRule implements DHLRule{

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
		
		boolean isTransitive = accessor.isTransitive(role);
		
		if(isTransitive) {
			
			//first
			Map<Integer, Set<DHLAxiom>> map =
					accessor.getObjectPropertyAssertionsByRoleAndSubject(role, object);
			
			if(map != null) {
				Set<Integer> oobjects = map.keySet();
				
				for(Integer oobject : oobjects) {
					if(!accessor.containsRoleAssertion(role, subject, oobject)) {
						deriver.deriveRoleAssertion(role, subject, oobject);
					}
				}
			}
			
			//second
			map = accessor.getObjectPropertyAssertionsByRoleAndObject(role, subject);
			
			if(map!=null) {
				Set<Integer> ssubjects = map.keySet();
				
				for(Integer ssubject : ssubjects) {
					if(!accessor.containsRoleAssertion(role, ssubject, object)) {
						deriver.deriveRoleAssertion(role, ssubject, object);
					}
				}
			}
		}
	}
	
	

}
