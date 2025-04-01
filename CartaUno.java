import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CartaUno {
    private int valor;
    private String color;
    private int identificador;
    private boolean isVisible;  
    private int x;
    private int y;
    private JButton boton;
    
    //  Colores Disponibles: Amarillo, Rojo, Verde, Azul, Negro.
    public CartaUno(int valor, String color){
        this.valor = valor;
        this.color = color;
        this.x = 0;
        this.y = 0;
        this.boton = new JButton();
        // Cargar la imagen y agregarla al botón
        // Cargar la imagen
        ImageIcon imagen = obtenerImagenCarta(valor, color);

        // Redimensionar la imagen
        Image img = imagen.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH); // Ajusta el tamaño
        ImageIcon imagenRedimensionada = new ImageIcon(img);

        // Asignar la imagen redimensionada al botón
        boton.setIcon(imagenRedimensionada);

        // Ajustar el tamaño del botón
    boton.setPreferredSize(new java.awt.Dimension(100, 150));
    }

    private ImageIcon obtenerImagenCarta(int valor, String color) {
        String rutaImagen = "PNGcartasUno/" + valor + color + ".png"; 
        java.net.URL imgURL = getClass().getResource(rutaImagen);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se encontró la imagen: " + rutaImagen);
            return new ImageIcon(); // Retorna un icono vacío en caso de error
        }
    }
    

    public CartaUno(CartaUno carta){
        if(carta!=null){
        this.valor = carta.getValor();
        this.color = carta.getColor();
        this.x = carta.getX();
        this.y = carta.getY();
        this.identificador = carta.getIdentificador();
        this.isVisible = carta.getIsVisible();

        this.boton = new JButton();
        ImageIcon imagen = obtenerImagenCarta(this.valor, this.color);
        boton.setIcon(imagen);
        boton.setPreferredSize(new java.awt.Dimension(80, 120));

        }
    }

    public void mostrarCarta(){
        if(!isVisible){
            isVisible = true;
        }
    }

    public void ocultarCarta(){
        if(isVisible){
            isVisible = false;
        }
    }
    

    public CartaUno(){
        valor = 5;
        color = "amarillo";
    }

    @Override
    public String toString(){
        return valor+" - "+color;
    }

    public JButton getBoton() {
        return boton;
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


