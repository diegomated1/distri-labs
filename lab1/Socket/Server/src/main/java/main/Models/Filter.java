/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Models;

import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class Filter implements Serializable {
    private static final long serialVersionUID = -8860540110367046379L;
    
    private final String filter;
    private final String q;
    
    public Filter(String filter, String q){
        this.filter = filter;
        this.q = q;
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public String getQ() {
        return this.q;
    }
    
    @Override
    public String toString() {
        return "Filter{" +
                "filter='" + filter + '\'' +
                ", q='" + q + '\'' +
                '}';
    }
    
}
