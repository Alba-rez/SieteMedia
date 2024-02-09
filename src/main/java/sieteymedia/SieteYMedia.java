package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

/**
 * Clase SieteYMedia que representa el juego de cartas "Siete y Media".
 */
public class SieteYMedia {
    private  Baraja baraja;  // Objeto Baraja que representa la baraja de cartas
    private  Carta[] cartasJugador;  // Array de Carta que representa las cartas del jugador
    private  Carta[] cartasBanca;    // Array de Carta que representa las cartas de la banca
    private  InterfaceConsola interfaceConsola;  // Objeto InterfaceConsola para interactuar con el usuario


    /**
     * Constructor de la clase SieteYMedia.
     * Inicializa la baraja, las cartas del jugador y de la banca, y la interfaz de consola.
     */
    public SieteYMedia(InterfaceConsola interfaceConsola) {
        this.interfaceConsola=interfaceConsola;
        baraja = new Baraja();
        baraja.barajar();
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }

    // Métodos getter para obtener la baraja, las cartas del jugador y las cartas de la banca

    Baraja getBaraja(){
        return baraja;
    }
    Carta[] getCartasJugador(){

        return cartasJugador;
    }
    Carta[]getCartasBanca(){

        return cartasBanca;
    }

    /**
     * Método para iniciar el juego.
     * Llama a los métodos turnoJugador y turnoBanca, y luego determina el ganador.
     */
    public void jugar() {
        turnoJugador();
        turnoBanca();
        interfaceConsola.determinarGanador();


    }
    /**
     * Método para el turno del jugador.
     * Mientras el valor de las cartas del jugador sea menor a 7.5, sigue pidiendo cartas.
     */
    public void turnoJugador() {

        while(valorCartas(cartasJugador)<7.5 ){
            Carta c=baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasJugador,c);
            interfaceConsola.mostrarCartas(getCartasJugador());
            double valor=valorCartas(cartasJugador);
            char opc= interfaceConsola.pedirOpcionJugador();
            if(opc=='P'){
                break;
            }

        }

        turnoBanca();
    }

    /**
     * Método para el turno de la banca.
     * Si el valor de las cartas del jugador es mayor a 7.5, termina el turno.
     * De lo contrario, sigue pidiendo cartas hasta que el valor de las cartas de la banca sea mayor o igual al del jugador.
     */
    public void turnoBanca() {

        if (valorCartas(cartasJugador) > 7.5) {
           interfaceConsola.mostrarCartas(getCartasJugador());
            return;
        }
        while (valorCartas(cartasBanca) < valorCartas(cartasJugador) && valorCartas(cartasBanca) < 7.5) {

            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }
        interfaceConsola.mostrarCartas(getCartasBanca());

    }

    /**
     * Método para insertar una carta en el array de cartas.
     * La carta se inserta en la primera posición libre del array.
     */
    private void insertarCartaEnArray(Carta[] cartas, Carta carta) {
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] == null) { // mete la carta en la primera pos libre
                cartas[i] = carta;
                break;
            }
        }
    }
    /**
     * Método para calcular el valor total de las cartas en un array.
     */
    private double valorCartas(Carta[] cartas) {
        double valor = 0;
        for (Carta carta : cartas) {
            if(carta!=null) {
                valor += carta.getValor();
            }
        }
        return valor;
    }

    /**
     * Método para obtener el valor de las cartas.
     */

    public double obtenerValorCartas(Carta[] cartas) {
        return valorCartas(cartas);
    }

}

