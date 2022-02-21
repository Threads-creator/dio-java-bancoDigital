package dio.model.Conta;

import java.util.Date;

public class ContaPoupanca extends Conta{

    @Override
    public void imprimirExtrato() {
        System.out.println("@@      Extrato Conta Poupança      @@");
        imprimirAtributos();
    }

    public Date getDataCriacao(){
        return this.dataCriacao;
    }
}
