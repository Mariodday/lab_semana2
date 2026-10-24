/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author mario
 */

public class Libro extends MaterialBibliografico {
    private String autor;

    public Libro(String codigo, String titulo, int anioPublicacion, String rutaImagen, String autor) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public int getPlazoPrestamo() {
        return 14;
    }

    @Override
    public double getTarifaMultaDiaria() {
        return 1.50;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String getDatoEspecifico() {
        return "Autor: " + autor;
    }
}