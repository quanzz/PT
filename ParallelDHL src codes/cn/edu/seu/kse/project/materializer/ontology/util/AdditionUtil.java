package cn.edu.seu.kse.project.materializer.ontology.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiom;
import cn.edu.seu.kse.project.toolkit.ConcurrentHashSet;

public class AdditionUtil {
	
	public static void addElement(
			Integer key, 
			DHLAxiom axiom, 
			Map<Integer, Set<DHLAxiom>> map) {
		
		if(map.containsKey(key)) {
			Set<DHLAxiom> axiomSet = map.get(key);
			axiomSet.add(axiom);
		} else {
			Set<DHLAxiom> axiomSet = new HashSet<DHLAxiom>();
			axiomSet.add(axiom);
			map.put(key, axiomSet);
		}
	}
	
	public static void addElement(
			Integer key1,
			Integer key2,
			DHLAxiom axiom, 
			Map<Integer, Map<Integer, Set<DHLAxiom>>> map) {
		
		if(map.containsKey(key1)) {
			Map<Integer, Set<DHLAxiom>> subMap = map.get(key1);
			
			if(subMap.containsKey(key2)) {
				Set<DHLAxiom> subSet= subMap.get(key2);
				subSet.add(axiom);
			
			} else {
				Set<DHLAxiom> subSet = new ConcurrentHashSet<DHLAxiom>();
				subSet.add(axiom);
				subMap.put(key2, subSet);
			}
		} else {
			Set<DHLAxiom> subSet = new ConcurrentHashSet<DHLAxiom>();
			subSet.add(axiom);
			
			Map<Integer, Set<DHLAxiom>> subMap = 
					new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
			subMap.put(key2, subSet);
			
			map.put(key1, subMap);
		}
		
	}
	
	public static void addElement(
			Integer key1,
			Integer key2,
			Integer key3,
			DHLAxiom axiom, 
			Map<Integer, Map<Integer, Map<Integer, Set<DHLAxiom>>>> map) {
		
		if(map.containsKey(key1)) {
			Map<Integer, Map<Integer, Set<DHLAxiom>>> subMap = map.get(key1);
			
			if(subMap.containsKey(key2)) {
				Map<Integer, Set<DHLAxiom>> subsubMap = subMap.get(key2);
				
				if(subsubMap.containsKey(key3)) {
					Set<DHLAxiom> set = subsubMap.get(key3);
					set.add(axiom);
				} else {
					Set<DHLAxiom> set = new ConcurrentHashSet<DHLAxiom>();
					set.add(axiom);
					
					subsubMap.put(key3, set);
				}
				
			} else {
				Set<DHLAxiom> set = new ConcurrentHashSet<DHLAxiom>();
				set.add(axiom);
				
				Map<Integer, Set<DHLAxiom>> subsubMap = 
						new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
				subsubMap.put(key3, set);
				
				subMap.put(key2, subsubMap);				
			}
		} else {
			Set<DHLAxiom> set = new ConcurrentHashSet<DHLAxiom>();
			set.add(axiom);
			
			Map<Integer, Set<DHLAxiom>> subsubMap = 
					new ConcurrentHashMap<Integer, Set<DHLAxiom>>();
			subsubMap.put(key3, set);
			
			Map<Integer, Map<Integer, Set<DHLAxiom>>> subMap = 
					new  ConcurrentHashMap<Integer, Map<Integer, Set<DHLAxiom>>>();
			subMap.put(key2, subsubMap);
			
			map.put(key1, subMap);
		}
		
	}
	
	public static void addElement(Integer element, Set<Integer> elementSet){
		elementSet.add(element);
	}
	
	public static void addElement(DHLAxiom axiom, Set<DHLAxiom> axiomSet){
		axiomSet.add(axiom);
	}

}
