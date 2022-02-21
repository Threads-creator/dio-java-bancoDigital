package dio.model.Conta;

import java.util.Date;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    private double saldo;
    protected final Date dataCriacao;

    Conta(){
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.saldo = 0.0;
        this.dataCriacao = new Date();
    }


    @Override
    public void sacar(double valor) {
        if(this.getSaldo() >= valor){
            this.saldo = this.getSaldo() - valor;
        }
    }

    @Override
    public void depositar(double valor) {
        this.saldo = this.getSaldo() + valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if(this.getSaldo() >= valor){
            this.sacar(valor);
            contaDestino.depositar(valor);
        }
    }

    protected void imprimirAtributos(){
        System.out.println(String.format("| Numero  = %d  ", this.numero));
        System.out.println(String.format("| Agência = %d  ", this.agencia));
        System.out.println(String.format("| SALDO (R$) = %.2f  ", this.getSaldo()));
        System.out.println("");
    }

    public double getSaldo() {
        return saldo;
    }
}
