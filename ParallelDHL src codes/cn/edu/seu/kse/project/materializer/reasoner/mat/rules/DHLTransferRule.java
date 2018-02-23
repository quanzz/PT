package cn.edu.seu.kse.project.materializer.reasoner.mat.rules;

import java.util.Set;

import cn.edu.seu.kse.project.materializer.ontology.DHLBaseAccessor;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationController;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLTransferRule implements DHLRule {
	
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
		
		if(accessor.containsClassAssertion(concept, member)) {
			// ���classAssertion�Ѿ��Ƴ����ˣ���ô�ҾͲ���Ҫ����ǰ�ˣ���Ϊ����Ŀ����Ƴ�����
			Set<DHLClassAssertion> children =
					controller.getChildren(classAssertion);
			if(children != null) {
				for(DHLClassAssertion child : children) {
					if(!accessor.containsClassAssertion(child)){
						controller.deriveClassAssertion(child);
					}
				}
			}
			
		} else {
			Set<DHLClassAssertion> assertions = controller.transfer(classAssertion);
			for(DHLClassAssertion assertion : assertions) {
				controller.put(assertion);
			}
		}
		
	}

}
