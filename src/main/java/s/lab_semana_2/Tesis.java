/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author sergio
 */

public class Tesis extends MaterialBibliografico {
    private String autorAcademico;

    public Tesis(String codigo, String titulo, int anioPublicacion, String rutaImagen, String autorAcademico) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.autorAcademico = autorAcademico;
    }

    public String getAutorAcademico() {
        return autorAcademico;
    }

    public void setAutorAcademico(String autorAcademico) {
        this.autorAcademico = autorAcademico;
    }

    @Override
    public int getPlazoPrestamo() {
        return 3;
    }

    @Override
    public double getTarifaMultaDiaria() {
        return 3.00;
    }

    @Override
    public String getTipo() {
        return "Tesis";
    }

    @Override
    public String getDatoEspecifico() {
        return "Tesista: " + autorAcademico;
    }
}