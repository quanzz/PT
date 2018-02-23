package cn.edu.seu.kse.project.logger;

/**
 * 
 * This interface handles how to output the informations.
 * 
 * @author Zhangquan Zhou
 *
 */
public interface Logger {
	
	public static final int DEBUG = 0;
	
	public static final int WARN = 1;
	
	public static final int MSG = 3;
	
	public static final int TURNOFF = 2;
	
	
	public void launch(int level);
	
	public void warn(String words);
	
	public void debug(String words);
	
	public void msg(String words);
	
	public String command();
	
}
