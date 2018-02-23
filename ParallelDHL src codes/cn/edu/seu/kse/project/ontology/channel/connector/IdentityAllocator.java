package cn.edu.seu.kse.project.ontology.channel.connector;

public class IdentityAllocator {
	
	private Integer id = 2;
	
	public IdentityAllocator(Integer initialId) {
		this.id = initialId;
	}
	
	public Integer allocate(){
		return id ++ ;
	}

}
