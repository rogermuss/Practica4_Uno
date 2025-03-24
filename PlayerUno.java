
import java.util.ArrayList;
import java.util.stream.Collectors;


public class PlayerUno {

    private int numJugador;
    private ArrayList<CartaUno> cartas = new ArrayList<>();
    
    public PlayerUno(int numJugador){
        this.numJugador = numJugador;
    }

    //Recibe el ArrayList de cartas que posee el jugador
    public void setCartas(ArrayList<CartaUno> cartas){
        this.cartas = new ArrayList<>(cartas.stream()
            .filter(c -> c.getIdentificador() == getNumJugador())
            .collect(Collectors.toCollection(ArrayList::new)));
    }

    public ArrayList<CartaUno> getCartas() {
        return cartas;
    }
    

    public int getNumJugador() {
        return numJugador;
    }
    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }
}
