package com.helloboot;

import com.config.MySpringBootApplication;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@MySpringBootApplication
public class DataSourceApplication {
	private final JdbcTemplate jdbcTemplate;

	public DataSourceApplication(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	void init(){
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
	}

	public static void main(String[] args) {
		SpringApplication.run(DataSourceApplication.class, args);
	}

}
