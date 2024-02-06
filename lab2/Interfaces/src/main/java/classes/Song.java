/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package classes;

import distrilabs.interfaces.ISong;

/**
 *
 * @author 000430063
 */
public class Song implements ISong {
    private final String Title;
    private final String Genre;
    private final String Author;
    private final String Language;
    private final String LaunchYear;
    
    public Song(
        String title,
        String genre,
        String author,
        String language,
        String launchYear
    ) {
        this.Title = title;
        this.Genre = genre;
        this.Author = author;
        this.Language = language;
        this.LaunchYear = launchYear;
    }
    
    @Override
    public String getTitle() {
        return Title;
    }

    @Override
    public String getGenre() {
        return Genre;
    }

    @Override
    public String getAuthor() {
        return Author;
    }

    @Override
    public String getLanguage() {
        return Language;
    }

    @Override
    public String getLaunchYear() {
        return LaunchYear;
    }
    
    @Override
    public String toString() {
        return "Song{" +
                "Title='" + Title + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Author='" + Author + '\'' +
                ", Language='" + Language + '\'' +
                ", LaunchYear='" + LaunchYear + '\'' +
                '}';
    }
}
