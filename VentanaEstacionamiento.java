import javax.swing.*;
import java.awt.*;

public class VentanaEstacionamiento extends JFrame {

    public VentanaEstacionamiento() {
        super("Sistema de Estacionamiento");

        // --- Layout principal ---
        setLayout(new BorderLayout());

        // ---------------- Panel Superior (Título) ----------------
        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(new JLabel("Sistema de Gestión de Estacionamiento"));
        add(panelSuperior, BorderLayout.NORTH);

        // ---------------- Panel Central (Formulario) ----------------
        JPanel panelCentral = new JPanel(new GridLayout(4, 2, 10, 10));

        panelCentral.add(new JLabel("Patente:"));
        panelCentral.add(new JTextField(10));

        panelCentral.add(new JLabel("Marca:"));
        panelCentral.add(new JTextField(10));

        panelCentral.add(new JLabel("Modelo:"));
        panelCentral.add(new JTextField(10));

        panelCentral.add(new JLabel("Tipo Vehículo:"));
        panelCentral.add(new JComboBox<>(new String[]{"Auto", "Moto", "Camioneta"}));

        add(panelCentral, BorderLayout.CENTER);

        // ---------------- Panel Inferior (Botones) ----------------
        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.add(new JButton("Guardar"));
        panelInferior.add(new JButton("Cancelar"));
        add(panelInferior, BorderLayout.SOUTH);

        // ---------------- Configuración de la ventana ----------------
        setSize(450, 300);
        setLocationRelativeTo(null); // centrar en pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEstacionamiento());
    }
}





