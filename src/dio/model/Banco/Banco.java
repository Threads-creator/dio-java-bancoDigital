package dio.model.Banco;

import dio.model.Cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private String CNPJ;
    private List<Cliente> clientes = new ArrayList<Cliente>();

    public Banco(String nome, String CNPJ){
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void addCliente(Cliente[] clientes) {
        for(Cliente cliente : clientes) {
            this.clientes.add(cliente);
        }
    }

    public Cliente getClienteByName(String nome){
        Cliente clienteAlvo = null;
        for(Cliente cliente : this.getClientes()){
            if(cliente.getNome().equals(nome)){
                clienteAlvo = cliente;
                break;
            }
        }
        return clienteAlvo;
    }


    public void imprimirInfo(){
        System.out.println("@@--------------------------------@@");
        System.out.println(" Banco =       " + this.nome + "       ");
        System.out.println(" CNPJ = " + this.CNPJ + "       ");
        System.out.println("@@--------------------------------@@");
        System.out.println();
    }

    public void imprimirClientes() {
        System.out.println("@@  Imprimindo clientes do banco < " + this.nome + " >  @@");
        for(Cliente cliente : clientes){
            System.out.println("| Cliente =  " + cliente.getNome());
        }
        System.out.println();
    }
}
