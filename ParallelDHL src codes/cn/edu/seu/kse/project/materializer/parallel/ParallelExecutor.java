package cn.edu.seu.kse.project.materializer.parallel;

public interface ParallelExecutor extends Runnable {
	
	public void setNewParallelTask(ParallelTask task);
	
	public void setVacant();

}
