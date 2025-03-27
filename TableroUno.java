import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TableroUno {
    private final int TABLERO = -1;
    BoneyardUno boneyard;
    ArrayList<PlayerUno> jugadores = new ArrayList<>(); 
    ArrayList<CartaUno> cartas = new ArrayList<>();
    boolean firstTime = true;
    int turnoActual;
    CartaUno ultimaCartaEnJuego;


    //Recibe el ArrayList de cartas que posee el jugador
    public void setCartas(ArrayList<CartaUno> cartas){
        if (this.cartas != null){
            this.cartas.clear();
        }
        this.cartas = new ArrayList<>(cartas.stream()
            .filter(c -> c.getIdentificador() == TABLERO)
            .collect(Collectors.toCollection(ArrayList::new)));
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    //Guarda las jugadas disponibles ya sea si toca el primer turno o si toca el siguiente turno
    public ArrayList<String> jugadasDisponibles(){
        ArrayList<String> jugadas = new ArrayList<>(); 
        ArrayList<CartaUno> mazoTemporal;
        if(firstTime){
            firstTime = false;
            mazoTemporal = jugadores.get(turnoActual-1).getCartas();
            for (int i = 0; i < mazoTemporal.size(); i++) {
                jugadas.add(mazoTemporal.get(i).toString());
            }
            return jugadas;
        }
        else{
            mazoTemporal = jugadores.get(turnoActual-1).getCartas();
            for (int i = 0; i < mazoTemporal.size(); i++) {
                if(mazoTemporal.get(i).getColor().equalsIgnoreCase(ultimaCartaEnJuego.getColor()) 
                || mazoTemporal.get(i).getValor() == ultimaCartaEnJuego.getValor() 
                || mazoTemporal.get(i).esComodin()){
                jugadas.add(mazoTemporal.get(i).toString());
                }
            }
            return jugadas;
        }
    }

    //Le da la opcion al jugador de seleccionar sus jugadas y regresa el metodo toString de la carta.
    public String seleccionCartaUno() {
        int opc;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> jugadas = new ArrayList<>(jugadasDisponibles()); // Se llama una sola vez
        if (!jugadas.isEmpty()) {
            do {
                System.out.println("\tJUGADAS DISPONIBLES\n");
                for (int i = 0; i < jugadas.size(); i++) {
                    System.out.println((i + 1) + ". " + jugadas.get(i) + "\n");
                }
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Ingrese un número válido.");
                    scanner.next();
                }
                opc = scanner.nextInt();
            } while (opc < 1 || opc > jugadas.size());
    
            System.out.println("Has Seleccionado: " + jugadas.get(opc - 1));
            return jugadas.get(opc - 1);
        } else {
            return "Sin jugadas disponibles";
        }
    }
    

    public CartaUno obtenerCartaDeJugada(String jugada) {
        return jugadores.get(turnoActual-1).getCartas().stream()
            .filter(c -> jugada.equals(c.toString())) 
            .findFirst()
            .orElse(null);
    }

    public CartaUno colocarJugada(){
        String jugada;
        jugada = seleccionCartaUno();
        if (jugada.equalsIgnoreCase("Sin jugadas disponibles")) {
            return null;
        }
        else{
            ultimaCartaEnJuego = new CartaUno(obtenerCartaDeJugada(jugada));
            return ultimaCartaEnJuego;
        }
    }

    public ArrayList<CartaUno> getCartas() {
        return cartas;
    }

    public void setJugadores(ArrayList<PlayerUno> jugadores) {
        this.jugadores = jugadores;
    }

    public void setBoneyard(BoneyardUno boneyard) {
        this.boneyard = boneyard;
    }
    
    public ArrayList<PlayerUno> getJugadores() {
        return jugadores;
    }

}
