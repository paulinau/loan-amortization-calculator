/**
 * 
 */
package com.soa.business;



import java.util.Random;


import org.springframework.stereotype.Component;


/**
 * 
 */
@Component
public class CrediticioBusiness {
    
    public boolean verificarCrediticio() {

        Random random = new Random();

        // Generar un número aleatorio entre 300 (inclusivo) y 851 (exclusivo)
        int numeroAleatorio = random.nextInt(551) + 300;

        return numeroAleatorio > 500;
    }
        
    
}
