/**
 * 
 */
package com.soa.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soa.dao.PagarDao;
import com.soa.dto.Respuesta;
import com.soa.dto.Usuario;

/**
 * Clase para cobrar el prestamo.
 */
@Component
public class PagarBusiness {

    /** Objeto de acceso a datos. */
    @Autowired
    private PagarDao pagarDao;

    /**
     * Actualiza el saldo de la tarjeta.
     * @param usuario 
     * @return
     */
    public Respuesta pagar(Usuario usuario) {
        Respuesta respuesta = new Respuesta();
        try {
            // Consultar el saldo actual (del usuario en la base de datos)
            double saldoActual = pagarDao.consultar(usuario);

            // Realizar la lógica de pago
            double nuevoSaldo = saldoActual - usuario.getMonto();
            usuario.setMonto(nuevoSaldo);
            
            // Actualiza la base de datos
            pagarDao.actualizar(usuario);
            
            respuesta.setMessage("Pago exitoso");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setMessage("Pago rechazado");
        }

        return respuesta;
    }

}
