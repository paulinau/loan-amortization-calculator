package com.soa.dto;

import com.google.gson.Gson;

/**
 * Clase que modela la informacion de un usuario.
 */
public class Usuario {
    
    /** Numero de la tarjeta del usuario. */
    private String noTarjeta;
    
    /** Prestamo del usuario. */
    private double monto;

    /** Interes total de la deuda. */
    private double interes;
    
    /** Meses restantes de la deuda. */
    private int meses;
    
    /** Saldo disponible de la cuenta. */
    private double saldo;
    
    /** Total de interes de la deuda */
    private double totalIntereses;
    
    
    /**
     * @return the totalIntereses
     */
    public double getTotalIntereses() {
        return totalIntereses;
    }

    /**
     * @param totalIntereses the totalIntereses to set
     */
    public void setTotalIntereses(double totalIntereses) {
        this.totalIntereses = totalIntereses;
    }

    /**
     * @return the noTarjeta
     */
    public String getNoTarjeta() {
        return noTarjeta;
    }

    /**
     * @param noTarjeta the noTarjeta to set
     */
    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }
    
    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the interes
     */
    public double getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(double interes) {
        this.interes = interes;
    }

    /**
     * @return the meses
     */
    public int getMeses() {
        return meses;
    }

    /**
     * @param meses the meses to set
     */
    public void setMeses(int meses) {
        this.meses = meses;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
