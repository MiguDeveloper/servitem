package pe.tuna.servitem.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pe.tuna.servicommons.models.Producto;
import pe.tuna.servitem.models.RespApiProducto;
import pe.tuna.servitem.models.ResponseApiListProducto;
import pe.tuna.servitem.models.ResponseDelete;

@FeignClient(name = "servicio-productos")
public interface IProductoClienteRest {
    //"/api/productos"
    @GetMapping("/")
    public ResponseApiListProducto listar();

    //"/api/producto/{id}")
    @GetMapping("/{id}")
    public RespApiProducto getByIdProducto(@PathVariable Long id);

    @PostMapping("/crear")
    public RespApiProducto crear(@RequestBody Producto producto);

    @PutMapping("/{id}")
    public RespApiProducto editar(@RequestBody Producto producto, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    public ResponseDelete eliminar(@PathVariable Long id);
}
