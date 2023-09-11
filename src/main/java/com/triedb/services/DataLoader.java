package com.triedb.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class DataLoader {

	public static void main(String[] args) {
		/*try {
			System.out.println("loading file");
			FileWriter fw = new FileWriter(new File("/CSN_data_100k.txt"));
			for(int i=0; i<10000000; i++){
				long l = (new Date()).getTime() + i;
				String str = l + "|" + "{\"mainBalance\":"+(i/10000)+", \"promoBalance\":"+(i/100000)+"}\n";
				fw.write(str);
			}
			fw.flush();
			System.out.println("loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
			e.printStackTrace();
		}*/
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/automedic", "root", "admin");
            PreparedStatement ps = con.prepareStatement("insert into wallet_data values(?,?)");
            
			File dataFile = new File("D:/etc/CSN_data.txt");
			Scanner sc = new Scanner(dataFile);
			while(sc.hasNextLine()){
				String str = sc.nextLine();
				String key = str.split("\\|")[0];
				long l = Long.valueOf(key);
				String data = str.split("\\|")[1];
				ps.setLong(1, l);
	            ps.setString(2, data);
	            ps.executeUpdate();
//	        	System.out.println("key == " + key + " |  date == " + data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
