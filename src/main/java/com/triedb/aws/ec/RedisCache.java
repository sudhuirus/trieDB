package com.triedb.aws.ec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.common.net.HostAndPort;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache {

	private JedisPool jedisPool;
	private JedisPoolConfig jedisConfig;
	private Jedis jedis;

	public RedisCache() {
		try {
			jedisConfig = new JedisPoolConfig();
			jedisConfig.setMaxTotal(50);
			jedisConfig.setTestOnCreate(true);
			jedisConfig.setTestWhileIdle(true);
			HostAndPort endpoint = HostAndPort.fromString("cep-redis.ypreey.ng.0001.usw1.cache.amazonaws.com:6379");

			// HostAndPort endpoint =
			// HostAndPort.fromString("test2.etmvy3.ng.0001.usw2.cache.amazonaws.com:6379");
			System.out.println("Host=" + endpoint.getHostText() + ", port=" + endpoint.getPort());
			jedisPool = new JedisPool(jedisConfig, endpoint.getHostText(), endpoint.getPort());

			jedis = jedisPool.getResource();
		} catch (Exception e) {
			System.out.println("Error configuring Jedis........................");
			e.printStackTrace();
		}
	}

	public boolean setData(String key, String value) {
		try {
			jedis.connect();
			System.out.println("Successfully connect ..");

			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			System.out.println("Error connecting to Jedis........................");
			e.printStackTrace();
			return false;
		}

	}

	public String getData(String key) {
		try {
			jedis.connect();
			System.out.println("Successfully connect ..");

			return jedis.get(key);
		} catch (Exception e) {
			System.out.println("Error connecting to Jedis........................");
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {

		JedisPool jedisPool;
		try {
			JedisPoolConfig jedisConfig = new JedisPoolConfig();
			jedisConfig.setMaxTotal(50);
			jedisConfig.setTestOnCreate(true);
			jedisConfig.setTestWhileIdle(true);

			// cep account
			HostAndPort endpoint = HostAndPort.fromString("cep-redis.ypreey.ng.0001.usw1.cache.amazonaws.com:6379");

			// HostAndPort endpoint =
			// HostAndPort.fromString("test2.etmvy3.ng.0001.usw2.cache.amazonaws.com:6379");
			System.out.println("Host=" + endpoint.getHostText() + ", port=" + endpoint.getPort());
			jedisPool = new JedisPool(jedisConfig, endpoint.getHostText(), endpoint.getPort());

			final Jedis jedis = jedisPool.getResource();

			BufferedReader br = null;
			/*
			 * try { jedis.connect(); System.out.println(
			 * "Successfully connect ..");
			 * 
			 * String sCurrentLine; br = new BufferedReader(new
			 * FileReader("C:\\Users\\t_manim\\Desktop\\TriDBPrj\\CSN_data1.txt"
			 * )); //br = new BufferedReader(new
			 * FileReader("C:\\Users\\t_manim\\Desktop\\sampl.txt")); String
			 * timeStamp = new
			 * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance()
			 * .getTime()); System.out.println("Before loading timestamp == "
			 * +timeStamp); while ((sCurrentLine = br.readLine()) != null) {
			 * String[] strArr = sCurrentLine.split("\\|"); jedis.set(strArr[0],
			 * strArr[1]);
			 * 
			 * } String timeStamp1 = new
			 * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance()
			 * .getTime()); System.out.println("After loading timestamp == "
			 * +timeStamp1);
			 * 
			 * 
			 * } catch (IOException e) { e.printStackTrace(); } finally { try {
			 * if (br != null)br.close(); jedis.disconnect(); jedis.close(); }
			 * catch (IOException ex) { ex.printStackTrace(); } }
			 */

			try {
				jedis.connect();
				System.out.println("Successfully connect ..");

				jedis.set("testA", "firstval");
				// jedis.get("testA");
				System.out.println(jedis.get("testA"));
				// return new RetriableCacheTask<T>(config.getMaxRetries(),
				// config.getRetryInterval(), cacheTask).call(jedis);
			} finally {
				jedis.disconnect();
				jedis.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
