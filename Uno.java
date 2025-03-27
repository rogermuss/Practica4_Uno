
import java.util.ArrayList;
import java.util.Random;


public class Uno {
    private final int TABLERO = -1;
    private final int BONEYARD = 0;
    private final boolean HORARIO = true;
    private final boolean ANTIHORARIO = false;
    private int players;
    private int turnoActual = 1;
    private boolean sentido = HORARIO;
    private BoneyardUno boneyard = new BoneyardUno();
    private TableroUno tablero = new TableroUno();
    private ArrayList<CartaUno> mazoCartas = new ArrayList<>();
    private ArrayList<PlayerUno> jugadores = new ArrayList<>();
    private CartaUno ultimaCartaEnJuego;
    

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

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setUltimaCartaEnJuego(CartaUno ultimaCartaEnJuego) {
        this.ultimaCartaEnJuego = ultimaCartaEnJuego;
    }

    public CartaUno getUltimaCartaEnJuego(){
        return ultimaCartaEnJuego;
    }



    //Inicializa el ArrayList de jugadores
    public void InicializarJugadores(){
        for(int i = 1; i <= players; i++){
            jugadores.add(new PlayerUno(i));
        }
    }

    public void mostrarCartasBoneyard(){
        System.out.println("\n\tCARTAS BONEYARD\n");
        System.out.println(boneyard.getCartas());
    }
    
    //Metodos para Jugar

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

    public void cambiarTurno(){
        if(sentido){
            if (turnoActual == players){
                turnoActual = 1;
            }
            else{
                turnoActual++;
            }
        }
        else{
            if(turnoActual == 1){
                turnoActual = players;
            }
            else{
                turnoActual--;
            }
        }
    }

    //Coloca la carta y obtiene la ultima carta en juego para verificar si sigue el proceso
    public boolean colocarCarta(){
        CartaUno cartaJugada = new CartaUno(tablero.colocarJugada());
        System.out.println(cartaJugada);
        ultimaCartaEnJuego = new CartaUno(cartaJugada);
        if(cartaJugada.getColor() != null){
            for(CartaUno carta:mazoCartas){
                if(carta.getColor().compareTo(cartaJugada.getColor()) == 0 
                && carta.getValor() == cartaJugada.getValor()){
                    carta.setIdentificador(TABLERO);
                    break;
                }
            }
            return true;
        }
        if(cartasEnBoneyard()){
            for(CartaUno carta:mazoCartas){
                CartaUno cartaComida = new CartaUno(boneyard.comerCarta());
                if(cartaComida.toString().equalsIgnoreCase(carta.toString())){
                    carta.setIdentificador(turnoActual);
                    break;
                }
             }
             return false;
        }
        return false;
    }

    public boolean cartasEnBoneyard(){
        int cont = 0;
        for(CartaUno carta:mazoCartas){
            if(carta.getIdentificador() == BONEYARD){
            cont++;
            }
        }
        return (cont != 0);
    }

    public void mostrarCartasJugadores(){
        System.out.println("\tCARTAS JUGADORES\n");
        jugadores.forEach(j-> System.out.println("Jugador: "+j.getNumJugador()+"\n"+j.getCartas()));
    
    }
    public void mostrarCartasTablero(){
        System.out.println("\tCARTAS TABLERO\n");
        System.out.println(tablero.getCartas());
    }

    public void actualizarTablero(){
        tablero.setJugadores(jugadores);
        tablero.setBoneyard(boneyard);
        tablero.setTurnoActual(turnoActual);
    }

    public void actualizarArreglosDeCartas(){
        boneyard.setCartas(mazoCartas);
        tablero.setCartas(mazoCartas);
        jugadores.forEach(j->j.setCartas(mazoCartas));
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }
    public void setSentido(boolean sentido) {
        this.sentido = sentido;
    }
    public boolean getSentido(){
        return sentido;
    }

    public void efectoCambioDeTurno(){
        if(sentido){
            sentido = ANTIHORARIO;
        }
        else {
            sentido = HORARIO;
        }
    }

    public void efectoComerDosSiguienteTurno(){

    }

    public void efectoBloquearTurno(){

    }

    public void efectoCambiarColor(){

    }

    public void efectoComerCuatroCambiarColor(){

    }

    public static void main(String[] args) {
        boolean win = false;
        //Genero la clase de juego.
        Uno uno = new Uno(3);
        //Inicializa en un arreglo la cantidad de jugadores indicada
        uno.InicializarJugadores();

        //Reparte 7 cartas a cada jugador
        uno.repartirCartas();
        //Muestra la informacion del juego
        do{
        uno.mostrarCartasJugadores();
        uno.mostrarCartasBoneyard();
        uno.mostrarCartasTablero();
        //Envia las clases PlayerUno y BoneyardUno para trabajar con ellas en el tablero
        uno.actualizarTablero();
        System.out.println("\tTurno Jugador: "+uno.getTurnoActual()+"\n");
        //Coloca una carta basandose en el turno actual
        boolean pudoJugar = uno.colocarCarta();
        if(!pudoJugar){
            if(uno.getSentido()){
                uno.setTurnoActual(uno.getTurnoActual()-1);
            } else{
                uno.setTurnoActual(uno.getTurnoActual()+1);
            }
        }
        uno.actualizarArreglosDeCartas();

        uno.cambiarTurno();
        System.out.println(uno.getUltimaCartaEnJuego());
        

        //Repetir proceso y activar efectos de cartas, a su vez crear el pozo
        //Crear condiciones para ganar el juego.
        } while (!win);


        //Para obtener la carta final --- igualar la ultima carta que se coloque en el metodo para colocar la carta
        //Seleccionar al jugador a empezar o empezar por el jugador con la etiqueta de 1
        //Pondra la carta y cambiara el turno con todo y el efecto
        //Aplicar opcion de comer hasta que salga carta a jugar
        //Crear cartas comodines

    }
}
