import java.util.ArrayList;
import java.util.stream.Collectors;

public class TableroUno {
    private final int TABLERO = -1;
    ArrayList<CartaUno> cartas = new ArrayList<>();
    //Recibe el ArrayList de cartas que posee el jugador
    public void setCartas(ArrayList<CartaUno> cartas){
        this.cartas = new ArrayList<>(cartas.stream()
            .filter(c -> c.getIdentificador() == TABLERO)
            .collect(Collectors.toCollection(ArrayList::new)));
    }
}
