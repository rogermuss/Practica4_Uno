

public class CartaUno {
    private int valor;
    private String color;
    private int identificador;
    private boolean isVisible;  
    private int x;
    private int y;
    
    //  Colores Disponibles: Amarillo, Rojo, Verde, Azul, Negro.
    public CartaUno(int valor, String color){
        this.valor = valor;
        this.color = color;
        this.x = 0;
        this.y = 0;
    }

    public CartaUno(CartaUno carta){
        this.valor = carta.getValor();
        this.color = carta.getColor();
        this.x = carta.getX();
        this.y = carta.getY();
        this.identificador = carta.getIdentificador();
        this.isVisible = carta.getIsVisible();
    }

    public CartaUno(){
        valor = 5;
        color = "amarillo";
    }

    @Override
    public String toString(){
        return valor+" - "+color;
    }


    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    public String getColor() {
        return color;
    }
    public int getValor() {
        return valor;
    }
    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public int getIdentificador() {
        return identificador;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean esComodin(){
        return color.equalsIgnoreCase("negro");
    }


    



}


