import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Libro implements Serializable {
    private String titulo;
    private String autor;
    
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor;
    }
}

public class GestorBiblioteca {
    private ArrayList<Libro> listaLibros;
    private final String archivo = "libros.ser";

    public GestorBiblioteca() {
        // Cargar libros guardados
        cargarLibros();
        crearVentana();
    }

    private void crearVentana() {
        JFrame ventana = new JFrame("Gestor de Biblioteca");
        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); // Centra la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        // Etiquetas y campos
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();

        // Botones
        JButton btnAgregar = new JButton("Agregar Libro");
        JButton btnMostrar = new JButton("Mostrar Libros");

        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(btnAgregar);
        panel.add(btnMostrar);

        ventana.add(panel);
        ventana.setVisible(true);

        // Acción del botón Agregar
        btnAgregar.addActionListener(e -> {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();

            if(titulo.isEmpty() || autor.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            listaLibros.add(new Libro(titulo, autor));
            guardarLibros();
            JOptionPane.showMessageDialog(ventana, "Libro agregado correctamente");
            txtTitulo.setText("");
            txtAutor.setText("");
        });

        // Acción del botón Mostrar
        btnMostrar.addActionListener(e -> {
            if(listaLibros.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "No hay libros registrados");
            } else {
                StringBuilder sb = new StringBuilder();
                for(Libro libro : listaLibros){
                    sb.append(libro).append("\n");
                }
                JOptionPane.showMessageDialog(ventana, sb.toString(), "Libros Registrados", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void guardarLibros() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(listaLibros);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarLibros() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            listaLibros = (ArrayList<Libro>) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            listaLibros = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        new GestorBiblioteca();
    }
}
