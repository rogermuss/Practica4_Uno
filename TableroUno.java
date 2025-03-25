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

    public ArrayList<String> jugadasDisponibles(){
        ArrayList<String> jugadas = new ArrayList<>(); 
        ArrayList<CartaUno> mazoTemporal;
        if(firstTime){
            mazoTemporal = jugadores.get(turnoActual).getCartas();
            for (int i = 0; i < mazoTemporal.size(); i++) {
                jugadas.add(mazoTemporal.get(i).toString());
            }
            return jugadas;
        }
        else{
            
        }
    }

    public String seleccionCartaUno(){
        int opc;
        ArrayList<String> jugadas;
        Scanner scanner = new Scanner(System.in);
        if(!jugadasDisponibles().isEmpty()){
            jugadas = jugadasDisponibles();
            do{
                System.out.println("\tJUGADAS DISPONIBLES\n");
                for(int i = 0; i<jugadas.size(); i++){
                System.out.println(i+". "+jugadas.get(i)+"\n");
                }
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Ingrese un número válido.");
                    scanner.next(); // Descarta la entrada inválida
                }
                opc = scanner.nextInt();
            } while(opc >= 1 && opc <= jugadas.size());
            System.out.println("Has Seleccionado: "+jugadas.get(opc - 1));
            return jugadas.get(opc - 1);
        }
        else{
            return "Sin jugadas disponibles";
        }
    }

    public CartaUno obtenerCartaDeJugada(String jugada) {
        return jugadores.get(turnoActual).getCartas().stream()
            .filter(c -> jugada.equals(c.toString())) 
            .findFirst()
            .orElse(null);
    }
    


    public CartaUno colocarJugada(){
        String jugada;
        jugada = seleccionCartaUno();
        if(jugada.compareTo("Sin jugadas disponibles") == 0){
            return null;
        }
        else{
            ultimaCartaEnJuego = obtenerCartaDeJugada(jugada);

            return ultimaCartaEnJuego;

            //Espacio para colocar parte grafica

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
