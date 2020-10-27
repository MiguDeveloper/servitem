package pe.tuna.servitem.service;

import pe.tuna.servitem.models.Item;

import java.util.List;

public interface IItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
}
