/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distrilabs.filestopdf;

import java.io.IOException;

/**
 *
 * @author Dego
 */
public class LibreOffice {
    
    public static void convertToPdf(String inputFile, String outputFile) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "soffice", 
                    "--convert-to", "pdf",
                    "--outdir", outputFile, inputFile,
                    "--headless"
            );
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Archivo convertido exitosamente a PDF.");
            } else {
                System.err.println("Error al convertir el archivo a PDF. Código de salida: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
        }
    }
    
}
