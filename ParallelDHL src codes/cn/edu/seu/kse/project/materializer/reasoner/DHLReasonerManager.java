package cn.edu.seu.kse.project.materializer.reasoner;

import cn.edu.seu.kse.project.logger.Logger;
import cn.edu.seu.kse.project.materializer.ontology.DHLOntologyBase;
import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializer;

public class DHLReasonerManager {
	
	
	public static DHLReasoner createDHLReasoner(
			DHLOntologyBase ontoBase, int reasonerType,Logger log){
		if(reasonerType == DHLReasoner.MATERIALIZER) {
			return new DHLMaterializer(ontoBase,log);
		}
		return null;
	}
	
	public static DHLReasoner createParallelDHLReasoner(
			DHLOntologyBase ontoBase, int reasonerType, int numOfThreads,Logger log){
		if(reasonerType == DHLReasoner.MATERIALIZER) {
			DHLMaterializer materializer = new DHLMaterializer(ontoBase,log);
			materializer.setNumOfThreads(numOfThreads);
			return materializer;
		}
		return null;
	}

}
