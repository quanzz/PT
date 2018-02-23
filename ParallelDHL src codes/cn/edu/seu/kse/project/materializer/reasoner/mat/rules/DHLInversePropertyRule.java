package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLInversePropertyRule implements DHLRule {

	@Override
	public void apply(
			DHLAxiom trigger, 
			DHLMaterializer materializer,
			DHLOntologyBase ontoBase) {
		
		if(!(trigger instanceof DHLObjectPropertyAssertion)) return;
		
		DHLBaseAccessor accessor = ontoBase.getBaseAccessor();
		DHLMaterializationController controller = materializer.getMaterializationController();
		
		DHLObjectPropertyAssertion propertyAssertion = 
				(DHLObjectPropertyAssertion) trigger;
		
		Integer role = propertyAssertion.getProperty();
		Integer subject = propertyAssertion.getSubject();
		Integer object = propertyAssertion.getObject();
	
		
		Integer inverseRole = accessor.getInversePorperty(role);
		
		if(inverseRole != null) {
			if(!accessor.containsRoleAssertion(inverseRole, object, subject)) {
				controller.deriveRoleAssertion(inverseRole, object, subject);
			}
		}
		
	}

}
