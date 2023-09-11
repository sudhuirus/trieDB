package com.triedb.aws.ec;

public class RedisToJava {

	   public static void main(String[] args) { 
	      //Connecting to Redis server on localhost 
//	      Jedis jedis = new Jedis("tridb-redis-poc-001.a4srwi.0001.usw2.cache.amazonaws.com", 6379, 3600); 
	      System.out.println("Connection to server sucessfully"); 
	      //check whether server is running or not 
//	      System.out.println("Server is running: "+jedis.ping()); 
	   }

}
