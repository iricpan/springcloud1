package cn.bd.microservice.order.service;

import cn.bd.microservice.order.feign.ItemFeignClient;
import cn.bd.microservice.order.pojo.Item;
import cn.bd.microservice.properties.OrderProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {
    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

//    @Value ("${item.url}")
//    private String url;
    @Autowired
    private OrderProperties orderProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ItemFeignClient itemFeignClient;

    /**
     * 添加容错机制
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod ="queryItemByIdFallbackMethod")//添加容错处理方法
    public Item queryItemById(Long id) {
//        return this.restTemplate.getForObject("http://127.0.0.1:8081/item/"
//                + id, Item.class);
    /*    return this.restTemplate.getForObject(this.url
                + id, Item.class);*/
//    return this.restTemplate.getForObject(this.orderProperties.getItem ().getUrl ()
//                + id, Item.class);
  /*      String serviceId = "cnn-microservice-item";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if(instances.isEmpty()){
            return null;
        }*/
        // 为了演示，在这里只获取一个实例
       /* ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);*/
       //使用负载均衡后对上面的方法进行改造
      /*  String serverId = "cnn-microservice-item";
        return this.restTemplate.getForObject ("http://"+serverId+"/item/"+id,Item.class);*/
        /**
         * 使用Feign后对上面的代码进行改造
         */
        return this.itemFeignClient.queryItemById (id);
    }
    public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
        return new Item(id, "查询商品信息出错!", null, null, null);
    }
}
