package pe.tuna.servitem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.tuna.servitem.models.Item;
import pe.tuna.servitem.models.Producto;
import pe.tuna.servitem.models.RespApiProducto;
import pe.tuna.servitem.models.ResponseApiListProducto;
import pe.tuna.servitem.service.IItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("itemRestTemplate")
public class ItemServiceImpl implements IItemService {

    // como hemos implemantado ribbon para el balanceo de carga ya no es necesario poner
    // completo el servidor y el puerto http://localhost:8001
    // ahora solo pondremos el nombre del servicio
    private static final String URI_BASE_API = "http://servicio-productos/api";
    private static final String URI_API_PRODUCTOS = URI_BASE_API.concat("/productos");
    private static final String URI_API_PRODUCTO = URI_BASE_API.concat("/producto/{id}");

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        ResponseApiListProducto apiListProducto = clienteRest.getForObject(URI_API_PRODUCTOS, ResponseApiListProducto.class);
        List<Producto> productos = apiListProducto.getData();
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        RespApiProducto apiProducto = clienteRest.getForObject(URI_API_PRODUCTO, RespApiProducto.class, pathVariables);
        Producto producto = apiProducto.getData();
        return new Item(producto, cantidad);
    }
}
