package cn.edu.seu.kse.project.materializer.parallel;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.seu.kse.project.logger.Logger;


public class ParallelMaster {
	
	private String name = this.getClass().getName()+">";
	
	private int poolSize;
	
	private ExecutorService performerPool = null;
	private ConcurrentLinkedQueue<ParallelExecutor> executableThreads;
	private ParallelController controller;
	
	private Logger log;
	
	private boolean startup = false;
	
	public ParallelMaster(ParallelController controller, Logger log){
		this.controller = controller;
		this.log = log;
	}
	
	public void setup(Set<ParallelExecutor> executors){
		if(executors != null) {
			this.poolSize = executors.size();
			
			performerPool = 
					Executors.newFixedThreadPool(this.poolSize);
			executableThreads = 
					new ConcurrentLinkedQueue<ParallelExecutor>();
			
			for(ParallelExecutor executor : executors) {
				executableThreads.offer(executor);
			}
			
			startup = true;
		}
		log.debug(name+"ParallelExecutorService setup. "
				+ "#threads: " + this.poolSize );
	}
	
	public void process(){
		
		if(!startup) return;
		
		// if there is un-processed assertion,
		// or there is some thread still being working,
		// the whole parallel work does not terminate.
		boolean executionSuccessful = false;
		while(controller.hasParallelTask()
				|| hasWorkingExecutor()) {
			
			ParallelTask newTask = controller.getNewParallelTask();
			
			if(newTask != null) {
				executionSuccessful = false;
				while(!executionSuccessful) {
					ParallelExecutor executor = getVacantExecutor();
					
					if(executor != null) {
						executionSuccessful = true;
						
						executor.setNewParallelTask(newTask);
						performerPool.execute(executor);
					}
				}
			}
		}
		
		log.debug(name+"no task, no working thread.");
	}
	
	public void shutdown(){
		if(performerPool != null){
			if(!performerPool.isShutdown()){
				performerPool.shutdown();
			}
		}
		startup = false;
		
		log.debug(name+"ParallelExecutorService shutdown.");
	}
	
	public ParallelExecutor getVacantExecutor(){
		if(!executableThreads.isEmpty()){
			return executableThreads.poll();
		}
		return null;
	}
	
	public boolean hasWorkingExecutor() {
		if(executableThreads.size() < poolSize) {
			return true;
		}
		return false;
	}
	
	public void setVacant(ParallelExecutor vacantExecutor){
		executableThreads.offer(vacantExecutor);
	}

}
