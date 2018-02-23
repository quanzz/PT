package uba;

public class GeneratorPTU {
	
	private int univNum = 0;
	private int articleNum = 0;
	private int chainNum = 0;
	private int articleNumEachChain = 0;
	private int articleNumEachChainEachUniv = 0;
	
	private String dir;
	
	private String CLASS_COLLEGE_ARTICLE = "CollegeArticle";
	private String CLASS_COLLEGE_PUBLICATION = "CollegePublication";
	private String CLASS_COLLEGE_CONFERENCE = "CollegeConference";
	private String CLASS_COLLEGE_SESSION = "CollegeSession";
	private String PROPERTY_CITE = "cite";
	private String PROPERTY_REFERTTO = "referTo";
	
	private RDFWriter writer;
			
	
	public GeneratorPTU(int univNum, int articleNum, int chainNum, String dir) {
		this.univNum = univNum;
		this.articleNum = articleNum;
		this.chainNum = chainNum;
		this.articleNumEachChain = this.articleNum/chainNum + 1;
		this.articleNumEachChainEachUniv = articleNumEachChain/univNum + 1;
		this.writer = new OWLWriter();
		this.dir = dir;
	}
	
	private void generate() {
		
		for(int c = 0; c < chainNum; c++) {
			for(int i = 0; i < univNum; i ++) {
				generateArt(i,c);
			}
		}
	}
	
	private void generateArt(int univIndex, int chainIndex) {
		String fileName = dir + "/" + getFileName(univIndex, chainIndex) + ".owl";
		System.out.println("Start generating: " + fileName);
        writer.startFile(fileName);
        for(int j = 0; j < articleNumEachChainEachUniv; j++) {
			String art = getArtId(univIndex,chainIndex,j);
			String citedArt = getCitedArtId(univIndex,chainIndex,j);
			
			if(!citedArt.equalsIgnoreCase("null")) {		
				writer.startAnArticle(CLASS_COLLEGE_PUBLICATION, art);
				writer.addPropertyPTU(PROPERTY_CITE, citedArt);
				writer.addPropertyPTU(PROPERTY_REFERTTO, citedArt);
				writer.endAnArticle(CLASS_COLLEGE_PUBLICATION);
			}
			
			 if(univIndex == univNum - 1 && j == articleNumEachChainEachUniv - 1) {
				writer.startANewArticle(CLASS_COLLEGE_ARTICLE, art);
				writer.endANewArticle(CLASS_COLLEGE_ARTICLE);
			 }
		}
        writer.endFile();
	}
	
	private String getFileName(int univIndex, int chanIndex) {
		return "University" + univIndex + "-RefChain"+ chanIndex + "-ArticleCitationList";
	}
	
	private String getArtId(int univIndex, int chainIndex, int relativeArtIndex) {
		String id;
		id = getArtURI(univIndex, chainIndex, relativeArtIndex);
		return id;
	}
	
	private String getCitedArtId(int univIndex, int chainIndex, int relativeArtIndex) {
		String id = "null";
		if(relativeArtIndex == articleNumEachChainEachUniv - 1) {
			if(univIndex == univNum - 1) {
				id = "null";
			} else {
				id = getArtURI(univIndex + 1, chainIndex, 0);
			}
		} else {
			id = getArtURI(univIndex, chainIndex, relativeArtIndex + 1);
		}
		return id;
	}
	
	private String getArtURI(int univIndex, int chainIndex, int relativeArtIndex) {
		String name;
		name = "http://www.ArticleList.University" + univIndex + ".edu/RefChain" + chainIndex + "/Article" + relativeArtIndex;
		return name;
	}
	
	public static void main(String[] args) {
//		String dir = "C:/Users/Spring/Desktop/Work/0_学校事务/1_PT_J/experiments/ptu-owl";
//		int univNum = 10;
//		int articleNum = 100;
//		int chainNum = 2;
//		
//		GeneratorPTU generatorPTU = new GeneratorPTU(univNum, articleNum, chainNum, dir);
//		generatorPTU.generate();
		
		
		if(args.length != 4) {
			System.out.println("arguments wrong.");
		} else {
			int univNum = Integer.valueOf(args[0]);
			int articleNum = Integer.valueOf(args[1]);
			int chainNum = Integer.valueOf(args[2]);
			String dir = args[3];
			GeneratorPTU generatorPTU = new GeneratorPTU(univNum, articleNum, chainNum, dir);
			generatorPTU.generate();
		}
	}

}
