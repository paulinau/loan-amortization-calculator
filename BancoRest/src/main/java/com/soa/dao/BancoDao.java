/**
 * 
 */
package com.soa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soa.dto.Usuario;

/**
 * Capa de acceso a datos.
 */
@Repository
public class BancoDao {
    
    /**
     * Objeto especializado en acceso a la BD.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Agrega un prestamo a la BD.
     * @param usuario Usuario a insertar.
     */
    public void insertar(Usuario usuario) 
    {
        jdbcTemplate.execute("INSERT INTO usuario(noTarjeta, prestamo, interes, "
                + "meses, mesActual, balance, totalIntereses, saldo) VALUES ('" + usuario.getNoTarjeta() + 
                "', '" + usuario.getMonto() + "', '" + usuario.getInteres() + "', '"
                + usuario.getMeses() + "', 0, '"  + usuario.getMonto() + "', '0 ',"
                + " '" +  usuario.getSaldo() + "')");
    }
}