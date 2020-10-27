package pe.tuna.servitem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.tuna.servitem.clientes.IProductoClienteRest;
import pe.tuna.servitem.models.Item;
import pe.tuna.servitem.models.Producto;
import pe.tuna.servitem.models.RespApiProducto;
import pe.tuna.servitem.models.ResponseApiListProducto;
import pe.tuna.servitem.service.IItemService;

import java.util.List;
import java.util.stream.Collectors;


@Service("itemServiceFeign")
public class ItemServiceFeign implements IItemService {

    @Autowired
    private IProductoClienteRest productoClienteRest;

    @Override
    public List<Item> findAll() {
        ResponseApiListProducto apiListProducto = productoClienteRest.listar();
        List<Producto> productos = apiListProducto.getData();
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        RespApiProducto apiProducto = productoClienteRest.getByIdProducto(id);
        Producto producto = apiProducto.getData();
        return new Item(producto, cantidad);
    }
}
