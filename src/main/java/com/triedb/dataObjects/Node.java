package com.triedb.dataObjects;

import java.io.Serializable;
import java.util.Map;

public class Node implements Serializable{

	private Map<String, Node> nodeMap;
	private String json;
	
	public Node(Map<String, Node> nodeMap, String json){
		this.nodeMap = nodeMap;
		this.json = json;
	}
	
	public Node(){
	}

	public Map<String, Node> getNodeMap() {
		return nodeMap;
	}

	public void setNodeMap(Map<String, Node> nodeMap) {
		this.nodeMap = nodeMap;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	
	
}
