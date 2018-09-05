package com.taotao.order.dao;

public interface JedisClient {
	
	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long incr(String key);//加1
	long expire(String key, int second);//设置有效时间
	long ttl(String key);//剩余有效时间
	long del(String key);
	long hdel(String hkey, String key);
}
