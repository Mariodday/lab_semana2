/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author sergio
 */

public class Revista extends MaterialBibliografico {
    private int numeroEdicion;

    public Revista(String codigo, String titulo, int anioPublicacion, String rutaImagen, int numeroEdicion) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public int getPlazoPrestamo() {
        return 7;
    }

    @Override
    public double getTarifaMultaDiaria() {
        return 0.75;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String getDatoEspecifico() {
        return "Edición N°: " + numeroEdicion;
    }
}