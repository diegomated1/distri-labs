/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.Models;

/**
 *
 * @author Diego
 */
public interface ISong {
    String getTitle();
    String getGenre();
    String getAuthor();
    String getLanguage();
    String getLaunchYear();
    @Override
    String toString();
}