package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Account;
import entities.BusinessAccount;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		BusinessAccount accountB = new BusinessAccount();
		Account account = new Account();
		Scanner sc = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);

		int accountType;

		System.out.println("Que tipo de aconta deseja abrir ?");
		System.out.print("Conta Pessoal = 1 ou conta Empresarial = 2 : ");
		accountType = sc.nextInt();
		while (accountType < 1 || accountType > 2) {
			System.out.println("Numero invalido, entre com 1 para conta Pessoal ou 2 para conta Empresarial");
			accountType = sc.nextInt();
		}
		if (accountType == 1) {
			System.out.println("Conta Pessoal selecionada");
			System.out.println("Entre com os dados");
			System.out.print("Numero da conta : ");
			int accountNumber = sc.nextInt();
			while (accountNumber < 1) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				accountNumber = sc.nextInt();
			}
			System.out.print("Nome do titular da conta : ");
			String accountHolder = scString.nextLine();
			System.out.print("Valor inicial da conta em $ : ");
			double accountBalance = sc.nextDouble();
			while (accountBalance < 1) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				accountBalance = sc.nextInt();
			}

			account = new Account(accountNumber, accountHolder, accountBalance);
			
			System.out.println(account);
			
		}
		else {
			System.out.println("Conta Empresarial selecionada");
			System.out.println("Entre com os dados");
			System.out.print("Numero da conta : ");
			int accountNumber = sc.nextInt();
			while (accountNumber < 1) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				accountNumber = sc.nextInt();
			}
			System.out.print("Nome do titular da conta : ");
			String accountHolder = scString.nextLine();
			System.out.print("Valor inicial da conta em $ : ");
			double accountBalance = sc.nextDouble();
			while (accountBalance < 1) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				accountBalance = sc.nextInt();
			}
			System.out.println("Qual o valor maximo para emprestimo para a conta : ");
			double accountLoanLimit = sc.nextDouble();
			while (accountLoanLimit < 1) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				accountLoanLimit = sc.nextInt();
			}
			
			accountB = new BusinessAccount(accountNumber, accountHolder, accountBalance, accountLoanLimit);
			
			System.out.println(accountB);
		}

		System.out.println("Qual operação deseja efetuar");
		System.out.println("Deposito 1");
		System.out.println("Retirada 2");
		if(accountType == 2) {
			System.out.println("Emprestimo 3");
		}
		int operation = sc.nextInt();
		while (((operation < 1 || operation > 2) && accountType == 1) || ((operation < 1 || operation > 3) && accountType == 2)) {
			System.out.println("Numero invalido");
			System.out.println("Deposito 1");
			System.out.println("Retirada 2");
			if(accountType == 2) {
				System.out.println("Emprestimo 3");
			}
			operation = sc.nextInt();
		}
		
		if(operation == 1) {
			System.out.print("Deposito selecionado, digite quanto deseja depositar em $ : ");
			double deposit = sc.nextDouble();
			while (deposit < 0) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				deposit = sc.nextDouble();
			}
			if(accountType == 1) {
				account.deposit(deposit);
			}
			else {
				accountB.deposit(deposit);
			}
		}
		else if(operation == 2){
			System.out.print("Retirada selecionada, digite quanto deseja retirar em $ : ");
			double withdraw = sc.nextDouble();
			while (withdraw < 0) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				withdraw = sc.nextDouble();
			}
			if(accountType == 1) {
				while(withdraw > account.getBalance()) {
					System.out.println("Saldo insuficiente.");
					System.out.print("Entre com um valor até o saldo da conta : ");
					withdraw = sc.nextDouble();
				}
				account.withdraw(withdraw);
			}
			else {
				while(withdraw > account.getBalance()) {
					System.out.println("Saldo insuficiente.");
					System.out.print("Entre com um valor até o saldo da conta : ");
					withdraw = sc.nextDouble();
				}
				accountB.withdraw(withdraw);
			}
		}
		else {
			System.out.print("Emprestimo selecionado ($10 sera cobrado para essa operação), digite quanto deseja pegar emprestado em $ : ");
			double loan = sc.nextDouble();
			while (loan < 0) {
				System.out.print("Numero invalido, entre com um numero maior que 0 :");
				loan = sc.nextDouble();
			}
			while(loan > accountB.getLoanLimit()) {
				System.out.println("O valor excede o maximo de emprestimo para essa conta.");
				System.out.println("Entre com um valor até o limite de emprestimo : ");
				loan = sc.nextDouble();
			}
			accountB.loan(loan);
			
		}
		if(accountType == 1) {
			System.out.println(account);
		}
		else {
			System.out.println(accountB);
		}
		
		sc.close();
		scString.close();
	}
}
