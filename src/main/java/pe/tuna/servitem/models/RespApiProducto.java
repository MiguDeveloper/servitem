package pe.tuna.servitem.models;
import pe.tuna.servicommons.models.Producto;
import java.util.List;

public class RespApiProducto {
    private boolean isSuccess;
    private String message;
    private Producto data;

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

    public Producto getData() {
        return data;
    }

    public void setData(Producto data) {
        this.data = data;
    }
}
