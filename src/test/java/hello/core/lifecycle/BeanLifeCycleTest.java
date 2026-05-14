package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // destroyMethod의 디폴트 값은 (inferred)로 되어있다.
        // 이는 '추론'이라는 의미로,
        // 라이브러리는 대부분 close, shutdown으로 되어있기 때문에
        // 이러한 메서드들을 자동으로 호출해준다.
        // 추론 기능을 사용하고 싶지 않다면 빈 문자열을 입력하면 된다. (예시: destroyMethod = "")
        // @Bean(initMethod = "init", destroyMethod = "close")

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
