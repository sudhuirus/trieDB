package com.triedb.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DBServiceImpl {

	private Connection con;
	
	
	public DBServiceImpl(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://10.41.44.57:3312/imagine_poc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "im_admin", "BIa1#NYp");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/automedic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getData(long walletId){
		try {
			PreparedStatement ps = this.con.prepareStatement("select wallet_datacol from wallet_data where idwallet_data = ?");
			ps.setLong(1, walletId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		DBServiceImpl dbServiceImpl = new DBServiceImpl();
//		System.out.println(dbServiceImpl.getData(1486571056275l));
//	}

	public String insertData(long longKey, String payload) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = this.con.prepareStatement("insert into wallet_data values (?,?)");
			ps.setLong(1, longKey);
			ps.setString(2, payload);
			int result = ps.executeUpdate();
			return "SUCCESS";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		DBServiceImpl dbServiceImpl = new DBServiceImpl();
		try {
			System.out.println("loading file");
//			FileWriter fw = new FileWriter(new File("D:/CSN_data_100k_new.csv"));
//			String  hrdStr = "wallet_id,wallet_data\n";
//			fw.write(hrdStr);
			PreparedStatement ps = dbServiceImpl.con.prepareStatement("insert into wallet_data values (?,?)");
			Date d1 = new Date();
			long dl1 = d1.getTime();
			for(int i=0; i<1000; i++){
				long l = (new Date()).getTime() + i;
				String str = "{\"csn\":"+l+", \"data\":"+l+"}";	
				ps.setLong(1, l);
				ps.setString(2, str);
				int result = ps.executeUpdate();
//				fw.write(str);
			}
			Date d2 = new Date();
			long dl2 = d2.getTime();
			System.out.println("total time taken ==== " + (dl2-dl1) + "milliseconds");
			dbServiceImpl.con.close();
//			fw.flush();
			System.out.println("loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				dbServiceImpl.con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
