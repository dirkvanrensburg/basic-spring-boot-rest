package com.demo;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo")
@EnableCaching

// Option 1 - Disable auto configuration of Hazelcast
// --------
//@EnableAutoConfiguration(exclude = {
//        HazelcastAutoConfiguration.class,
//})
public class Application {

    //Option 2 - Manually bring the existing instance into the application context
//    @Bean
//    public HazelcastInstance getInstance() {
//        return Hazelcast.getHazelcastInstanceByName("test");
//    }


    //Options 3 - setting `spring.hazelcast.config` property in application.properties

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        //Just some basic tests to save us calling the REST endpoint.
        //In a separate thread, call the service every 7 seconds and log the response.
        CalculatorService service = ctx.getBean(CalculatorService.class);
        new Thread(() -> {
            CalculatorService.CalcResult prevResult = null;
            int count = 0;
            int step = 7;

            try {Thread.sleep(2000);} catch(Exception e){}

            while(true) {
                CalculatorService.CalcResult result = service.sum(1d,2d);
                if (!result.equals(prevResult)) {
                    prevResult = result;
                    count = 0;

                    System.out.println("Cache miss!!");
                }

                System.out.println(count + " seconds since created. \t Result: " + result);

                try {Thread.sleep(step * 1000);} catch(Exception e){}
                count += step;
            }

        }).run();


    }
}