/**
 * 
 */
package com.soa.rest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.soa.business.PagarBusiness;
import com.soa.commons.AppException;
import com.soa.dao.PagarDao;
import com.soa.dto.Pago;
import com.soa.dto.ResponseTabla;
import com.soa.dto.Usuario;

/**
 * 
 */
@RestController
public class PagarRest {
    
    @Autowired
    private PagarBusiness pagarBusiness;
    
    /** Objeto de acceso a datos. */
    @Autowired
    private PagarDao pagarDao;
    
    @PutMapping("/pagar")
    public ResponseEntity<ResponseTabla> consultar(
            @RequestBody Usuario usuario)
    {
     
        /** Consultar la tabla de amortizacion. */
        
        // Traemos de la base de datos la informacion del prestamo
        Map<String, Object> resultado = pagarDao.consultarAmortizacion(usuario);

//        System.out.println(resultado);
        double monto = (double) resultado.get("prestamo");
        double interes = (double) resultado.get("interes");
        int meses = (int) resultado.get("meses");
        int mesActual = (int) resultado.get("mesActual");
        double balance = (double) resultado.get("balance");
        double totalIntereses = (double) resultado.get("totalIntereses");
        
        // Variables para checar la tabla de amortizacion
//        int noPago = 0;
        double montoMensual = 0;
        
        //Generar la tabla con los datos de la tarjeta
        String urlTabla = "http://localhost:8082/generarTabla/"
                + interes + "/" + meses + "/" + monto;
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap headers = new HttpHeaders();
        headers.add("noControl", "12345");
        HttpEntity reque = new HttpEntity(usuario, headers);
        
        ResponseEntity<ResponseTabla> responseTabla = 
                restTemplate.exchange(urlTabla, HttpMethod.GET,
                reque, ResponseTabla.class);
        
        List<Pago> tablaAmortizacion = responseTabla.getBody().getTablaAmortizacion();
        
        for (Pago pago : tablaAmortizacion) {
            // Acceder a los campos de cada objeto Pago
//            noPago = pago.getNoPago();
            montoMensual = pago.getPago();  
        }
        
        // Excepcion de que el monto del request no debe ser menor al monto mensual
        try {
            if(usuario.getMonto() < montoMensual)
            {
                throw new AppException("El monto ingresado debe ser mayor o igual"
                        + " que el pago mensual");
            }
        }catch(Exception e)
        {
            responseTabla.getBody().setMensaje(e.getMessage());
            return responseTabla;
        }
        
        // Actualizar el numero del mes
        mesActual++;
        usuario.setMesActual(mesActual);
        
        /** Se regenera la tabla. */
        
        // Formato para redondear a 2 decimales para arriba
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(java.math.RoundingMode.CEILING);
        
        ResponseEntity<ResponseTabla> responseT = null;
        ResponseTabla response = new ResponseTabla();
        
        // Variable donde se calcula la formula para el monto mensual
        double interesMensual = ((double)interes / 100) / 12;
        
        // Separar el deposito 
        double extra = usuario.getMonto() - montoMensual;
        
        List<Pago> tabla = new ArrayList();
        // se añade la lista de los pagos a la respuesta
        response.setTablaAmortizacion(tabla);
        
        // Genera el numero de meses mientras todavía deba dinero
        for(int i = mesActual; balance > 0; i++) {
            Pago pago = new Pago();
            pago.setNoPago(i);
            
            //Interes es el prestamo * tasa de interes
            double interesMes = balance * interesMensual;
            
            //Intereses pagados por mes acumulados
            totalIntereses += interesMes;

            //Capital es pago mensual - interes
            double capital = 0;

            //En caso de que el ultimo pago deba ser menor.
            if (balance < montoMensual) {
                capital = balance;
                interesMes = balance * interesMensual;
                montoMensual = capital + interesMes;
            } else {
                capital = montoMensual - interesMes;
            }
            // Agregar el pago extra del mes si lo hay.
            if(i == mesActual)
            {
                pago.setExtra(extra);
            }
            
            //Prestamo (Lo que falta por pagar) - capital - extra
            balance = balance - capital - pago.getExtra();
            
            pago.setPago(Double.parseDouble(df.format(montoMensual)));
            pago.setInteres(Double.parseDouble(df.format(interesMes)));
            pago.setCapital(Double.parseDouble(df.format(capital)));
            pago.setBalance(Double.parseDouble(df.format(balance)));
            pago.setTotal(Double.parseDouble(df.format(totalIntereses)));
            
            tabla.add(pago);
        }
        
        // Si el balance es 0, entonces no se genera la tabla porque ya no debe nada
        if(!tabla.isEmpty())
        {
         // Volver a setear el balance
            double primerBalance = tabla.get(0).getBalance();
            usuario.setBalance(primerBalance);
            
            double primerInteresTotal = tabla.get(0).getTotal();
            usuario.setTotalIntereses(primerInteresTotal);
            
            double checarCapital = tabla.get(0).getCapital();
            
            try {
                if (checarCapital != 0)
                {
                 // Se efectua el pago
                    pagarBusiness.pagar(usuario);
                    response.setMensaje("Mes " + mesActual + 
                            " pagado correctamente");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }catch(Exception e)
            {
                responseTabla.getBody().setMensaje(e.getMessage());
                return responseTabla;
            }
            
            responseT = new ResponseEntity<>(response, HttpStatus.OK);
            return responseT;
            
        } else
        {
            response.setMensaje("Has terminado de pagar tu deuda");
            // Se le agrega la tabla original de pagos 
            response.setTablaAmortizacion(tablaAmortizacion);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}