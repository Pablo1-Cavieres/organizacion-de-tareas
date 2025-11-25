import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// --- Backend ---
class Vehiculo {
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

class GestorEstacionamiento {
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    public void registrarEntrada(Vehiculo v) { listaVehiculos.add(v); }
    public ArrayList<Vehiculo> getLista() { return listaVehiculos; }
}

// --- Frontend ---
public class VentanaEstacionamientoC extends JFrame {
    private GestorEstacionamiento gestor;
    private JTextField txtPatente, txtMarca, txtModelo;
    private JComboBox<String> cbTipo;
    private JTextArea txtListado;

    public VentanaEstacionamientoC() {
        super("Integración Backend-Frontend");

        gestor = new GestorEstacionamiento(); // Conexión con el backend

        setLayout(new BorderLayout());

        // Panel formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        txtPatente = new JTextField(); txtMarca = new JTextField(); txtModelo = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"Auto","Moto","Camioneta"});
        panelForm.add(new JLabel("Patente:")); panelForm.add(txtPatente);
        panelForm.add(new JLabel("Marca:")); panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo:")); panelForm.add(txtModelo);
        panelForm.add(new JLabel("Tipo:")); panelForm.add(cbTipo);
        add(panelForm, BorderLayout.NORTH);

        // Panel botones
        JPanel panelBot = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnListar = new JButton("Listar/Refrescar");
        panelBot.add(btnGuardar); panelBot.add(btnListar);
        add(panelBot, BorderLayout.CENTER);

        // Área de listado
        txtListado = new JTextArea(10, 30);
        add(new JScrollPane(txtListado), BorderLayout.SOUTH);

        // Evento Guardar
        btnGuardar.addActionListener(e -> {
            String patente = txtPatente.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String tipo = cbTipo.getSelectedItem().toString();

            Vehiculo v = new Vehiculo(patente, marca, modelo, tipo);
            gestor.registrarEntrada(v); // Enviar al backend
        });

        // Evento Listar/Refrescar
        btnListar.addActionListener(e -> {
            ArrayList<Vehiculo> lista = gestor.getLista(); // Obtener lista del backend
            txtListado.setText("");
            for(Vehiculo v : lista) txtListado.append(v.toString() + "\n");
        });

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEstacionamientoC());
    }
}
