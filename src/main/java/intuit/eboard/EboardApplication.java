package intuit.eboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EboardApplication {

  public static void main(String[] args) {
    SpringApplication.run(EboardApplication.class, args);
  }

}
