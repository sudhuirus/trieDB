package com.triedb.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.triedb.dataObjects.Node;
import com.triedb.dataObjects.TRIEstructure;

public class TrieDBServiceImpl {
	
	static{
		TRIEstructure triEstructure = new TRIEstructure();
		triEstructure.initialize();
	}
	/*static {
		System.out.println("sudarshan");
		try {
			Scanner sc = new Scanner(new File("D:/CSN_data.txt"));
			TrieDBServiceImpl trieDBServiceImpl = new TrieDBServiceImpl();
			int i = 0;
			while(sc.hasNextLine() && i<10){
				String str = sc.nextLine();
				String key = str.split("\\|")[0];
				String data = str.split("\\|")[1];
				FileWriter fw = new FileWriter(new File("D:/sampleKeys.txt"));
				fw.write(key + "\n");
//				System.out.println("key == " + key + " |  date == " + data);
				i++;
				trieDBServiceImpl.postData(key, data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	private Node trieDS = TRIEstructure.TRIE_STRUCTURE;
	private String keyVal = "";

	public boolean loadData(){
		System.out.println("inside the loaddata");
		try {
			System.out.println(getClass().getClassLoader().getResource("/").getPath());
			File sampleKeys = new File("/etc/sampleKeys.txt");
			System.out.println("sampleKeys.exists() == "+ sampleKeys.exists());
			File dataFile = new File("/etc/CSN_data.txt");
			System.out.println("after load");
			System.out.println("AbsolutePath == "+dataFile.getAbsolutePath());
			System.out.println("Path == "+dataFile.getPath());
			System.out.println("CanonicalPath == "+dataFile.getCanonicalPath());
			System.out.println("dataFile.exists() == " + dataFile.exists());
			FileWriter fw = new FileWriter(sampleKeys);
			Scanner sc = new Scanner(dataFile);
			TrieDBServiceImpl trieDBServiceImpl = new TrieDBServiceImpl();
			System.out.println("Loading data");
			int i = 0;
			while(sc.hasNextLine()){
				String str = sc.nextLine();
				String key = str.split("\\|")[0];
				String data = str.split("\\|")[1];
				fw.write(key + "\n");
//				System.out.println("key == " + key + " |  date == " + data);
				i++;
				trieDBServiceImpl.postData(key, data);
			}
			System.out.println("flushing");
			fw.flush();
			System.out.println("closing");
			fw.close();
			System.out.println("after load");
			System.out.println("AbsolutePath == "+sampleKeys.getAbsolutePath());
			System.out.println("Path == "+sampleKeys.getPath());
			System.out.println("CanonicalPath == "+sampleKeys.getCanonicalPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public String getData(String key) {
		Node node = getNode(trieDS, key);
		String str = node.getJson();
		return str;
	}

	public String postData(String key, String json) {
		
		try{
		Node node = trieDS;
		if(!key.equals("")){
			if(key.length() == 1){
//				Integer j = Integer.parseInt(key);
				String j = key;
				Node nodeNew = new Node(null,json);
				node.getNodeMap().put(j, nodeNew);
			} else {//123456
				for(int i = 0; i < key.length(); i++) {
					String letter = "" + key.charAt(i);
					String j = letter;
					if(node != null && node.getNodeMap() != null && node.getNodeMap().get(j) != null){
						node = node.getNodeMap().get(j);
						if(i==key.length()-1){
							node.setJson(json);
						}
					} else {
						String keyPart = key.substring(i);
						boolean flag = createNodes(node, keyPart, json);
						break;
					}
				}
			}
			return "{'pass' : 'pass'}";
		}
		}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return null;
		
	}

	private boolean createNodes(Node node, String keyPart, String json) {
		if(keyPart == null || keyPart.equals("")){
			node.setJson(json);
			return true;
		}
		//for(int i = 0; i <= keyPart.length(); i++) {
			String letter = "" + keyPart.charAt(0);
			String j = letter;
			Map<String, Node> nodeMap = node.getNodeMap();
			if(nodeMap == null){
				nodeMap = new HashMap<String, Node>();
			} 
			Node subNode = new Node();
			nodeMap.put(j, subNode);
			node.setNodeMap(nodeMap);
			//if(keyPart.length() == 1){
			//	node.setJson(json);
			//	return true;
			//} else {
				createNodes(subNode, keyPart.substring(1), json);
			//}
		return false;
	}

	@SuppressWarnings("unused")
	private Node getNode(Node node, String key) {
		if(key.equals("")){
			return node;
		}
//		for (int i = 0; i <= key.length(); i++) {
			String letter = "" + key.charAt(0);
			String j = letter;
//			System.out.println("letter == " + j);
			if (!key.equals("") && key.length() >= 1 && node.getNodeMap() != null && node.getNodeMap().get(j) != null) {
				Node subNode = node.getNodeMap().get(j);
				return getNode(subNode, key.substring(1));
			} //else return node;
			if(node.getNodeMap() == null){
				return trieDS;
			}
//		}
		return node;
	}

	public static void main1(String[] args) {

		TRIEstructure triEstructure = new TRIEstructure();
		triEstructure.initialize();

		/*Map<Integer, Node> map3 = new HashMap<Integer, Node>();
		Node node4 = new Node(null, "four");
		Node node5 = new Node(null, "five");
		map3.put(4, node4);
		map3.put(5, node5);

		Map<Integer, Node> map2 = new HashMap<Integer, Node>();
		Node node3 = new Node(map3, "three");
		map2.put(3, node3);

		Map<Integer, Node> map1 = new HashMap<Integer, Node>();
		Node node2 = new Node(map2, "two");
		map1.put(2, node2);

		Node node1 = new Node(map1, "one");

		TRIEstructure.TRIE_STRUCTURE.getNodeMap().put(1, node1);*/

		TrieDBServiceImpl trieDBService = new TrieDBServiceImpl();
		
		System.out.println("posting...");
		trieDBService.postData("123456", "six");
		System.out.println("123456 == six");
		trieDBService.postData("123457", "seven");
		System.out.println("123457 == seven");
		trieDBService.postData("4584", "random1");
		System.out.println("4584 == random1");
		trieDBService.postData("4599", "random2");
		System.out.println("4599 == random2");
		System.out.println("posted...");
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str1 = trieDBService.getData("1235");
		System.out.println("result1 == " + str1);
		
		String str2 = trieDBService.getData("123456");
		System.out.println("result2 == " + str2);
		
		String str3 = trieDBService.getData("4599");
		System.out.println("result3 == " + str3);
		
		

	}
	
	public static void main(String[] args) {
//		TRIEstructure triEstructure = new TRIEstructure();
//		triEstructure.initialize();
		
		/*TrieDBServiceImpl trieDBService = new TrieDBServiceImpl();
		trieDBService.postData("123456", "six");
		
		System.out.println(trieDBService.getData("123456"));
		trieDBService.postData("123456", "newsix");
		System.out.println(trieDBService.getData("123456"));
		*/
		try {
			System.out.println("loading file");
			FileWriter fw = new FileWriter(new File("D:/CSN_data_100k_new.csv"));
			String  hrdStr = "wallet_id,wallet_data\n";
			fw.write(hrdStr);
			for(int i=0; i<10000000; i++){
				long l = (new Date()).getTime() + i;
				String str = l + "," + "{\"csn\":"+l+". \"data\":"+l+"}\n";
				fw.write(str);
			}
			fw.flush();
			System.out.println("loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
			e.printStackTrace();
		}
		
		/*try {
			Scanner sc = new Scanner(new File("CSN_data.txt"));
			TrieDBServiceImpl trieDBServiceImpl = new TrieDBServiceImpl();
			int i = 0;
			while(sc.hasNextLine() && i<10){
				String str = sc.nextLine();
				String key = str.split("\\|")[0];
				String data = str.split("\\|")[1];
				System.out.println("key == " + key + " |  date == " + data);
				i++;
//				trieDBServiceImpl.postData(key, data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("date1 = "+(new Date()).getTime());
		
		//System.out.println("date2 = "+(new Date()).getTime());
*/	}
}
