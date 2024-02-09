package sieteymedia;
import recursos.Carta;

import java.util.Scanner;

/**
 * Clase InterfaceConsola que proporciona una interfaz de consola para el juego SieteYMedia.
 */
public class InterfaceConsola {

    Scanner sc = new Scanner(System.in);

    // Objeto SieteYMedia que representa el estado actual del juego
    SieteYMedia sieteymedia;


    /**
     * Método principal que se ejecuta al iniciar el programa.
     */
public static void main(String[]args) {
    // Creación de un objeto InterfaceConsola para interactuar con el usuario
    InterfaceConsola interfaz=new InterfaceConsola();

    // Creación de un objeto SieteYMedia que representa el juego
    SieteYMedia sieteMed=new SieteYMedia(interfaz);

    // Asignación del objeto SieteYMedia a la interfaz para que pueda interactuar con el juego
    interfaz.setSieteYMedia(sieteMed);

    //inicio del juego
    interfaz.presentarJuego();
    sieteMed.jugar();

}
    /**
     * Método para asignar el objeto SieteYMedia a la interfaz.
     */
    public void setSieteYMedia(SieteYMedia sieteymedia) {
        this.sieteymedia = sieteymedia;
    }

    /**
     * Método para presentar las reglas del juego al usuario.
     */
    public void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la  banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println(
                "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.print("- Si la suma de los valores de las cartas sacadas es superior ");
        System.out.println("a 7 y medio, el jugador 'se pasa de siete y medio' y  pierde.");
        System.out.println(
                "- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta  está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println(
                "- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.");
        System.out.println(
                "- La banca no se puede plantar y tiene que empatar o superar la puntuación del  jugador sin pasarse.");
        System.out.println(
                "- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.");
        System.out.println("\nEmpecemos!!!\n");
    }

    /**
     * Método para mostrar las cartas/puntos del jugador y de la banca.
     */
   void mostrarCartas(Carta[] cartas) {
       if(cartas==sieteymedia.getCartasJugador()) {
           System.out.println("Valor de las cartas del jugador: " + sieteymedia.obtenerValorCartas(cartas));
       }
       if(cartas==sieteymedia.getCartasBanca()){
           System.out.println("Valor de las cartas de la banca: " + sieteymedia.obtenerValorCartas(sieteymedia.getCartasBanca()));
       }
       if(sieteymedia.obtenerValorCartas(sieteymedia.getCartasJugador())>7.5) {
           cartasTodas(sieteymedia.getCartasJugador());
           determinarGanador();
       } else if(sieteymedia.obtenerValorCartas(sieteymedia.getCartasBanca())>7.5)  {
           cartasTodas(sieteymedia.getCartasBanca());
           determinarGanador();
       }





    }


    /**
     * Método para mostrar todas las cartas.
     */
void cartasTodas(Carta[]cartas){
    int i = 0;
    while (cartas[i] != null) {
        System.out.print("\t" + cartas[i]);
        i++;
    }
    System.out.println();
}

    /**
     * Método para pedir la opción al jugador.
     */
    public char pedirOpcionJugador() {
        char opc=' ';
        System.out.println("\n¿Pides [C]arta o te [P]lantas?");
        opc = sc.next().trim().toUpperCase().charAt(0);

        if(opc=='C'){
        sieteymedia.turnoJugador();
        }
        if(opc=='P'){
            System.out.println("Te has plantado");

        }

        return opc;
    }

    /**
     * Método para determinar el ganador del juego.
     */
    void determinarGanador() {
        double valorJugador = sieteymedia.obtenerValorCartas(sieteymedia.getCartasJugador());
        double valorBanca = sieteymedia.obtenerValorCartas(sieteymedia.getCartasBanca());

        if (valorJugador > 7.5) {
            mostrarMensaje("La banca gana, el jugador se ha pasado de 7.5");
            System.exit(0);
        } else if (valorBanca > 7.5) {
           mostrarMensaje("El jugador gana, la banca se ha pasado de 7.5");
            System.exit(0);
        } else if (valorJugador > valorBanca) {
           mostrarMensaje("El jugador gana con " + valorJugador + " puntos");
            System.exit(0);
        } else if (valorBanca > valorJugador) {
            mostrarMensaje("La banca gana con " + valorBanca + " puntos");
            System.exit(0);
        } else {
            mostrarMensaje("Empate, ambos tienen la misma puntuación");
            System.exit(0);
        }
    }

    void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }

}
