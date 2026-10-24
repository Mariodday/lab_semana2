/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author sergio
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Prestamo {
    private MaterialBibliografico material;
    private Calendar fechaPrestamo;
    private Calendar fechaPrevistaDevolucion;
    private Calendar fechaDevolucionReal;
    private boolean activo;
    private double multaPagada;

    public Prestamo(MaterialBibliografico material, Calendar fechaPrestamo) {
        this.material = material;
        this.fechaPrestamo = (Calendar) fechaPrestamo.clone();
        normalizarFecha(this.fechaPrestamo);

        this.fechaPrevistaDevolucion = (Calendar) this.fechaPrestamo.clone();
        this.fechaPrevistaDevolucion.add(Calendar.DAY_OF_YEAR, material.getPlazoPrestamo());
        normalizarFecha(this.fechaPrevistaDevolucion);

        this.fechaDevolucionReal = null;
        this.activo = true;
        this.multaPagada = 0.0;
        this.material.setDisponible(false);
    }

    private void normalizarFecha(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    public int calcularDiasRetraso(Calendar fechaActual) {
        Calendar actualNormalizada = (Calendar) fechaActual.clone();
        normalizarFecha(actualNormalizada);

        if (!actualNormalizada.after(fechaPrevistaDevolucion)) {
            return 0;
        }

        long milisegundosDiferencia = actualNormalizada.getTimeInMillis() - fechaPrevistaDevolucion.getTimeInMillis();
        long dias = Math.round(milisegundosDiferencia / (1000.0 * 60 * 60 * 24));
        return (int) dias;
    }

    public double registrarDevolucion(Calendar fechaDevolucion) {
        if (!activo) {
            return 0.0;
        }
        this.fechaDevolucionReal = (Calendar) fechaDevolucion.clone();
        normalizarFecha(this.fechaDevolucionReal);

        int diasRetraso = calcularDiasRetraso(this.fechaDevolucionReal);
        this.multaPagada = material.calcularMulta(diasRetraso);

        this.activo = false;
        this.material.setDisponible(true);
        return this.multaPagada;
    }

    public MaterialBibliografico getMaterial() {
        return material;
    }

    public Calendar getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Calendar getFechaPrevistaDevolucion() {
        return fechaPrevistaDevolucion;
    }

    public Calendar getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public boolean isActivo() {
        return activo;
    }

    public double getMultaPagada() {
        return multaPagada;
    }

    public String getFechaPrestamoFormateada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaPrestamo.getTime());
    }

    public String getFechaPrevistaFormateada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaPrevistaDevolucion.getTime());
    }
}