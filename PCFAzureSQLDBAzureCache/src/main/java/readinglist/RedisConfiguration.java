package readinglist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import redis.clients.jedis.JedisShardInfo;

@Configuration
@EnableRedisRepositories

public class RedisConfiguration {

	@Bean
	RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		JedisShardInfo shardInfo = new JedisShardInfo("<HOSTNAME>", 6379);
		shardInfo.setPassword("<PASSWORD>"); 
		jedisConFactory.setShardInfo(shardInfo);

		return jedisConFactory;

	}

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(connectionFactory);

		return template;
	}

}
