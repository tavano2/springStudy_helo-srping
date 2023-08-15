package hello.helosrping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 내장된 톰캣 서버로 부트를 실행시켜주는 어노테이션
@SpringBootApplication
public class HeloSrpingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeloSrpingApplication.class, args);
	}

}
