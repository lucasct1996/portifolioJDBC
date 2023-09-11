package conta;

import cliente.Cliente;
import cliente.DadosCadastroCliente;

import java.math.BigDecimal;
import java.util.Scanner;

public class PizzariaAplicattion {

    private static ContaService service = new ContaService();
    private static Scanner teclado = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

    var opcao = exibirMenu();
        while (opcao != 7) {
        try {
            switch (opcao) {
                case 1:
                    abrirConta();
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    depositarConta();
                    break;
                case 4:
                    fazerPedido();
                    break;
                case 5:
                    deletarConta();
                    break;
                case 6:
                    sacarConta();
                    break;
            }
        } catch (RegraDeNegocioException e) {
            System.out.println("Erro: " +e.getMessage());
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
            teclado.next();
        }
        opcao = exibirMenu();
    }
    }

    private static void abrirConta(){
        System.out.println("Digite o nome da conta");
        var usuario = teclado.next();

        System.out.println("Digite o seu nome");
        var nome = teclado.next();

        System.out.println("Digite o seu CPF");
        var cpf = teclado.nextInt();

        System.out.println("Digite o seu endereco");
        var endereco = teclado.next();

        System.out.println("Digite o seu telefone");
        var telefone = teclado.nextInt();


        DadosCadastroCliente dados = new DadosCadastroCliente(nome, cpf, telefone, endereco);
        Cliente cliente = new Cliente(dados);

        Conta conta = new Conta(usuario, BigDecimal.ZERO);

        service.abrir(cliente, conta);
        System.out.println("Conta aberta com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void consultarSaldo(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        BigDecimal saldo = service.consultar(usuario);

            if (saldo != null) {
                System.out.println("Seu saldo é de " + saldo);
            } else {
                System.out.println("Usuário não encontrado ou saldo não disponivel.");
            }
        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }

    private static void depositarConta(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        System.out.println("Digite o valor do deposito");
        var valorDeposito = teclado.nextBigDecimal();

        service.realizarDeposito(usuario, valorDeposito);
        System.out.println("Deposito realizado com sucesso");
        BigDecimal saldo = service.consultar(usuario);

        System.out.println("Seu saldo novo é de: " + saldo);

        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }

    private static void deletarConta(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        service.deleteConta(usuario);

        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }

    private static void sacarConta(){
        System.out.println("Digite o nome do usuario");
        var usuario = teclado.next();

        System.out.println("Digite o valor que voce quer sacar");
        var valor = teclado.nextBigDecimal();

        service.realizarSaque(usuario, valor);
        System.out.println("Deposito realizado com sucesso");
        BigDecimal saldo = service.consultar(usuario);

        System.out.println("Seu saldo novo é de: " + saldo);

        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();
    }

    private static void fazerPedido(){
        System.out.println("""
                PIZZARIA ERECHIM - Cardapio:
                
                - Pizza de BACON: 75 R$
                - Pizza de STROGONOF: 90 R$
                - Pizza de QUEIJO: 60 R$
                - Pizza de CAMARAO: 100 R$
                - Pizza de CORACAO: 80 R$
                - Pizza de CHOCOLATE: 80 R$
                - Pizza de SORVETE: 88 R$
                
                """);

        System.out.println("Digite o nome do seu usuario");
        var usuario = teclado.next();

        System.out.println("Digite a pizza que você quer");
        var produto = teclado.next();

        service.fazerPedido(usuario, Produtos.valueOf(produto));
        System.out.println("Digite uma tecla para voltar ao menu");
        teclado.next();

    }

    private static int exibirMenu() {
        System.out.println("""
                PIZZARIA ERECHIM - ESCOLHA UMA OPÇÃO:
                1 - Criar conta
                2 - Consultar saldo
                3 - Depositar na Conta
                4 - Fazer pedido
                5 - Encerrar conta
                6 - Sacar
                7 - Sair
                """);
        return teclado.nextInt();
    }

}
