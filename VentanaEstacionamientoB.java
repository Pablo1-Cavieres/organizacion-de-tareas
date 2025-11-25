import javax.swing.*;
import java.awt.*;

public class VentanaEstacionamientoB extends JFrame {

    private JTextField txtPatente, txtMarca, txtModelo;
    private JComboBox<String> cbTipo;
    private JRadioButton rbSi, rbNo;
    private ButtonGroup grupoRadio;
    private JTextArea txtListado;

    public VentanaEstacionamientoB() {
        super("Gestión de Estacionamiento");
        setLayout(new BorderLayout());

        // ---------------- Panel Formulario ----------------
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 8, 8));

        panelFormulario.add(new JLabel("Patente:"));
        txtPatente = new JTextField();
        panelFormulario.add(txtPatente);

        panelFormulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panelFormulario.add(txtMarca);

        panelFormulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelFormulario.add(txtModelo);

        panelFormulario.add(new JLabel("Tipo Vehículo:"));
        cbTipo = new JComboBox<>(new String[]{"Auto", "Moto", "Camioneta"});
        panelFormulario.add(cbTipo);

        panelFormulario.add(new JLabel("Estacionamiento Preferencial:"));
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbSi = new JRadioButton("Sí");
        rbNo = new JRadioButton("No");
        grupoRadio = new ButtonGroup();
        grupoRadio.add(rbSi);
        grupoRadio.add(rbNo);
        panelRadio.add(rbSi);
        panelRadio.add(rbNo);
        panelFormulario.add(panelRadio);

        add(panelFormulario, BorderLayout.NORTH);

        // ---------------- Área de Listado ----------------
        txtListado = new JTextArea(10, 40);
        txtListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListado);
        add(scroll, BorderLayout.SOUTH);

        // ---------------- Configuración de la ventana ----------------
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEstacionamientoB());
    }
}

