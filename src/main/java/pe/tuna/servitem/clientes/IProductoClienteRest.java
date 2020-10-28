package pe.tuna.servitem.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.tuna.servitem.models.RespApiProducto;
import pe.tuna.servitem.models.ResponseApiListProducto;

@FeignClient(name = "servicio-productos")
public interface IProductoClienteRest {
    @GetMapping("/api/productos")
    public ResponseApiListProducto listar();

    @GetMapping("/api/producto/{id}")
    public RespApiProducto getByIdProducto(@PathVariable Long id);
}
