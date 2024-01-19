/**
 * 
 */
package com.soa.dto;

import com.google.gson.Gson;

/**
 * Clase que modela la informacion resumida de una persona.
 */
public class Respuesta {
    
    /** Nombre de respuesta. */
    private String message;
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString()
    {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
