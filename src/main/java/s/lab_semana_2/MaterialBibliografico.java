/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author mario
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MaterialBibliografico {
    private String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private ImageIcon imagen;

    public MaterialBibliografico(String codigo, String titulo, int anioPublicacion, String rutaImagen) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.imagen = cargarImagen(rutaImagen);
    }

    private ImageIcon cargarImagen(String ruta) {
        if (ruta != null && !ruta.trim().isEmpty()) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                try {
                    BufferedImage img = ImageIO.read(archivo);
                    if (img != null) {
                        Image escalada = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        return new ImageIcon(escalada);
                    }
                } catch (Exception e) {
                }
            }
        }
        BufferedImage dummy = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
        return new ImageIcon(dummy);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public int getPlazoPrestamo() {
        return 0;
    }

    public double getTarifaMultaDiaria() {
        return 0.0;
    }

    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0.0;
        }
        return diasRetraso * getTarifaMultaDiaria();
    }

    public String getTipo() {
        return "General";
    }

    public String getDatoEspecifico() {
        return "N/A";
    }
}