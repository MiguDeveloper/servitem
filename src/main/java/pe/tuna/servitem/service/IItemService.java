package pe.tuna.servitem.service;

import pe.tuna.servitem.models.Item;
import pe.tuna.servicommons.models.Producto;

import java.util.List;

public interface IItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    public Producto save(Producto producto);
    public Producto update(Long id, Producto producto);
    public void delete(Long id);
}
