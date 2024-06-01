package Coordinate.coordikittyBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@SpringBootApplication
public class CoordikittyBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoordikittyBeApplication.class, args);
	}

}