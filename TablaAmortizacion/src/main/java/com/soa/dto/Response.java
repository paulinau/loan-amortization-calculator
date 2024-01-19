/**
 * 
 */
package com.soa.dto;

import java.util.List;

/**
 * 
 */
public class Response {
    
    private List<Pago> tablaAmortizacion;

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

}
