package trabalho1;
import java.util.Random;

public class Loteria {
    private Pessoa[] clientes = new Pessoa[100]; // vetor de clientes
    private Pessoa[] ganhadores = new Pessoa[100]; // vetor de ganhadores
    private Concurso[] concursos = new Concurso[100];
    private int quant_concursos;
    private int quant_clientes;
    private int quant_ganhadores;
    private int lucro;

    public Loteria(){
        this.quant_clientes = 0;
        this.quant_concursos = 0;
        this.quant_ganhadores = 0;
        this.lucro = 0;
    }

    public void criaConcurso(int num, int range, int preco){
        concursos[quant_concursos] = new Concurso(num, range, preco);
        this.quant_concursos++;
    }

    private void cadastraPessoa(Pessoa p){
        // checa se pessoa ja não foi cadastrado
        for (int i = 0; i < quant_clientes; i++){
            if(clientes[i].getId() == p.getId()){
                return;
            }
        }
        clientes[quant_clientes] = p;
        quant_clientes++;
    }

    // overwrite vender com varios possiveis argumentos
    public void vender(Pessoa p, int conc, int a1, int a2, int a3){
        if (conc >= quant_concursos){
            return;
        }
        Concurso c = concursos[conc-1];
        if ((c.getRange() < a1) || (c.getRange() < a2) || (c.getRange() < a3)){
            return;
        }
        c.vender(p, a1, a2, a3);
        this.lucro += c.getPreco();
        cadastraPessoa(p);
    }

    public void vender(Pessoa p, int conc){
        if (conc > quant_concursos){
            return;
        }
        Concurso c = concursos[conc-1];

        // sortear apostas
        Random rand = new Random();
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        
        while ((a1 == a2) || (a2 == a3) || (a1 == a3)){
            // o bilhete deve ser composto de 3 numeros diferentes
            a1 = rand.nextInt(c.getRange()) + 1;
            a2 = rand.nextInt(c.getRange()) + 1;
            a3 = rand.nextInt(c.getRange()) + 1;
        }
        c.vender(p, a1, a2, a3);
        this.lucro += c.getPreco();
        cadastraPessoa(p);
    }

    public void vender(Pessoa p, int a1, int a2, int a3){
        Concurso c;
        int i;
        for (i = 0; i < quant_concursos; i++){
            if (((concursos[i].getRange() < a1) || (concursos[i].getRange() < a2) || (concursos[i].getRange() < a3))){
                continue;
            }
            break;
        }
        c = concursos[i];
        if (i >= quant_concursos){
            Random rand = new Random();
            // apostas são altas demais
            // escolhe um concurso aleatorio
            c = concursos[rand.nextInt(quant_concursos)];

            a1 = 0;
            a2 = 0;
            a3 = 0;
            
            while ((a1 == a2) || (a2 == a3) || (a1 == a3)){
                // o bilhete deve ser composto de 3 numeros diferentes
                a1 = rand.nextInt(c.getRange()) + 1;
                a2 = rand.nextInt(c.getRange()) + 1;
                a3 = rand.nextInt(c.getRange()) + 1;
            } 
        }
        c.vender(p, a1, a2, a3);
        this.lucro += c.getPreco();
        cadastraPessoa(p);
    }

    public void vender(Pessoa p){
        Random rand = new Random();

        Concurso c = concursos[rand.nextInt(quant_concursos)];

        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
            
        while ((a1 == a2) || (a2 == a3) || (a1 == a3)){
            // o bilhete deve ser composto de 3 numeros diferentes
            a1 = rand.nextInt(c.getRange()) + 1;
            a2 = rand.nextInt(c.getRange()) + 1;
            a3 = rand.nextInt(c.getRange()) + 1;
        }
        c.vender(p, a1, a2, a3);
        this.lucro += c.getPreco();
        cadastraPessoa(p);
    }

    public boolean chegaGanhador(Bilhete b, int resultado){
        if ((b.getAposta(1) == resultado) || (b.getAposta(2) == resultado) || (b.getAposta(3) == resultado)){
            return true;
        }
        return false;
    }

    public void sorteiaResultado(){
        for (int i = 0; i < quant_concursos; i++){
            concursos[i].sorteiaResultado();
        }
        
        for (int i = 0; i < quant_clientes; i++){
            for (int j = 0; j < clientes[i].getQuantBilhetes(); j++){
                if (chegaGanhador(clientes[i].getBilhete(j), concursos[clientes[i].getBilhete(j).getNumConcuso() - 1].getResultado())){
                    boolean cadastrado = false;
                    for (int k = 0; k < quant_ganhadores; k++){
                        if (ganhadores[k].getId() == clientes[i].getId()){
                            cadastrado = true;
                        }
                    }
                    if (!cadastrado){
                        ganhadores[quant_ganhadores] = clientes[i];
                        quant_ganhadores++;
                    }
                }
            }
        }

        System.out.println("-----Sorteio dos Concursos-----");
        for (int i = 0; i < quant_concursos; i++){
            System.out.println("Concurso " + concursos[i].getNum() + " Resultado: " + concursos[i].getResultado());
        }
        System.out.println("-------------------");

        if (quant_ganhadores > 0){
            int premio = this.lucro / quant_ganhadores;

            System.err.println("-----Resultado da loteria-----");
            for (int i = 0; i < quant_ganhadores; i++){
                imprimeCliente(ganhadores[i]);
            }
            System.out.println("Premio para os jogadores: " + premio);
        }

    }

    // função auxiliar de imprimir cliente
    private void imprimeCliente(Pessoa p){
        System.err.println("----------------------");
        System.out.println("jogador: " + p.getId());
        System.out.println("bilhetes comprados:");
        for (int i = 0; i < p.getQuantBilhetes(); i++){
            System.out.println("bilhete para o concurso: " + p.getBilhete(i).getNumConcuso());
            System.out.println("apostas: " + p.getBilhete(i).getAposta(1) + " " + p.getBilhete(i).getAposta(2) + " "  + p.getBilhete(i).getAposta(3));
        }

    }
}
