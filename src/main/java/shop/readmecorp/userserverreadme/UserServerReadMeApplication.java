package shop.readmecorp.userserverreadme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserServerReadMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServerReadMeApplication.class, args);
	}

}

