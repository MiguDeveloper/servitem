package pe.tuna.servitem.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.tuna.servitem.models.RespApiProducto;
import pe.tuna.servitem.models.ResponseApiListProducto;

@FeignClient(name = "servicio-productos")
public interface IProductoClienteRest {
    //"/api/productos"
    @GetMapping("/")
    public ResponseApiListProducto listar();

    //"/api/producto/{id}")
    @GetMapping("/{id}")
    public RespApiProducto getByIdProducto(@PathVariable Long id);
}
