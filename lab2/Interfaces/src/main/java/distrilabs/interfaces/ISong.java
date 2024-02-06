/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package distrilabs.interfaces;

import java.io.Serializable;

/**
 *
 * @author 000430063
 */
public interface ISong extends Serializable {
    String getTitle();
    String getGenre();
    String getAuthor();
    String getLanguage();
    String getLaunchYear();
    @Override
    String toString();
}
