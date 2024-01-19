/**
 * 
 */
package com.soa.dto;

/**
 * 
 */
public class Pago {
    
    /** Numero de pago (mes) que se va a pagar. */
    private int noPago;
    
    /** Cantidad mensual que se va a pagar */
    private double pago;
    
    /** Interes por mes de la deuda */
    private double interes;
    
    /** Lo que se lleva pagado */
    private double capital;
    
    /** Monto adicional */
    private double extra;
    
    /** Monto de la deuda que falta por pagar */
    private double balance;
    
    /** Total de interes */
    private double total;
    
    /**
     * @return the noPago
     */
    public int getNoPago() {
        return noPago;
    }
    /**
     * @param noPago the noPago to set
     */
    public void setNoPago(int noPago) {
        this.noPago = noPago;
    }
    /**
     * @return the pago
     */
    public double getPago() {
        return pago;
    }
    /**
     * @param pago the pago to set
     */
    public void setPago(double pago) {
        this.pago = pago;
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
     * @return the capital
     */
    public double getCapital() {
        return capital;
    }
    /**
     * @param capital the capital to set
     */
    public void setCapital(double capital) {
        this.capital = capital;
    }
    /**
     * @return the extra
     */
    public double getExtra() {
        return extra;
    }
    /**
     * @param extra the extra to set
     */
    public void setExtra(double extra) {
        this.extra = extra;
    }
    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
}
