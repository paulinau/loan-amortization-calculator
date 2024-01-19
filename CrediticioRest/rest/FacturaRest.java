/**
 * 
 */
package com.soa.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.dto.Entrada;
import com.soa.dto.Factura;
import com.soa.dto.Response;

/**
 * 
 */
@RestController
public class FacturaRest {
    
 
    @PostMapping("/factura")
    public ResponseEntity<Response> factura(
            @RequestBody Entrada entrada) {
        ResponseEntity<Response> re = null;
        
        Response response = new Response();
        //Entrada entrada = new Entrada();
        //Factura factura = new Factura();
                
        List<Integer> productos = entrada.getFactura().getProductos();        
        int resultado = productos.stream().mapToInt(Integer::intValue).sum();
        
        response.setTotal(resultado);        
        re = new ResponseEntity<Response>(response, HttpStatus.OK);
        return re;
    }
}
