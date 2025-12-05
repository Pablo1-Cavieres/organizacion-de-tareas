package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JTable tablaLibros;
    private JMenuBar menuBar;

    public VentanaPrincipal() {
        setTitle("Gestor de Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu
        menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        // Tabla
        tablaLibros = new JTable();
        add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        setVisible(true);
    }

    // Métodos para actualizar la tabla desde el controlador
}
