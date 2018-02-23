package cn.edu.seu.kse.project.toolkit;

import java.text.DecimalFormat;

public class ProcessTracker {
	
	private double total = 0;
	private double current = 0;
	private double lastPercent = 0;
	private String processName = "";
	private String percentStr = "";
	
	private DecimalFormat df = new DecimalFormat("0");
	
	public ProcessTracker(String processName, int total) {
		this.processName = processName;
		this.total = total;
		Tool.p(this.processName + ": ");
	}
	
	public void processed(int workload) {
		
		current = current + workload;
		
		double currentPercent = (current / total)*100;
		
		double process = currentPercent - lastPercent;
		
		if(process >= 10) {
			lastPercent = currentPercent;
			percentStr = df.format(currentPercent);
			
			Tool.p(percentStr + "% ");
		}

		if(currentPercent >= 100) {
			Tool.p("100% \r\n\r\n");
		}
	}

}
