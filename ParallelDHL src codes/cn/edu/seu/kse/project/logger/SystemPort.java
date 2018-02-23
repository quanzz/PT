package cn.edu.seu.kse.project.logger;

import java.util.Scanner;

public class SystemPort implements Logger{

	private Scanner sysIn;
	private int level = 3;
	
	public SystemPort(int level) {
		this.level = level;
	}

	@Override
	public void launch(int level) {
		this.level = level;
	}

	@Override
	public String command() {
		sysIn = new Scanner(System.in);
		return sysIn.nextLine();
	}


	@Override
	public void debug(String words) {
		if(level <= Logger.DEBUG) {
			System.out.println(words);
		}
	}
	
	@Override
	public void warn(String words) {
		if(level <= Logger.WARN) {
			System.out.println(words);
		}
	}

	@Override
	public void msg(String words) {
		if(level <= Logger.MSG) {
			System.out.println(words);
		}
	}
	
	
	

}
