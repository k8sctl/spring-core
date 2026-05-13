package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자A가 10000원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: 사용자B가 20000원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A가 주문 금액 조회
        // int price = statefulService1.getPrice();
        // System.out.println("price = " + price);
        System.out.println("userAPrice = " + userAPrice);

        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    @Configuration
    static class TestConfig {

        @Bean
        StatefulService statefulService() {
            return new StatefulService();
        }
    }
}