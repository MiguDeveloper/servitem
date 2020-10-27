package pe.tuna.servitem.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.tuna.servitem.models.RespApiProducto;
import pe.tuna.servitem.models.ResponseApiListProducto;

@FeignClient(name = "servicio-productos", url = "localhost:8001/api")
public interface IProductoClienteRest {
    @GetMapping("/productos")
    public ResponseApiListProducto listar();

    @GetMapping("/producto/{id}")
    public RespApiProducto getByIdProducto(@PathVariable Long id);
}
