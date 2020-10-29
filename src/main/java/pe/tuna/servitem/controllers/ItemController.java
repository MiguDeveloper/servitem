package pe.tuna.servitem.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.servitem.models.Item;
import pe.tuna.servitem.models.Producto;
import pe.tuna.servitem.service.IItemService;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("itemServiceFeign")
    private IItemService itemService;

    @GetMapping("/")
    public List<Item> listar() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlernativoGetItem")
    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item getItemById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad){
        return itemService.findById(id,cantidad);
    }

    public Item metodoAlernativoGetItem(Long id, Integer cantidad){
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setNombre("Defaul product");
        producto.setId(id);
        producto.setPrecio(1.0);

        item.setProducto(producto);

        return item;
    }
}
