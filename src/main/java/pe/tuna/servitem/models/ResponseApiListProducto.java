package pe.tuna.servitem.models;

import java.util.List;
import pe.tuna.servicommons.models.Producto;

public class ResponseApiListProducto {
    private boolean isSuccess;
    private String message;
    private List<Producto> data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Producto> getData() {
        return data;
    }

    public void setData(List<Producto> data) {
        this.data = data;
    }
}
