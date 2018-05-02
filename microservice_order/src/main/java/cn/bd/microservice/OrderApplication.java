package cn.bd.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients//开启对支持Feign
@EnableHystrix//开取容错机制
@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {
    @Bean
    @LoadBalanced//开启负载均衡
    public RestTemplate getRestTemplate(){
       // return new RestTemplate ();
        return new RestTemplate (new OkHttp3ClientHttpRequestFactory ());
    }

    public static void main(String[] args) {
        SpringApplication.run (OrderApplication.class,args);
    }
}
