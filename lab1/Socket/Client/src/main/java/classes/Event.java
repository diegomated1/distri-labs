/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.Serializable;

/**
 *
 * @author Diego
 * @param <T>
 */
public class Event<T> implements Serializable {
    private final String name;
    private final T data;
    
    public Event(String name, T data) {
        this.name = name;
        this.data = data;
    }
    
    public String getName() {
        return this.name;
    }
    
    public T getData() {
        return this.data;
    }
}