/**
 * 
 */
package com.soa.dto;

import java.util.List;

import com.google.gson.Gson;

/**
 * 
 */
public class ResponseTabla {
    
    /** Lista que almacena la tabla de amortizacion. */
    private List<Pago> tablaAmortizacion;
    
    /** Mensajes para el usuario. */
    private String mensaje;
    
    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the tablaAmortizacion
     */
    public List<Pago> getTablaAmortizacion() {
        return tablaAmortizacion;
    }

    /**
     * @param tablaAmortizacion the tablaAmortizacion to set
     */
    public void setTablaAmortizacion(List<Pago> tablaAmortizacion) {
        this.tablaAmortizacion = tablaAmortizacion;
    }
    
    /**
     * Representacion String del objeto.
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
