package trabalho1;

public class Bilhete {
    private int concurso_prentencente;
    private int a1;
    private int a2;
    private int a3;

    public Bilhete(int conc) {
        this.concurso_prentencente = conc;
    }

    public void setAposta(int idx, int valor) {
        if (idx == 1) {
            this.a1 = valor;
            return;
        }
        if (idx == 2) {
            this.a2 = valor;
            return;
        }
        if (idx == 3) {
            this.a3 = valor;
            return;
        } else {
            System.out.println("Endereço de bilhete inválido");
            return;
        }
    }

    public int getAposta(int idx) {
        if (idx == 1) {
            return this.a1;
        }
        if (idx == 2) {
            return this.a2;
        }
        if (idx == 3) {
            return this.a3;
        } else {
            System.out.println("Endereço de bilhete inválido");
            return -1;
        }
    }

    public int getNumConcuso() {
        return this.concurso_prentencente;
    }
}
