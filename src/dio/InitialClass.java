package dio;

import dio.Exception.NomeMalFormadoException;
import dio.model.Banco.Banco;
import dio.model.Cliente.Cliente;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InitialClass {


    private static Banco banco = new Banco("Banco Big", "1234556");

    public static void main(String[] args) throws IOException, InterruptedException {



        Scanner scan = new Scanner(System.in);
        int option = 0;

        try{
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
        }catch (Exception e){
        }finally {
            System.out.println("|  Obrigado por testar o projeto !!  |");
        }





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

        int[] numeros = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        try{
            System.out.println("|        Menu Adicionar Cliente       |");
            System.out.println("| Insira um RG (apenas numeros):      |");
            int rg = scan.nextInt();
            System.out.println("| Insira um Nome :                    |");
            String nome = scan.next();

            for(int i = 0; i < numeros.length; i++){
                if(nome.contains(Integer.toString(numeros[i]))){
                    throw new NomeMalFormadoException(nome);
                }
            }

            Cliente cliente = new Cliente(nome, Integer.toString(rg));
            banco.addCliente(cliente);
            System.out.println("|    Cliente Criado com Sucesso !!   |");

        }catch (NumberFormatException | InputMismatchException ime){
            System.out.println("|    RG inválido !!    :(            |");
        }catch (NomeMalFormadoException mmfe) {
            System.out.println("|    Nome inválido !!    :(          |");
        }catch (Exception e){
            System.out.println("|    Errro inesperado !!    :(          |");
        }


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

        try{
            do{
                limparConsole();
                imprimirMenuCliente(cliente);
                option = scan.nextInt();

                switch(option){
                    case 1:
                        sacar(cliente);
                        break;
                    case 2:
                        depositar(cliente);
                        break;
                    case 3:
                        transferir(cliente);
                        Thread.sleep(1500);
                        break;
                    case 4:
                        exibirContas(cliente);
                        break;
                    default:
                        break;
                }
            }while (option != 0);
        }catch (Exception e){}

    }

    private static void exibirContas(Cliente cliente) {
        Scanner scan  = new Scanner(System.in);
        String nome;
        cliente.imprimirContas();
        System.out.println("| Digite qualquer coisa para sair   |");
        nome = scan.next();
    }

    private static void transferir(Cliente cliente) {
        Scanner scan  = new Scanner(System.in);
        double valor;
        System.out.println("| Transferindo                 |");
        System.out.println("| Insira um nome:              |");
        String nome = scan.next();
        Cliente destinatario = banco.getClienteByName(nome);
        if(destinatario != null){
            try{
                System.out.println("| Insira um valor (R$):        |");
                valor = scan.nextDouble();
                cliente.getContaCorrente().transferir(valor, destinatario.getContaCorrente());

            }catch (InputMismatchException | NumberFormatException mfe){
                System.out.println("| Valor Inválido :(           |");
            }catch (Exception e){
                e.printStackTrace();
            }
            return;
        }
        System.out.println("| Destinatario nao encontrado   |");
    }

    private static void depositar(Cliente cliente) {
        Scanner scan  = new Scanner(System.in);
        try{

            double valor;
            System.out.println("| Depositando                  |");
            System.out.println("| Insira um valor (R$):        |");
            valor = scan.nextDouble();
            cliente.getContaCorrente().depositar(valor);

        }catch (InputMismatchException | NumberFormatException mfe){
            System.out.println("| Valor Inválido :(           |");
        }catch (Exception e){
            System.out.println("| Erro inesperado, Operação nao realizada :( |");
        }
    }

    private static void sacar(Cliente cliente) {
        Scanner scan  = new Scanner(System.in);
        try{

            System.out.println("| Sacando                      |");
            System.out.println("| Insira um valor (R$):        |");
            double valor = scan.nextDouble();
            cliente.getContaCorrente().sacar(valor);

        }catch (InputMismatchException | NumberFormatException mfe){
            System.out.println("| Valor Inválido :(           |");
        }catch (Exception e){
            System.out.println("| Erro inesperado, Operação nao realizada :( |");
        }

    }

    public static void limparConsole(){
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
    }
}
