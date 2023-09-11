package com.triedb.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.triedb.aws.ec.RedisCache;
import com.triedb.dataObjects.TRIEstructure;
import com.triedb.services.DBServiceImpl;
import com.triedb.services.TrieDBServiceImpl;

@Path("/entry")
public class TrieDBService {
	
	private TrieDBServiceImpl trieDBServiceImpl = new TrieDBServiceImpl();
	private DBServiceImpl dbServiceImpl = new DBServiceImpl();
	private RedisCache cache = new RedisCache();
	
	static {
		TRIEstructure triEstructure = new TRIEstructure();
		triEstructure.initialize();
		
		/*try {
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
		}*/

	}
	

	
	@GET
	public Response getData(@QueryParam("key") String key){
		String result = trieDBServiceImpl.getData(key);
				return Response.status(200).entity(result).build();

	}
	
	@GET
	@Path("/fromdb")
	public Response getDataFromDB(@QueryParam("key") String key){
		try{
			long longKey = Long.parseLong(key);
			String result = dbServiceImpl.getData(longKey);
			return Response.status(200).entity(result).build();
		}catch(Exception e){
			return Response.status(400).entity("INVALID KEY FORMAT").build();
		}

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postData(@QueryParam("key") String key, String payload) {
		System.out.println("key == " + key);
		String result = trieDBServiceImpl.postData(key, payload);
		System.out.println("result == " + result);		
		return Response.status(200).entity(result).build();

	}
	
	@POST
	@Path("/todb")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postDataToDB(@QueryParam("key") String key, String payload) {
		System.out.println("key == " + key);
		long longKey = Long.parseLong(key);
		String result = dbServiceImpl.insertData(longKey, payload);
		System.out.println("result == " + result);		
		return Response.status(200).entity(result).build();

	}

	@DELETE
	public Response deleteData(@QueryParam("key") String key) {

		String result = key;
		return Response.status(200).entity(result).build();

	}

	
	
	@GET
	@Path("/loaddata")
	public Response loadData() {
		boolean result = trieDBServiceImpl.loadData();
		return Response.status(200).entity(result).build();

	}
	
	@POST
	@Path("/redis")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loadRedis(@QueryParam("key") String key, String payload) {
		boolean result = cache.setData(key, payload);
		System.out.println("result == " + result);		
		return Response.status(200).entity(result).build();

	}
	
	@GET
	@Path("/redis")
	public Response getRedis(@QueryParam("key") String key) {
		String result = cache.getData(key);
		System.out.println("result == " + result);		
		return Response.status(200).entity(result).build();

	}
	
}