package pe.tuna.servitem.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.tuna.servitem.models.Item;
import pe.tuna.servicommons.models.Producto;
import pe.tuna.servitem.service.IItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment environment;

    @Value("${configuracion.texto}")
    private String textoPropertie;

    // tambien lo podemos pasar como parametro en la funcion
    @Value("${server.port}")
    private String puerto;

    // itemServiceFeign, itemRestTemplate
    @Autowired
    @Qualifier("itemServiceFeign")
    private IItemService itemService;

    @GetMapping("/")
    public List<Item> listar() {
        return itemService.findAll();
    }


    @HystrixCommand(fallbackMethod = "metodoAlernativoGetItem")
    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item getItemById(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlernativoGetItem(Long id, Integer cantidad) {
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setNombre("Defaul product");
        producto.setId(id);
        producto.setPrecio(1.0);

        item.setProducto(producto);

        return item;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        return itemService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
        return itemService.update(id, producto);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        itemService.delete(id);
    }

    @GetMapping("/obtenerconfig")
    public ResponseEntity<?> obtenerConfig() {
        Map<String, Object> response = new HashMap<>();
        response.put("texto", textoPropertie);
        response.put("puerto", puerto);
        log.info(textoPropertie);
        if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")) {
            response.put("autor.nombre", environment.getProperty("configuracion.autor.nombre"));
            response.put("autor.email", environment.getProperty("configuracion.autor.email"));
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
