package dio.Exception;

public class NomeMalFormadoException extends Exception{

    private String nome;

    public NomeMalFormadoException(String nome) {
        super("Nome" + nome + " Mal Formatado ");
        this.nome = nome;
    }
}
