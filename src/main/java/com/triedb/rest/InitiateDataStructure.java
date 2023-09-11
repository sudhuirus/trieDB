package com.triedb.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.triedb.dataObjects.TRIEstructure;
import com.triedb.services.TrieDBServiceImpl;


public class InitiateDataStructure 
	implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener has started");
		TRIEstructure triEstructure = new TRIEstructure();
		triEstructure.initialize();
		
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
//				trieDBServiceImpl.postData(key, data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



