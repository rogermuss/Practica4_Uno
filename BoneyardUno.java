import java.util.ArrayList;
import java.util.Random;
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

    public CartaUno comerCarta() {
        if (cartas.isEmpty()) {
            System.out.println("No hay fichas en el boneyard");
            return null; 
        }
        Random random = new Random();
        int numRandom = random.nextInt(cartas.size());
        CartaUno cartaAComer = cartas.get(numRandom); 
        return cartaAComer; 
    }

    public ArrayList<CartaUno> getCartas() {
        return cartas;
    }
    

}
