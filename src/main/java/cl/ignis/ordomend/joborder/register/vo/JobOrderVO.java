package cl.ignis.ordomend.joborder.register.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public class JobOrderVO {

    @NotBlank(message = "codigoOrden is required")
    private String codigoOrden;

    @NotBlank(message = "idTecnico is required")
    private String idTecnico;

    @NotBlank(message = "idCliente is required")
    private String idCliente;

    @NotBlank(message = "detalleOrden is required")
    private String detalleOrden;

    @NotNull(message = "fechaRecepcion is required")
    @PastOrPresent(message = "fechaRecepcion cannot be in the future")
    private Date fechaRecepcion;

    @NotNull(message = "plazoResolucion is required")
    @Min(value = 1, message = "plazoResolucion must be at least 1")
    private Integer plazoResolucion;

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(String detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Integer getPlazoResolucion() {
        return plazoResolucion;
    }

    public void setPlazoResolucion(Integer plazoResolucion) {
        this.plazoResolucion = plazoResolucion;
    }
}
