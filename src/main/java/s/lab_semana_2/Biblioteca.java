/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author sergio
 */

import java.util.ArrayList;
import java.util.Calendar;

public class Biblioteca {
    private ArrayList<MaterialBibliografico> materiales;
    private ArrayList<Prestamo> prestamos;

    public Biblioteca() {
        this.materiales = new ArrayList<MaterialBibliografico>();
        this.prestamos = new ArrayList<Prestamo>();
    }

    public boolean agregarMaterial(MaterialBibliografico material) {
        if (material == null) {
            return false;
        }
        if (buscarMaterialRecursivo(material.getCodigo()) != null) {
            return false;
        }
        materiales.add(material);
        return true;
    }

    public MaterialBibliografico buscarMaterialRecursivo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        return buscarRecursivoAux(codigo.trim(), 0);
    }

    private MaterialBibliografico buscarRecursivoAux(String codigo, int indice) {
        if (indice >= materiales.size()) {
            return null;
        }
        MaterialBibliografico actual = materiales.get(indice);
        if (actual.getCodigo().equalsIgnoreCase(codigo)) {
            return actual;
        }
        return buscarRecursivoAux(codigo, indice + 1);
    }

    public Prestamo buscarPrestamoActivoPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo p = prestamos.get(i);
            if (p.isActivo() && p.getMaterial().getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }
        return null;
    }

    public Prestamo registrarPrestamo(String codigo, Calendar fechaPrestamo) {
        MaterialBibliografico material = buscarMaterialRecursivo(codigo);
        if (material == null || !material.isDisponible()) {
            return null;
        }
        Prestamo nuevoPrestamo = new Prestamo(material, fechaPrestamo);
        prestamos.add(nuevoPrestamo);
        return nuevoPrestamo;
    }

    public double registrarDevolucion(String codigo, Calendar fechaDevolucion) {
        Prestamo prestamo = buscarPrestamoActivoPorCodigo(codigo);
        if (prestamo == null) {
            return -1.0;
        }
        return prestamo.registrarDevolucion(fechaDevolucion);
    }

    public ArrayList<MaterialBibliografico> getMateriales() {
        return materiales;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }
}