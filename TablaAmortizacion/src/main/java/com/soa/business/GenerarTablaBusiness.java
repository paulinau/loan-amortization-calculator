/**
 * 
 */
package com.soa.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soa.dto.Pago;
import com.soa.dto.Response;


/**
 * 
 */
@Service
public class GenerarTablaBusiness {
    
    /**
     * Generar la tabla de amortizacion
     * @param interes Tasa de interes
     * @param meses Meses para completar el pago
     * @param monto Cantidad de prestamo
     * @return
     */
    public Response pagoMes(double interes, int meses, double monto) {
        
        // Formato para redondear a 2 decimales para arriba
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(java.math.RoundingMode.CEILING);
        
        Response response = new Response();
        
        // Variable donde se calcula la formula para el monto mensual
        double result = 0;
        double interesMensual = ((double)interes / 100) / 12;

        result = (monto * interesMensual)/
                (1-(Math.pow(1+interesMensual, -meses)));
        
        result =  Double.parseDouble(df.format(result));
        
        double prestamo = monto;
        double totalIntereses = 0;
        
        List<Pago> tabla = new ArrayList();
        // se añade la lista de los pagos a la respuesta
        response.setTablaAmortizacion(tabla);
        
        for(int i = 1; prestamo >= 0; i++) {
            Pago pago = new Pago();
            pago.setNoPago(i);
            
            //Interes es el prestamo * tasa de interes
            double interesMes = prestamo * interesMensual;
            
            //Capital es pago mensual - interes
            double capital = result-interesMes;
            
            //Prestamo (Lo que falta por pagar) - capital - extra
            prestamo = prestamo - capital - pago.getExtra();
            
            //Intereses pagados por mes acumulados
            totalIntereses += interesMes;
            
            pago.setInteres(Double.parseDouble(df.format(interesMes)));
            pago.setPago(result);
            pago.setCapital(Double.parseDouble(df.format(capital)));
            pago.setBalance(Double.parseDouble(df.format(prestamo)));
            pago.setTotal(Double.parseDouble(df.format(totalIntereses)));
            
            tabla.add(pago);
        }
        
        return response;
    }
}
