
import java.util.ArrayList;

public final class MazoUno {
    private String colorActual;
    private boolean recorrerPrimeraVez;
    private ArrayList<CartaUno> cartas;
    public static final int COMER2 = 10;
    public static final int BLOQUEAR_TURNO = 11;
    public static final int INVERTIR_DIRECCION = 12;
    public static final int CAMBIAR_COLOR = 13;
    public static final int COMER4_CAMBIAR_COLOR = 14;
    

    public MazoUno() {
        cartas = new ArrayList<>();
        recorrerPrimeraVez = false;
        agregarCartasNumericas();
        agregarComodines();

    }

    public void AsignarColor(int opc){
        if (recorrerPrimeraVez){
        if (opc >=0 && opc < 2){
            colorActual = "Amarillo";
        }
        else if (opc >=2 && opc < 4){
            colorActual = "Azul";
        }
        else if (opc >=4 && opc < 6){
            colorActual = "Verde";
        }
        else{
            colorActual = "Rojo";
        }
        }
        else{
            if (opc == 0){
                colorActual = "Amarillo";
            }
            else if (opc == 1){
                colorActual = "Azul";
            }
            else if (opc == 2){
                colorActual = "Verde";
            }
            else{
                colorActual = "Rojo";
                recorrerPrimeraVez = true;
            }
        }
    }


    public void agregarCartasNumericas(){
        int excepcion = 4;
        for (int i = 0; i <= 12; i++) {
            for (int j = 0; j < excepcion; j++){
                AsignarColor(j);
                cartas.add(new CartaUno(i, colorActual));
            }
            excepcion = 8;
        }
    }


    public void agregarComodines(){
        for(int i = 13; i <= 14; i++){
            for(int j = 0; j < 4; j++){
                cartas.add(new CartaUno(i, "negro"));
            }
        }
    }

    public ArrayList<CartaUno> getCartas() {
        return cartas;
    }
    public void setCartas(ArrayList<CartaUno> cartas) {
        this.cartas = cartas;
    }
    
    public static void main(){
        MazoUno mazoUno = new MazoUno();
        System.out.println(mazoUno.getCartas());
    }
    
}
