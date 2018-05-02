package cn.bd.microservice.action;

import cn.bd.microservice.pojo.Item;
import cn.bd.microservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("item/{id}")
    public Item queryItemById(@PathVariable("id") Long id){
        return itemService.queryItemById (id);
    }
}
