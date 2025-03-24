
import java.util.ArrayList;
import java.util.Random;


public class Uno {
    private final int TABLERO = -1;
    private final int BONEYARD = 0;
    private int players;
    private BoneyardUno boneyard = new BoneyardUno();
    private ArrayList<CartaUno> mazoCartas = new ArrayList<>();
    private ArrayList<PlayerUno> jugadores = new ArrayList<>();
    

    public Uno(int players){
        MazoUno mazoUno = new MazoUno();
        mazoCartas = new ArrayList<CartaUno>(mazoUno.getCartas());
        for (CartaUno carta:mazoCartas) {
            carta.setIdentificador(BONEYARD);
        }
        this.players = players;
    }

    
    public ArrayList<CartaUno> getMazoCartas() {
        return mazoCartas;
    }

    //Inicializa el ArrayList de jugadores
    public void InicializarJugadores(){
        for(int i = 1; i <= players; i++){
            jugadores.add(new PlayerUno(i));
        }
    }
    
    //Reparte las cartas
    public void repartirCartas(){
        Random rand = new Random();
        for(PlayerUno jugador:jugadores){
            int numRandom;
            for(int i = 0; i < 7; i++){
                numRandom = rand.nextInt(mazoCartas.size());
                while (mazoCartas.get(numRandom).getIdentificador() != BONEYARD){
                    numRandom = rand.nextInt(mazoCartas.size());
                }
                mazoCartas.get(numRandom).setIdentificador(jugador.getNumJugador());
            }
            jugador.setCartas(mazoCartas);
        }
        boneyard.setCartas(mazoCartas);
    }



    public void mostrarCartasJugadores(){
        System.out.println("\tCARTAS JUGADORES\n");
        for(PlayerUno jugador:jugadores){
        System.out.println("Jugador: "+jugador.getNumJugador()+"\n"+jugador.getCartas());
        }
    }

    public void mostrarCartasBoneyard(){
        System.out.println("\n\tCARTAS BONEYARD\n");
        System.out.println(boneyard.getCartas());
    }


    public static void main(String[] args) {
        Uno uno = new Uno(3);
        uno.InicializarJugadores();
        System.out.println(uno.getMazoCartas());
        uno.repartirCartas();
        uno.mostrarCartasJugadores();
        uno.mostrarCartasBoneyard();

        //Seleccionar al jugador a empezar o empezar por el jugador con la etiqueta de 1
        //Pondra la carta y cambiara el turno con todo y el efecto
        //Aplicar opcion de comer hasta que salga carta a jugar
        //Crear cartas comodines

    }
}
