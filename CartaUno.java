

public class CartaUno {
    private int valor;
    private String color;
    private int identificador;
    private boolean isVisible;  
    
    //  Colores Disponibles: Amarillo, Rojo, Verde, Azul, Negro.
    public CartaUno(int valor, String color){
        this.valor = valor;
        this.color = color;
    }

    public CartaUno(){
        valor = 5;
        color = "amarillo";
    }

    @Override
    public String toString(){
        return valor+" - "+color;
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

    



}


