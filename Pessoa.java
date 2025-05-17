package trabalho1;

public class Pessoa {
    private int id;
    private int saldo;
    private int quant_bilhetes;
    private Bilhete[] bilhetes = new Bilhete[100];

    public Pessoa(int id, int saldo) {
        this.id = id;
        this.saldo = saldo;
        this.quant_bilhetes = 0;
    }

    public void alocaAposta(int conc, int a1, int a2, int a3) {
        bilhetes[quant_bilhetes] = new Bilhete(conc);
        bilhetes[quant_bilhetes].setAposta(1, a1);
        bilhetes[quant_bilhetes].setAposta(2, a2);
        bilhetes[quant_bilhetes].setAposta(3, a3);
        this.quant_bilhetes++;
    }

    public int getSaldo(){
        return this.saldo;
    }

    public void subtraiSaldo(int preco){
        this.saldo = this.saldo - preco;
    }

    public int getId(){
        return this.id;
    }

    public int getQuantBilhetes(){
        return this.quant_bilhetes;
    }

    public Bilhete getBilhete(int idx){
        return bilhetes[idx];
    }

}
