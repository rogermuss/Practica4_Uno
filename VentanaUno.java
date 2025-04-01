import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class VentanaUno {

    private JFrame ventana;
    private ArrayList<JButton> botonesCartas; 
    private JLabel cartaEnMesa;

    public VentanaUno(){
        // Crear la ventana
        JFrame ventana = new JFrame("Uno");
        ventana.setSize(1920, 1080); // Tamaño de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null); // Posicionamiento manual

        botonesCartas = new ArrayList<>();

        // Carta en la mesa (última carta jugada)
        cartaEnMesa = new JLabel();
        cartaEnMesa.setBounds(860, 400, 100, 150); // Centro de la pantalla
        ventana.add(cartaEnMesa);
    }

    public void agregarBoton(JButton boton){
        ventana.add(boton);
    }

    public void mostrarVentana(){
        ventana.setVisible(true);
    }

    public void ocultarVentana(){
        ventana.setVisible(false);
    }

}
