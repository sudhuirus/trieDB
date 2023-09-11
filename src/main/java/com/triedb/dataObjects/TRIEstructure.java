package com.triedb.dataObjects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TRIEstructure implements Serializable{
	
	public static Node TRIE_STRUCTURE;
	
	public void initialize(){
		String start = "START";
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		for(int i=0; i<=10; i++){
			nodeMap.put(String.valueOf(i), new Node());
		}
		for(char alphabet = 'a'; alphabet <= 'z';alphabet++){
			nodeMap.put(String.valueOf(alphabet), new Node());
		}
		
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++){
			nodeMap.put(String.valueOf(alphabet), new Node());
		}
		TRIE_STRUCTURE = new Node(nodeMap,start);
	}
	
	public static void main(String[] args) {
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++){
			  System.out.println(alphabet);
			  }
	}
	
}
