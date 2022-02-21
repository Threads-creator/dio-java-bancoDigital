package dio.model.Cliente;

import dio.model.Conta.Conta;
import dio.model.Conta.ContaCorrente;
import dio.model.Conta.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String RG;
    private List<Conta> contas = new ArrayList<Conta>();

    public Cliente(String nome, String RG) {
        this.nome = nome;
        this.RG = RG;
        this.contas.add(new ContaCorrente());

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }



    public List<Conta> getContas() { return this.contas; }

    // permite que toda conta tenha Somente uma Conta Corrente
    public void addConta(Conta newConta){
        if(ContaPoupanca.class.isInstance(newConta)){
            this.contas.add(newConta);
        }
    }

    public Conta getContaCorrente(){
        for(Conta conta : contas) {
            if(ContaCorrente.class.isInstance(conta)){
                return conta;
            }
        }
        return null;
    }
    public List<Conta> getContaPoupanca(){
        List<Conta> contasPoupanca = new ArrayList<Conta>();
        for(Conta conta : contas) {
            if(ContaPoupanca.class.isInstance(conta)){
                contasPoupanca.add(conta);
            }
        }
        return contasPoupanca;
    }

    public void imprimirContas(){
        System.out.println("|| Imprimindo Contas de " + this.nome + " ||");
        for(Conta conta : contas) {
            conta.imprimirExtrato();
        }
    }
}
