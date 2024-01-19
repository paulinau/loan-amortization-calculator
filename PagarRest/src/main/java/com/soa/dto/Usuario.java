package com.soa.dto;

import com.google.gson.Gson;

/**
 * Clase que modela la informacion de un usuario.
 */
public class Usuario {
    
    /** Numero de la tarjeta del usuario. */
    private String noTarjeta;
    
    /** Saldo disponible de la cuenta. */
    private double monto;

    /** Mes pagado */
    private int mesActual;
    
    /** Balance calculado */
    private double balance;
    
    /** Total intereses de la deuda */
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
     * @return the mesActual
     */
    public int getMesActual() {
        return mesActual;
    }

    /**
     * @param mesActual the mesActual to set
     */
    public void setMesActual(int mesActual) {
        this.mesActual = mesActual;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
