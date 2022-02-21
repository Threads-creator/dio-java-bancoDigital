package dio.model.Conta;

public class ContaCorrente extends Conta{
    @Override
    public void imprimirExtrato() {
        System.out.println("@@      Extrato Conta Corrente      @@");
        imprimirAtributos();
    }
}
