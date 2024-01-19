/**
 * 
 */
package com.soa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.business.CrediticioBusiness;
import com.soa.dto.Response;


/**
 * 
 */
@RestController
public class CrediticioRest {
    
    @Autowired
    private CrediticioBusiness crediticioBusiness;
    
    @GetMapping("/crediticio")
    public ResponseEntity<Response> verificar() {
        
        ResponseEntity<Response> re = null;
        Response respuesta = new Response();
        boolean darCredito = crediticioBusiness.verificarCrediticio();
        respuesta.setDarCredito(darCredito);
        re = new ResponseEntity<Response>(respuesta, HttpStatus.OK);
        return re;
    }

}
