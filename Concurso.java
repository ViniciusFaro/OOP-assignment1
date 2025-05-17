package trabalho1;
import java.util.Random;

public class Concurso {
    private int numero;
    private int resultado;
    private int rangeApostas;
    private int preco;

    public Concurso(int num, int range, int preco) {
        this.rangeApostas = range;
        this.numero = num;
        this.preco = preco;
    }

    public void sorteiaResultado() {
        Random rand = new Random();
        this.resultado = rand.nextInt(this.rangeApostas) + 1;
    }

    public boolean vender(Pessoa p, int a1, int a2, int a3) {
        if (p.getSaldo() < this.preco){
            System.out.println("Falha na compra de bilhetes da pessoa " + p.getId() + " saldo insuficiente");
            return false;
        }
        p.alocaAposta(this.numero, a1, a2, a3);
        p.subtraiSaldo(this.preco);
        
        return true;
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

    public int getPreco(){
        return this.preco;
    }
}
