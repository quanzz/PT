package cn.edu.seu.kse.project.materializer.reasoner.mat;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.toolkit.ConcurrentHashSet;

public class SWDGraph {
	
	private Set<DHLClassAssertion> nodes = null;
	
	private Map<DHLClassAssertion, Set<DHLClassAssertion>> parentToChildren = null;

	private Map<DHLClassAssertion, Set<DHLClassAssertion>> childToParents = null;
	
	public SWDGraph() {
		nodes = new ConcurrentHashSet<DHLClassAssertion>();
		parentToChildren = 
				new ConcurrentHashMap<DHLClassAssertion, Set<DHLClassAssertion>>();
		childToParents =
				new ConcurrentHashMap<DHLClassAssertion, Set<DHLClassAssertion>>();
		
	}
	
	public void addNode(DHLClassAssertion node){
		if(!nodes.contains(node)) {
			nodes.add(node);
		}
	}
	
	public void addEdge(DHLClassAssertion parent, DHLClassAssertion child) {
		addNode(parent);
		addNode(child);
		
		if(parentToChildren.containsKey(parent)){
			Set<DHLClassAssertion> children = parentToChildren.get(parent);
			children.add(child);
		
		} else {
			Set<DHLClassAssertion> children = new ConcurrentHashSet<DHLClassAssertion>();
			children.add(child);
			parentToChildren.put(parent, children);
		}
		
		if(childToParents.containsKey(child)){
			Set<DHLClassAssertion> parents = childToParents.get(child);
			parents.add(parent);
		
		} else {
			Set<DHLClassAssertion> parents = new ConcurrentHashSet<DHLClassAssertion>();
			parents.add(parent);
			childToParents.put(child, parents);
		}
		
	}
	
	public boolean hasNode(DHLClassAssertion node){
		if(nodes.contains(node)) return true;
		return false;
	}
	
	public boolean hasEdge(DHLClassAssertion parent, DHLClassAssertion child) {
		if(!hasNode(parent)) return false;
		
		Set<DHLClassAssertion> children = parentToChildren.get(parent);
		if(children.contains(child)) return true;
		
		return false;
	}
	
	public Set<DHLClassAssertion> transfer(DHLClassAssertion assertion) {
		Set<DHLClassAssertion> updatedParents = 
				new ConcurrentHashSet<DHLClassAssertion>();
		
		if(hasNode(assertion)) {
			Set<DHLClassAssertion> children = parentToChildren.get(assertion);
			Set<DHLClassAssertion> parents = childToParents.get(assertion);
			
			for(DHLClassAssertion parent : parents) {
				for(DHLClassAssertion child : children) {

					if(!hasEdge(parent, child)) {
						addEdge(parent, child);
						updatedParents.add(parent);
					}
				}
			}
		}
		
		return updatedParents;
	}
	
	public Set<DHLClassAssertion> getChildren(DHLClassAssertion assertion) {
		if(parentToChildren.containsKey(assertion)) {
			return parentToChildren.get(assertion);
		} else {
			return null;
		}
	}
}
