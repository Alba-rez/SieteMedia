package recursos;

public class Carta {

    private Palo palo;
    private int numero;

    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    public String getPalo() {
        return palo.toString();
    }

    public int getNumero() {
        return numero;
    }


    @Override
    public String toString() {
        return "(" + palo + ", " + numero + ')';
    }

    public double getValor() {
        if(numero>=10){
            return 0.5;
        }else{
            return numero;
        }

    }
}