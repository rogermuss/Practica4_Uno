import javax.swing.JButton;
import javax.swing.JFrame;

public class JuegoUno{
    public static void main(String[] args){
        // Crear la ventana
        JFrame ventana = new JFrame("Uno");
        ventana.setSize(1920, 1080); // Tamaño de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null); // Posicionamiento manual

        // Agregar el botón a la ventana
        CartaUno carta = new CartaUno(12, "Azul"); // Carta UNO con valor 1 y color azul
        JButton botonCarta = carta.getBoton();
        botonCarta.setBounds(150, 80, 100, 150); // Posic
        ventana.add(carta.getBoton());

        // Hacer visible la ventana
        ventana.setVisible(true);
        // Agregar un ActionListener para ocultar el botón al hacer clic
        botonCarta.addActionListener(e -> botonCarta.setVisible(false));
    }
}