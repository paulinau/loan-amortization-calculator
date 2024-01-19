/**
 * 
 */
package com.soa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soa.business.GenerarTablaBusiness;
import com.soa.dto.Response;


/**
 * 
 */
@RestController
public class TablaAmortizacionRest {
    
    @Autowired
    private GenerarTablaBusiness generaTabla;
    
    @GetMapping("/generarTabla/{interes}/{meses}/{monto}")
    public ResponseEntity<Response> verificar(
            @PathVariable Double interes, 
            @PathVariable Integer meses,
            @PathVariable Double monto) {
        
        ResponseEntity<Response> re = null;
        
        Response response = generaTabla.pagoMes(interes, meses, monto);
        
        re = new ResponseEntity<Response>(response, HttpStatus.OK);
        return re;
    }
}
