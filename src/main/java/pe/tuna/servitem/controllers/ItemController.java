package pe.tuna.servitem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.servitem.models.Item;
import pe.tuna.servitem.service.IItemService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    @Qualifier("itemServiceFeign")
    private IItemService itemService;

    @GetMapping("/items")
    public List<Item> listar() {
        return itemService.findAll();
    }

    @GetMapping("/item/{id}/cantidad/{cantidad}")
    public Item getItemById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad){
        return itemService.findById(id,cantidad);
    }
}
