/**
 * 
 */
package com.soa.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soa.dao.BancoDao;
import com.soa.dto.Respuesta;
import com.soa.dto.Usuario;

/**
 * Clase para concatenación de datos personales.
 */
@Component
public class BancoBusiness {

    /** Objeto de acceso a datos. */
    @Autowired
    private BancoDao bancoDao;

    /**
     * Agrega el prestamo a la base de datos.
     * 
     * @param persona Usuario recibido.
     * @return Mensaje personalizado
     */
    public Respuesta add(Usuario usuario) {
        Respuesta respuesta = new Respuesta();
        try {
            // Se añade el dinero del prestamo con el saldo actual de la tarjeta
            double saldoTotal = usuario.getMonto() + usuario.getSaldo();
            usuario.setSaldo(saldoTotal);
            
            bancoDao.insertar(usuario);
            respuesta.setMessage("Prestamo disponible");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setMessage("Prestamo rechazado");
        }

        return respuesta;
    }

}
