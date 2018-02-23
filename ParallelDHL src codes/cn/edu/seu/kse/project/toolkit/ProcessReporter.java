package cn.edu.seu.kse.project.toolkit;

import cn.edu.seu.kse.project.logger.Logger;


public class ProcessReporter {
	
	private String name = this.getClass().getName()+">";
	private int base = 0;
	private int currentProcessed = 0;
	private int snapshot1 = 0;
	private int snapshot2 = 0;
	private Logger logger;
	
	public ProcessReporter(int base, Logger logger) {
		this.base = base;
		this.logger = logger;
	}
	
	public void anotherProcessed(){
		currentProcessed ++ ;
		snapshot2 = currentProcessed * 100 / base;
		if(snapshot2 != snapshot1) {
			snapshot1 = snapshot2;
			logger.debug(name + snapshot2 + "% processed.");
		}
	}

}
