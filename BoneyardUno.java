import java.util.ArrayList;
import java.util.stream.Collectors;

public class BoneyardUno {
    private final int BONEYARD = 0;
    ArrayList<CartaUno> cartas = new ArrayList<>();


    //Recibe el ArrayList de cartas que posee el jugador
    public void setCartas(ArrayList<CartaUno> cartas){
        if (this.cartas != null){
            this.cartas.clear();
        }
        this.cartas = new ArrayList<>(cartas.stream()
            .filter(c -> c.getIdentificador() == BONEYARD)
            .collect(Collectors.toCollection(ArrayList::new)));
    }

    public ArrayList<CartaUno> getCartas() {
        return cartas;
    }

    

}
