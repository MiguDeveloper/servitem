package pe.tuna.servitem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.tuna.servitem.models.*;
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
    private static final String URI_BASE_API = "http://servicio-productos";
    private static final String URI_API_PRODUCTOS = URI_BASE_API.concat("/");
    private static final String URI_API_PRODUCTO = URI_BASE_API.concat("/{id}");
    private static final String URI_API_CREAR = URI_BASE_API.concat("/crear");
    private static final String URI_API_ELIMINAR = URI_BASE_API.concat("/eliminar/{id}");

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

    @Override
    public Producto save(Producto producto) {
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        ResponseEntity<RespApiProducto> response = clienteRest.exchange(URI_API_CREAR, HttpMethod.POST, body, RespApiProducto.class);
        RespApiProducto respApiProducto = response.getBody();

        return respApiProducto.getData();
    }

    @Override
    public Producto update(Long id, Producto producto) {
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        ResponseEntity<RespApiProducto> response = clienteRest.exchange(URI_API_PRODUCTO, HttpMethod.PUT,
                body, RespApiProducto.class, pathVariables);
        RespApiProducto respApiProducto = response.getBody();

        return respApiProducto.getData();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        ResponseEntity<ResponseDelete> response = clienteRest.exchange(URI_API_ELIMINAR, HttpMethod.DELETE, null,
                ResponseDelete.class, pathVariables);
    }
}
