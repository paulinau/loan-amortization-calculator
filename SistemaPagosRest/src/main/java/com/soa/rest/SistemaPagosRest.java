/**
 * 
 */
package com.soa.rest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.soa.commons.AppException;
import com.soa.commons.Env;
import com.soa.commons.LogConfiguration;
import com.soa.dto.Pago;
import com.soa.dto.Request;
import com.soa.dto.ResponseTabla;

/**
 * 
 */
@RestController
public class SistemaPagosRest {
    /** Logger. */
    private static final Logger LOGGER = LogManager
            .getLogger(SistemaPagosRest.class);
    
    /** Mensaje de error configurable. */
    @Value("${mensaje.error}")
    private String mensaje;
    
    @GetMapping("/mostrarPago")
    public ResponseEntity<ResponseTabla> pagaCredito(
            @RequestBody Request request) {
        
        // Iniciar el log, generamos diferentes id 
        // para cada uno de los request
        LogConfiguration.initLog(UUID.randomUUID().toString());
        LOGGER.info("Request: {}", request);
        
        try {
            // Realiza el pago, y actualiza el credito.
            String urlPago = "http://localhost:8084/pagar";
            RestTemplate restTemplate = new RestTemplate();
            
            MultiValueMap headers = new HttpHeaders();
            headers.add("noControl", "12345");
            HttpEntity reque = new HttpEntity(request, headers);
            
            ResponseEntity<ResponseTabla> responsePago = 
                    restTemplate.exchange(urlPago, HttpMethod.PUT,
                    reque, ResponseTabla.class);
            
            LOGGER.info("Se realizo exitosamente el pago.");
            
            return responsePago;
            
        } catch (AppException e) {
            // Lanza la excepcion directamente
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error al hacer el pago.", e.getMessage());
            throw new AppException(e.getMessage());
        }
    }
}
