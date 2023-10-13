package stockm;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("stockm.dao")
public class Training1Application {

	public static void main(String[] args) {
		SpringApplication.run(Training1Application.class, args);
	}

}
