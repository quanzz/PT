package cn.edu.seu.kse.project.materializer.parallel;

import cn.edu.seu.kse.project.materializer.reasoner.mat.DHLMaterializationTask;

public interface ParallelController {
	
	public boolean hasParallelTask();
	
	public DHLMaterializationTask getNewParallelTask();

}
