package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.utill.Cores;

public class Menu {

	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;

		System.out.println("\nCriar Contas\n");
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "José da Silva", 1000f, 1000.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria dos Silva", 2000.0f, 1000f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000.0f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000.0f, 12);
		contas.cadastrar(cp2);

		contas.listarTodas();

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("************************************");
			System.out.println("                                    ");
			System.out.println("         BANCO DO BRAZIL            ");
			System.out.println("                                    ");
			System.out.println("************************************");
			System.out.println("      1- Criar Conta                ");
			System.out.println("      2- Listar todas as Contas     ");
			System.out.println("      3- Buscar Contas da Conta     ");
			System.out.println("      4- Atualizar Dados da Conta   ");
			System.out.println("      5- Apagar Conta               ");
			System.out.println("      6- Sacar                      ");
			System.out.println("      7- Depositar                  ");
			System.out.println("      8- Transferir valores entre contas");
			System.out.println("      9- Sair                       ");
			System.out.println("                                      ");
			System.out.println("************************************");
			System.out.println("  Entre na opção desejada      ");
			System.out.println("                                      ");

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
				System.out.println("Dgite o numero da agencia:");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println("Digite o tipo da conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o saldo da conta (R$: ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite de crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}
				break;
			case 2:
				System.out.println("Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por numero\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");

				System.out.println("Digite o numero da conta :");
				numero = leia.nextInt();

				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {

					tipo = buscaConta.getTipo();

					System.out.println("Digite o numero da agencia: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();

					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de credito (R$): ");
						limite = leia.nextFloat();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

					}
					case 2 -> {
						System.out.println("Digite o dia do aniversario da conta: ");
						aniversario = leia.nextInt();

						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));

					}
					default -> {
						System.out.println("Tipo de conta invalido!");
					}
					}
				} else {
					System.out.println("A Conta nao foi encontrada!");
				}

				keyPress();
				break;
			case 5:
				System.out.println("Apagar a Conta\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println("Saque\n\n");

				keyPress();
				break;
			case 7:
				System.out.println("Depósito\n\n");

				keyPress();
				break;
			case 8:
				System.out.println("Transferência entre Contas\n\n");

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida!\n");

				keyPress();
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n**");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Generation Brasil - generation@generation.org");
		System.out.println("github.com/conteudoGeneration");
		System.out.println("**");
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}