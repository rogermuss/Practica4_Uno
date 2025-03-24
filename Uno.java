
import java.sql.Array;
import java.util.ArrayList;


public class Uno {
    private final int TABLERO = -1;
    private final int BONEYARD = 0;
    ArrayList<CartaUno> mazoCartas = new ArrayList<>();
    public Uno(){
        MazoUno mazoUno = new MazoUno();
        mazoCartas = new ArrayList<CartaUno>(mazoUno.getCartas());
    }
    public ArrayList<CartaUno> getMazoCartas() {
        return mazoCartas;
    }
    public static void main(String[] args) {
        Uno uno = new Uno();
        System.out.println(uno.getMazoCartas());
    }
}
