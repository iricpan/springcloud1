package cn.bd.microservice.order.feign;

import cn.bd.microservice.order.pojo.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cnn-microservice-item")// 申明这是一个Feign客户端，并且指明服务id
public interface ItemFeignClient {

    /**
     *  这里定义了类似于SpringMVC用法的方法，就可以进行RESTful的调用了
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id")Long id);
}
