/**
 * 
 */
package com.soa.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soa.dto.Usuario;

/**
 * Capa de acceso a datos.
 */
@Repository
public class PagarDao {
    
    /**
     * Objeto especializado en acceso a la BD.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Agrega un prestamo a la BD.
     * @param usuario Usuario a insertar.
     */
    public void actualizar(Usuario usuario) 
    {
        jdbcTemplate.execute("UPDATE usuario SET saldo = "
                + usuario.getMonto() + ", mesActual =" + usuario.getMesActual()
                + ", balance = " + usuario.getBalance()  + ", totalIntereses ="
                + usuario.getTotalIntereses() + "  WHERE noTarjeta = " 
                + usuario.getNoTarjeta());
    }
    
    public double consultar(Usuario usuario)
    {
        Double saldo = jdbcTemplate.queryForObject("SELECT saldo FROM usuario WHERE "
                + "noTarjeta = "+ usuario.getNoTarjeta(), Double.class);
        return saldo;
    }
    
    /**
     * Consulta la informacion del prestamo en la base de datos
     * @param request
     * @return
     */
    public Map<String, Object> consultarAmortizacion(Usuario usuario) { 
        String sql = "SELECT prestamo, interes, meses, mesActual, balance, totalIntereses FROM usuario WHERE noTarjeta = ?";
        return jdbcTemplate.queryForMap(sql, usuario.getNoTarjeta());
    }
}
