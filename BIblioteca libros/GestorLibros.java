import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

// Clase Libro debe implementar Serializable para poder guardarse
class Libro implements Serializable {
    private String titulo;
    private String autor;
    private int cantidad;

    public Libro(String titulo, String autor, int cantidad) {
        this.titulo = titulo;
        this.autor = autor;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Cantidad: " + cantidad;
    }
}

public class GestorBiblioteca {
    private ArrayList<Libro> listaLibros;
    private final String archivo = "datos.ser"; // Archivo para persistencia

    public GestorBiblioteca() {
        // Cargar libros previamente guardados
        cargarLibros();
        crearVentana();
    }

    private void crearVentana() {
        JFrame ventana = new JFrame("Gestor de Biblioteca");
        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Campos de entrada
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();
        JLabel lblCantidad = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField();

        // Botones
        JButton btnAgregar = new JButton("Agregar Libro");
        JButton btnMostrar = new JButton("Mostrar Libros");

        // Agregar componentes al panel
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(btnAgregar);
        panel.add(btnMostrar);

        ventana.add(panel);
        ventana.setVisible(true);

        // Acción botón Agregar
        btnAgregar.addActionListener(e -> {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();

            if (titulo.isEmpty() || autor.isEmpty() || txtCantidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(txtCantidad.getText().trim()); // Validación numérica
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Ingrese un número válido para la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            listaLibros.add(new Libro(titulo, autor, cantidad));
            guardarLibros(); // Guardar en archivo
            JOptionPane.showMessageDialog(ventana, "Libro agregado correctamente");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtCantidad.setText("");
        });

        // Acción botón Mostrar
        btnMostrar.addActionListener(e -> {
            if (listaLibros.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "No hay libros registrados");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Libro libro : listaLibros) {
                    sb.append(libro).append("\n");
                }
                JOptionPane.showMessageDialog(ventana, sb.toString(), "Libros Registrados", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Guardar lista de libros en archivo datos.ser
    private void guardarLibros() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(listaLibros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar lista de libros desde archivo datos.ser
    private void cargarLibros() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            listaLibros = (ArrayList<Libro>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            listaLibros = new ArrayList<>(); // Si no existe archivo, crear lista vacía
        }
    }

    public static void main(String[] args) {
        new GestorBiblioteca();
    }
}
