package db;

import redis.clients.jedis.Jedis;

public class ServerRedis {
	private Jedis jedis;

	public void startUp() { // 连接服务器
		jedis = new Jedis("127.0.0.1", 6379);
		// 权限认证
		// jedis.auth("admin");
	}

	public void setString(String key, String value) {
		jedis.set(key, value);
		System.out.println(jedis.get(key));

		jedis.append(key, " is my lover"); // 拼接
		System.out.println(jedis.get(key));
	}

}