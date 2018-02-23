package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLSubObjectPropertyOfRule implements DHLRule {
	
	

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
		
		Set<DHLAxiom> subObjectPropertyOfAxioms =
				accessor.getSubObjectPropertyOfAxiomsBySubProperty(role);
		
		if(subObjectPropertyOfAxioms != null) {
			
			for(DHLAxiom axiom : subObjectPropertyOfAxioms) {
				DHLSubObjectPropertyOfAxiom subObjectPropertyOfAxiom =
						(DHLSubObjectPropertyOfAxiom) axiom;
				
				Integer superProperty = subObjectPropertyOfAxiom.getSuperProperty();
				
				if(!(accessor.containsRoleAssertion(
						superProperty, subject, object))) {
					deriver.deriveRoleAssertion(
							superProperty, subject, object);
				}		
			}
		}	
		
	}

}
