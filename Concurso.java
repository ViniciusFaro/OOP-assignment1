package trabalho1_estudo;
import java.util.Random;

public class Concurso {
    private int numero;
    private int resultado;
    private int rangeApostas;

    public Concurso(int num, int range) {
        this.rangeApostas = range;
        this.numero = num;
    }

    public void sorteia_resultado() {
        Random rand = new Random();
        this.resultado = rand.nextInt(this.rangeApostas) + 1;
    }

    public void vender(Pessoa p, int a1, int a2, int a3) {
        p.alocaAposta(this.numero ,a1, a2, a3);
    }

    public int getRange() {
        return this.rangeApostas;
    }

    public int getNum() {
        return this.numero;
    }

    public int getResultado() {
        return this.resultado;
    }
}
