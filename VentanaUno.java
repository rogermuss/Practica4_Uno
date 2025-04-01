import java.awt.BorderLayout;
import java.awt.Color;
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


    public VentanaUno(){
        // Crear la ventana
        ventana = new JFrame("Uno");
        ventana.setSize(1920, 1080); // Tamaño de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null); // Posicionamiento manual

        botonesCartasTurnoActual = new ArrayList<>();

        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.setBounds(100, 50, 200, 50);

        botonContinuar.addActionListener(e -> semaforo.release()); // Libera el juego al hacer clic

        panelCentro = new JPanel(new GridBagLayout());
        panelCartas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCartas.setBackground(Color.LIGHT_GRAY);

        // Solo se añaden los paneles, pero aún no hay contenido en ellos
        ventana.add(panelCentro, BorderLayout.CENTER);
        ventana.add(panelCartas, BorderLayout.SOUTH);

        ventana.add(botonContinuar);
        ventana.setVisible(true);


    }

    public static void esperarClick() {
        try {
            semaforo.acquire(); // Bloquea la ejecución hasta que se presione el botón
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCartaEnMesa(JButton nuevaCarta) {
        panelCentro.removeAll(); // Elimina la carta anterior
        this.cartaEnMesa = nuevaCarta;
        panelCentro.add(cartaEnMesa);
        actualizarInterfaz();
    }


    public void setBotonesCartasTurnoActual(ArrayList<JButton> nuevasCartas) {
        panelCartas.removeAll(); // Limpia las cartas anteriores
        botonesCartasTurnoActual = nuevasCartas;
        for (JButton carta : nuevasCartas) {
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
