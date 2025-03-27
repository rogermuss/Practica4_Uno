
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


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


    //Coloca la carta y obtiene la ultima carta en juego para verificar si sigue el proceso
    public boolean colocarCarta(){
        CartaUno cartaJugada = new CartaUno(tablero.colocarJugada());
        System.out.println(cartaJugada);
        ultimaCartaEnJuego = new CartaUno(cartaJugada);
        
        if(cartaJugada.getColor() != null){
            for(CartaUno carta:mazoCartas){
                if(carta.getColor().compareTo(cartaJugada.getColor()) == 0 
                && carta.getValor() == cartaJugada.getValor() && carta.getIdentificador() == turnoActual){
                    carta.setIdentificador(TABLERO);
                    actualizarArreglosDeCartas();
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

    public void efectoCambioDeDireccion(){
        if(sentido){
            sentido = ANTIHORARIO;
        }
        else {
            sentido = HORARIO;
        }
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

    public void efectoComerDosSiguienteTurno(){
        cambiarTurno();
        for(int i = 0; i<2; i++){
            if(cartasEnBoneyard()){
                for(CartaUno carta:mazoCartas){
                    CartaUno cartaComida = new CartaUno(boneyard.comerCarta());
                    if(cartaComida.toString().equalsIgnoreCase(carta.toString())){
                        carta.setIdentificador(turnoActual);
                        break;
                    }
                }
            }
        }
    }

    public void efectoBloquearTurno(){
        cambiarTurno();
    }

    public void efectoCambiarColor(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> coloresAElegir = new ArrayList<>();
        coloresAElegir.addAll(Arrays.asList("Amarillo", "Rojo", "Verde", "Azul"));
        int opcColor;
        int[] indexColor = {1};
        do {
            coloresAElegir.forEach(c -> {
            System.out.println(":" + indexColor[0] + ": " + c);
            indexColor[0]++;
            });
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
                scanner.next();
            }
            opcColor = scanner.nextInt();
        } while (opcColor < 1 || opcColor > coloresAElegir.size());
        ultimaCartaEnJuego.setColor(coloresAElegir.get(opcColor-1));
        tablero.setUltimaCartaEnJuego(ultimaCartaEnJuego);
        System.out.println(ultimaCartaEnJuego.getColor());
    }

    public boolean verificarVictoria(){
        for(CartaUno carta:mazoCartas){
            if(carta.getIdentificador() == turnoActual){
                return false;
            }
        }
        return true;
    }

    public void efectoComerCuatroCambiarColor(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> coloresAElegir = new ArrayList<>();
        coloresAElegir.addAll(Arrays.asList("Amarillo", "Rojo", "Verde", "Azul"));
        int opcColor;
        int[] indexColor = {1};
        do {
            coloresAElegir.forEach(c -> {
            System.out.println(":" + indexColor[0] + ": " + c);
            indexColor[0]++;
            });
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
                scanner.next();
            }
            opcColor = scanner.nextInt();
        } while (opcColor < 1 || opcColor > coloresAElegir.size());
        ultimaCartaEnJuego.setColor(coloresAElegir.get(opcColor-1));
        tablero.setUltimaCartaEnJuego(ultimaCartaEnJuego);
        //Logica comer 4 y cambiar color
        cambiarTurno();
        for(int i = 0; i<4; i++){
            if(cartasEnBoneyard()){
                for(CartaUno carta:mazoCartas){
                    CartaUno cartaComida = new CartaUno(boneyard.comerCarta());
                    if(cartaComida.toString().equalsIgnoreCase(carta.toString())){
                        carta.setIdentificador(turnoActual);
                        break;
                    }
                }
            }
        }
    }

    //Para terminar la parte de logica falta arreglar el removimiento de las fichas colocadas

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
        if(uno.getUltimaCartaEnJuego().getValor() == MazoUno.COMER2){
            uno.efectoComerDosSiguienteTurno();
        }
        else if(uno.getUltimaCartaEnJuego().getValor() == MazoUno.BLOQUEAR_TURNO){
            uno.efectoBloquearTurno();
        }
        else if(uno.getUltimaCartaEnJuego().getValor() == MazoUno.INVERTIR_DIRECCION){
            uno.efectoCambioDeDireccion();
        }
        else if(uno.getUltimaCartaEnJuego().getValor() == MazoUno.CAMBIAR_COLOR){
            uno.efectoCambiarColor();
        }
        else if(uno.getUltimaCartaEnJuego().getValor() == MazoUno.COMER4_CAMBIAR_COLOR){
            uno.efectoComerCuatroCambiarColor();
        }
        uno.actualizarArreglosDeCartas();
        win = uno.verificarVictoria();
        uno.cambiarTurno();        

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

