package dio;

import dio.model.Banco.Banco;
import dio.model.Cliente.Cliente;

import java.io.IOException;
import java.util.Scanner;

public class InitialClass {


    private static Banco banco = new Banco("Banco Big", "1234556");

    public static void main(String[] args) throws IOException, InterruptedException {



        Scanner scan = new Scanner(System.in);
        int option = 0;

        do{
            banco.imprimirInfo();
            imprimirMenuPrincipal();
            option = scan.nextInt();

            switch(option){
                case 1:
                    banco.imprimirClientes();
                    break;
                case 2:
                    adicionarCliente();
                    break;
                case 3:
                    entrarConta();
                    break;
                default:
                    break;
            }
            Thread.sleep(2000);
            limparConsole();

        }while (option != 0);

        System.out.println("|  Obrigado por testar o projeto !!  |");


    }

    public static void imprimirMenuPrincipal(){
        System.out.println("|       Menu do Banco            |");
        System.out.println("| 1 - Listar Clientes            |");
        System.out.println("| 2 - Adicionar Clientes         |");
        System.out.println("| 3 - Entrar Conta Cliente       |");
        System.out.println("| 0 - SAIR                       |");
    }

    public static void adicionarCliente(){
        Scanner scan = new Scanner(System.in);

        System.out.println("|        Menu Adicionar Cliente       |");
        System.out.println("| Insira um RG (apenas numeros):      |");
        int rg = scan.nextInt();
        System.out.println("| Insira um Nome :                    |");
        String nome = scan.next();

        Cliente cliente = new Cliente(nome, Integer.toString(rg));
        banco.addCliente(cliente);
        System.out.println("|    Cliente Criado com Sucesso !!   |");


    }

    public static void entrarConta() throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.println("|        Menu Entrar Conta       |");
        System.out.println("| Insira um Nome :               |");
        String nome = scan.next();

        Cliente clienteAlvo = banco.getClienteByName(nome);
        if(clienteAlvo != null){
            opcoesClienteLogado(clienteAlvo);
            return;
        }

        System.out.println("|    Cliente não encontrado :(  |");
    }



    public static void imprimirMenuCliente(Cliente cliente){
        System.out.println("|       Menu do Cliente            |");
        System.out.println(String.format("| Saldo (R$) = %.2f", cliente.getContaCorrente().getSaldo()));
        System.out.println("| 1 - Sacar                        |");
        System.out.println("| 2 - Depositar                    |");
        System.out.println("| 3 - Transferir                   |");
        System.out.println("| 4 - Listar Contas                |");
        System.out.println("| 0 - SAIR                         |");
    }

    public static void opcoesClienteLogado(Cliente cliente) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int option = 0;

        do{
            limparConsole();
            imprimirMenuCliente(cliente);
            option = scan.nextInt();

            switch(option){
                case 1:
                    sacar(cliente, scan);
                    break;
                case 2:
                    depositar(cliente, scan);
                    break;
                case 3:
                    transferir(cliente, scan);
                    Thread.sleep(1500);
                    break;
                case 4:
                    exibirContas(cliente, scan);
                    break;
                default:
                    break;
            }
        }while (option != 0);
    }

    private static void exibirContas(Cliente cliente, Scanner scan) {
        String nome;
        cliente.imprimirContas();
        System.out.println("| Digite qualquer coisa para sair   |");
        nome = scan.next();
    }

    private static void transferir(Cliente cliente, Scanner scan) {
        double valor;
        System.out.println("| Transferindo                 |");
        System.out.println("| Insira um nome:              |");
        String nome = scan.next();
        Cliente destinatario = banco.getClienteByName(nome);
        if(destinatario != null){
            System.out.println("| Insira um valor (R$):        |");
            valor = scan.nextDouble();
            cliente.getContaCorrente().transferir(valor, destinatario.getContaCorrente());
            return;
        }
        System.out.println("| Destinatario nao encontrado   |");
    }

    private static void depositar(Cliente cliente, Scanner scan) {
        double valor;
        System.out.println("| Depositando                  |");
        System.out.println("| Insira um valor (R$):        |");
        valor = scan.nextDouble();
        cliente.getContaCorrente().depositar(valor);
    }

    private static void sacar(Cliente cliente, Scanner scan) {
        System.out.println("| Sacando                      |");
        System.out.println("| Insira um valor (R$):        |");
        double valor = scan.nextDouble();
        cliente.getContaCorrente().sacar(valor);
    }

    public static void limparConsole(){
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
    }
}
