import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaUno {
    private JFrame ventana;
    private JPanel panelCentro;
    private JPanel panelCartas;
    private ArrayList<JButton> botonesCartasTurnoActual; 
    private JButton cartaEnMesa;
    private static Semaphore semaforo = new Semaphore(0); // Controla la pausa

    public VentanaUno() {
        // Crear la ventana
        ventana = new JFrame("Uno");
        ventana.setSize(1920, 1080);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout()); // Usa BorderLayout en la ventana

        botonesCartasTurnoActual = new ArrayList<>();

        // Botón para continuar
        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(e -> semaforo.release()); 

        // Panel central (donde se muestra la carta en mesa)
        panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setPreferredSize(new Dimension(250, 380));
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setOpaque(true);


        // Panel inferior (cartas del jugador)
        panelCartas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCartas.setPreferredSize(new Dimension(500, 200));
        panelCartas.setBackground(Color.LIGHT_GRAY);
        panelCartas.setOpaque(true);
        // Agregar los paneles a la ventana
        ventana.add(panelCentro, BorderLayout.CENTER);
        ventana.add(panelCartas, BorderLayout.SOUTH);
        ventana.add(botonContinuar, BorderLayout.NORTH);

        ventana.setVisible(true);
    }

    public static void esperarClick() {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCartaEnMesa(JButton cartaEnMesa) {
        panelCentro.removeAll();
        
        this.cartaEnMesa = cartaEnMesa;
        
        // Asegurar que el botón ocupa todo el espacio disponible
        this.cartaEnMesa.setPreferredSize(new Dimension(250, 380)); // Ajusta al tamaño de una carta
        
        panelCentro.setLayout(new GridBagLayout()); // Asegura que sigue centrado
        
        panelCentro.add(this.cartaEnMesa);
        
        actualizarInterfaz();
    }
    

    public void setBotonesCartasTurnoActual(ArrayList<JButton> cartasTurnoActual) {
        panelCartas.removeAll();
        botonesCartasTurnoActual = cartasTurnoActual;
        for (JButton carta : cartasTurnoActual) {
            panelCartas.add(carta);
        }
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        ventana.revalidate();
        ventana.repaint();
    }

    public void agregarBoton(JButton boton){
        ventana.add(boton);
    }

    public void ocultarVentana(){
        ventana.setVisible(false);
    }
}
