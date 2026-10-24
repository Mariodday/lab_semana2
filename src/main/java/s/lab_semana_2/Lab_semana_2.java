/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package s.lab_semana_2;

/**
 *
 * @author mario
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Lab_semana_2 extends JFrame {
    private Biblioteca biblioteca;

    private JComboBox<String> cmbTipoMaterial;
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAnio;
    private JLabel lblDatoEspecifico;
    private JTextField txtDatoEspecifico;
    private JTextField txtRutaImagen;
    private JButton btnExaminar;
    private JButton btnRegistrar;

    private JTextField txtBuscarCodigo;
    private JButton btnBuscar;
    private JLabel lblResultadoBusqueda;

    private JTextField txtPrestamoCodigo;
    private JButton btnPrestar;

    private JTextField txtDevolucionCodigo;
    private JButton btnDevolver;

    private JTable tblMateriales;
    private DefaultTableModel modeloTabla;

    public Lab_semana_2() {
        this.biblioteca = new Biblioteca();
        initComponents();
        cargarDatosIniciales();
        actualizarTabla();
    }

    private void initComponents() {
        setTitle("Sistema de Gestión de Biblioteca");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane pestanias = new JTabbedPane();

        JPanel panelRegistro = crearPanelRegistro();
        JPanel panelOperaciones = crearPanelOperaciones();
        JPanel panelInventario = crearPanelInventario();

        pestanias.addTab("Registro de Materiales", panelRegistro);
        pestanias.addTab("Préstamos y Búsqueda", panelOperaciones);
        pestanias.addTab("Inventario General", panelInventario);

        add(pestanias, BorderLayout.CENTER);
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipo = new JLabel("Tipo de Material:");
        String[] tipos = {"Libro", "Revista", "Tesis"};
        cmbTipoMaterial = new JComboBox<String>(tipos);

        JLabel lblCod = new JLabel("Código Único:");
        txtCodigo = new JTextField(20);

        JLabel lblTit = new JLabel("Título:");
        txtTitulo = new JTextField(20);

        JLabel lblAn = new JLabel("Año de Publicación:");
        txtAnio = new JTextField(20);

        lblDatoEspecifico = new JLabel("Nombre del Autor:");
        txtDatoEspecifico = new JTextField(20);

        JLabel lblImg = new JLabel("Imagen (Opcional):");
        txtRutaImagen = new JTextField(15);
        txtRutaImagen.setEditable(false);
        btnExaminar = new JButton("Examinar...");

        JPanel panelImg = new JPanel(new BorderLayout(5, 0));
        panelImg.add(txtRutaImagen, BorderLayout.CENTER);
        panelImg.add(btnExaminar, BorderLayout.EAST);

        btnRegistrar = new JButton("Registrar Material");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblTipo, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(cmbTipoMaterial, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblCod, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblTit, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(txtTitulo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblAn, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(txtAnio, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(lblDatoEspecifico, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(txtDatoEspecifico, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(lblImg, gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(panelImg, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnRegistrar, gbc);

        cmbTipoMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) cmbTipoMaterial.getSelectedItem();
                if ("Libro".equals(seleccionado)) {
                    lblDatoEspecifico.setText("Nombre del Autor:");
                } else if ("Revista".equals(seleccionado)) {
                    lblDatoEspecifico.setText("Número de Edición:");
                } else if ("Tesis".equals(seleccionado)) {
                    lblDatoEspecifico.setText("Autor Académico / Tesista:");
                }
            }
        });

        btnExaminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif"));
                int resultado = fileChooser.showOpenDialog(Lab_semana_2.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivo = fileChooser.getSelectedFile();
                    txtRutaImagen.setText(archivo.getAbsolutePath());
                }
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarRegistro();
            }
        });

        return panel;
    }

    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel panelBuscar = new JPanel(new BorderLayout(5, 5));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Búsqueda Recursiva de Material"));
        JPanel subBuscarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subBuscarPanel.add(new JLabel("Código:"));
        txtBuscarCodigo = new JTextField(15);
        btnBuscar = new JButton("Buscar");
        subBuscarPanel.add(txtBuscarCodigo);
        subBuscarPanel.add(btnBuscar);
        lblResultadoBusqueda = new JLabel("Ingrese un código para consultar.");
        panelBuscar.add(subBuscarPanel, BorderLayout.NORTH);
        panelBuscar.add(lblResultadoBusqueda, BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarBusqueda();
            }
        });

        JPanel panelPrestamo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 20));
        panelPrestamo.setBorder(BorderFactory.createTitledBorder("Préstamo de Material"));
        panelPrestamo.add(new JLabel("Código del Material:"));
        txtPrestamoCodigo = new JTextField(15);
        btnPrestar = new JButton("Registrar Préstamo");
        panelPrestamo.add(txtPrestamoCodigo);
        panelPrestamo.add(btnPrestar);

        btnPrestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrestamo();
            }
        });

        JPanel panelDevolucion = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 20));
        panelDevolucion.setBorder(BorderFactory.createTitledBorder("Devolución de Material"));
        panelDevolucion.add(new JLabel("Código del Material:"));
        txtDevolucionCodigo = new JTextField(15);
        btnDevolver = new JButton("Registrar Devolución");
        panelDevolucion.add(txtDevolucionCodigo);
        panelDevolucion.add(btnDevolver);

        btnDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarDevolucion();
            }
        });

        panel.add(panelBuscar);
        panel.add(panelPrestamo);
        panel.add(panelDevolucion);

        return panel;
    }

    private JPanel crearPanelInventario() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Imagen", "Código", "Título", "Tipo", "Año", "Dato Específico", "Disponibilidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class;
                }
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblMateriales = new JTable(modeloTabla);
        tblMateriales.setRowHeight(65);
        tblMateriales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < tblMateriales.getColumnCount(); i++) {
            tblMateriales.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(tblMateriales);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void ejecutarRegistro() {
        String codigo = txtCodigo.getText().trim();
        String titulo = txtTitulo.getText().trim();
        String anioTexto = txtAnio.getText().trim();
        String datoEsp = txtDatoEspecifico.getText().trim();
        String rutaImg = txtRutaImagen.getText().trim();
        String tipo = (String) cmbTipoMaterial.getSelectedItem();

        if (codigo.isEmpty() || titulo.isEmpty() || anioTexto.isEmpty() || datoEsp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(anioTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El año de publicación debe ser un número entero válido.", "Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (anio <= 0 || anio > anioActual) {
            JOptionPane.showMessageDialog(this, "El año de publicación no puede ser menor a 1 ni posterior al año actual (" + anioActual + ").", "Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (biblioteca.buscarMaterialRecursivo(codigo) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un material registrado con el código: " + codigo, "Código Duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MaterialBibliografico nuevoMaterial = null;
        if ("Libro".equals(tipo)) {
            nuevoMaterial = new Libro(codigo, titulo, anio, rutaImg, datoEsp);
        } else if ("Revista".equals(tipo)) {
            int edicion;
            try {
                edicion = Integer.parseInt(datoEsp);
                if (edicion <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de edición debe ser mayor a cero.", "Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El número de edición debe ser un valor numérico.", "Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            nuevoMaterial = new Revista(codigo, titulo, anio, rutaImg, edicion);
        } else if ("Tesis".equals(tipo)) {
            nuevoMaterial = new Tesis(codigo, titulo, anio, rutaImg, datoEsp);
        }

        if (nuevoMaterial != null && biblioteca.agregarMaterial(nuevoMaterial)) {
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Material registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtCodigo.setText("");
            txtTitulo.setText("");
            txtAnio.setText("");
            txtDatoEspecifico.setText("");
            txtRutaImagen.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el material.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarBusqueda() {
        String codigo = txtBuscarCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un código para realizar la búsqueda.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MaterialBibliografico mat = biblioteca.buscarMaterialRecursivo(codigo);
        if (mat != null) {
            String estado = mat.isDisponible() ? "Disponible" : "Prestado";
            String info = "<html><b>Código:</b> " + mat.getCodigo() + "<br>"
                    + "<b>Título:</b> " + mat.getTitulo() + "<br>"
                    + "<b>Tipo:</b> " + mat.getTipo() + "<br>"
                    + "<b>Año:</b> " + mat.getAnioPublicacion() + "<br>"
                    + "<b>Detalle:</b> " + mat.getDatoEspecifico() + "<br>"
                    + "<b>Plazo de Préstamo:</b> " + mat.getPlazoPrestamo() + " días naturales<br>"
                    + "<b>Tarifa Multa/Día:</b> " + String.format("%.2f", mat.getTarifaMultaDiaria()) + " €<br>"
                    + "<b>Estado:</b> " + estado + "</html>";
            lblResultadoBusqueda.setText(info);
            JOptionPane.showMessageDialog(this, "Material encontrado: " + mat.getTitulo(), "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            lblResultadoBusqueda.setText("No se encontró ningún material con el código: " + codigo);
            JOptionPane.showMessageDialog(this, "No existe ningún material registrado con el código ingresado.", "Sin Coincidencias", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarPrestamo() {
        String codigo = txtPrestamoCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el código del material que desea prestar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MaterialBibliografico mat = biblioteca.buscarMaterialRecursivo(codigo);
        if (mat == null) {
            JOptionPane.showMessageDialog(this, "No existe un material con el código especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!mat.isDisponible()) {
            JOptionPane.showMessageDialog(this, "El material seleccionado ya se encuentra prestado y no está disponible.", "No Disponible", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Calendar fechaActual = Calendar.getInstance();
        Prestamo prestamo = biblioteca.registrarPrestamo(codigo, fechaActual);

        if (prestamo != null) {
            actualizarTabla();
            txtPrestamoCodigo.setText("");
            String mensaje = "Préstamo registrado exitosamente.\n\n"
                    + "Material: " + mat.getTitulo() + " (" + mat.getTipo() + ")\n"
                    + "Fecha de Préstamo: " + prestamo.getFechaPrestamoFormateada() + "\n"
                    + "Plazo asignado: " + mat.getPlazoPrestamo() + " días\n"
                    + "Fecha prevista de devolución: " + prestamo.getFechaPrevistaFormateada();
            JOptionPane.showMessageDialog(this, mensaje, "Confirmación de Préstamo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void ejecutarDevolucion() {
        String codigo = txtDevolucionCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el código del material para devolver.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MaterialBibliografico mat = biblioteca.buscarMaterialRecursivo(codigo);
        if (mat == null) {
            JOptionPane.showMessageDialog(this, "No existe un material registrado con el código especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Prestamo prestamo = biblioteca.buscarPrestamoActivoPorCodigo(codigo);
        if (prestamo == null) {
            JOptionPane.showMessageDialog(this, "El material no tiene ningún préstamo activo registrado.", "Sin Préstamo Activo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Calendar fechaDevolucion = Calendar.getInstance();
        int retraso = prestamo.calcularDiasRetraso(fechaDevolucion);
        double multa = mat.calcularMulta(retraso);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea confirmar la devolución del material '" + mat.getTitulo() + "'?",
                "Confirmar Devolución",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        biblioteca.registrarDevolucion(codigo, fechaDevolucion);
        actualizarTabla();
        txtDevolucionCodigo.setText("");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaHoyStr = sdf.format(fechaDevolucion.getTime());

        String mensajeDevolucion = "Devolución registrada correctamente.\n\n"
                + "Material: " + mat.getTitulo() + "\n"
                + "Fecha prevista: " + prestamo.getFechaPrevistaFormateada() + "\n"
                + "Fecha de devolución: " + fechaHoyStr + "\n"
                + "Días de retraso: " + retraso + "\n"
                + "Multa calculada: " + String.format("%.2f", multa) + " €";

        JOptionPane.showMessageDialog(this, mensajeDevolucion, "Devolución Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < biblioteca.getMateriales().size(); i++) {
            MaterialBibliografico mat = biblioteca.getMateriales().get(i);
            Object[] fila = new Object[]{
                mat.getImagen(),
                mat.getCodigo(),
                mat.getTitulo(),
                mat.getTipo(),
                mat.getAnioPublicacion(),
                mat.getDatoEspecifico(),
                mat.isDisponible() ? "Disponible" : "Prestado"
            };
            modeloTabla.addRow(fila);
        }
    }

    private void cargarDatosIniciales() {
        biblioteca.agregarMaterial(new Libro("LIB-001", "Cien Años de Soledad", 1967, "", "Gabriel García Márquez"));
        biblioteca.agregarMaterial(new Revista("REV-101", "National Geographic", 2023, "", 450));
        biblioteca.agregarMaterial(new Tesis("TES-501", "Algoritmos Recursivos Distribuidos", 2024, "", "Carlos Mendoza"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Lab_semana_2().setVisible(true);
            }
        });
    }
}
