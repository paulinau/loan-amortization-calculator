/**
 * 
 */
package com.soa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soa.business.BancoBusiness;
import com.soa.dto.Respuesta;
import com.soa.dto.Usuario;

/**
 * 
 */
@RestController
public class BancoRest {
    
    @Autowired
    private BancoBusiness bancoBusiness;
    
    @PostMapping("/deposito")
    public ResponseEntity<Respuesta> consultar(
            @RequestBody Usuario usuario)
    {
        ResponseEntity<Respuesta> re = null;
        Respuesta respuesta = bancoBusiness.add(usuario);
        re = new ResponseEntity<>(respuesta, HttpStatus.OK);
        return re;
    }
}
