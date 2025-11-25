import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

// --- Clase Vehiculo ---
class Vehiculo implements Serializable {
    private String patente, marca, modelo, tipo;

    public Vehiculo(String patente, String marca, String modelo, String tipo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return patente + " - " + marca + " - " + modelo + " - " + tipo;
    }
}

// --- Clase GestorEstacionamiento ---
class GestorEstacionamiento {
    private ArrayList<Vehiculo> listaVehiculos;
    private final String ARCHIVO = "autos.dat";

    public GestorEstacionamiento() {
        listaVehiculos = new ArrayList<>();
        cargarDatos(); // cargar automáticamente al iniciar
    }

    public void registrarEntrada(Vehiculo v) {
        listaVehiculos.add(v);
        guardarDatos(); // guardar automáticamente
    }

    public ArrayList<Vehiculo> getLista() {
        return listaVehiculos;
    }

    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(listaVehiculos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            listaVehiculos = (ArrayList<Vehiculo>) ois.readObject();
        } catch (FileNotFoundException e) {
            listaVehiculos = new ArrayList<>(); // lista vacía si no existe el archivo
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
        }
    }
}

// --- Ventana Principal ---
public class VentanaestacionamientoD extends JFrame {

    private GestorEstacionamiento gestor;
    private JTextField txtPatente, txtMarca, txtModelo;
    private JComboBox<String> cbTipo;
    private JTextArea txtListado;

    public VentanaestacionamientoD() {
        super("Gestión de Estacionamiento ");

        gestor = new GestorEstacionamiento(); // backend
        setLayout(new BorderLayout());

        // --- Panel Formulario ---
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 8, 8));
        panelFormulario.add(new JLabel("Patente:"));
        txtPatente = new JTextField(); panelFormulario.add(txtPatente);

        panelFormulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField(); panelFormulario.add(txtMarca);

        panelFormulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField(); panelFormulario.add(txtModelo);

        panelFormulario.add(new JLabel("Tipo Vehículo:"));
        cbTipo = new JComboBox<>(new String[]{"Auto", "Moto", "Camioneta"});
        panelFormulario.add(cbTipo);

        add(panelFormulario, BorderLayout.NORTH);

        // --- Panel Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnListar = new JButton("Listar / Refrescar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnListar);
        add(panelBotones, BorderLayout.CENTER);

        // --- Área de Listado ---
        txtListado = new JTextArea(10, 40);
        txtListado.setEditable(false);
        add(new JScrollPane(txtListado), BorderLayout.SOUTH);

        // --- Evento Guardar con validación robusta ---
        btnGuardar.addActionListener(e -> {
            String patente = txtPatente.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String tipo = cbTipo.getSelectedItem().toString();

            if (patente.isEmpty() || marca.isEmpty() || modelo.isEmpty()) {
                // Mostramos alerta y salimos
                JOptionPane.showMessageDialog(this,
                        "¡Todos los campos son obligatorios!",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return; // no se guarda el vehículo
            }

            // Crear vehículo y enviarlo al gestor
            Vehiculo v = new Vehiculo(patente, marca, modelo, tipo);
            gestor.registrarEntrada(v);

            JOptionPane.showMessageDialog(this, "Vehículo registrado con éxito");

            // Limpiar campos
            txtPatente.setText("");
            txtMarca.setText("");
            txtModelo.setText("");
            cbTipo.setSelectedIndex(0);

            // Refrescar listado automáticamente
            btnListar.doClick();
        });

        // --- Evento Listar / Refrescar ---
        btnListar.addActionListener(e -> {
            txtListado.setText("");
            for (Vehiculo v : gestor.getLista()) {
                txtListado.append(v.toString() + "\n");
            }
        });

        // --- Cargar listado inicial ---
        btnListar.doClick();

        // --- Configuración ventana ---
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaestacionamientoD());
    }
}


