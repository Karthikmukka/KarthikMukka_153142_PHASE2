package com.cg.mypaymentapp.pl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.PropertyConfigurator;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;
public class Client {
	private WalletService walletService;
	Scanner console=new Scanner(System.in);
	public Client(){
		walletService=new WalletServiceImpl();
	}
	public void menu() throws InvalidInputException,InsufficientBalanceException{
		System.out.println("\t ********************************************************************************");
		System.out.println("\t\tWelcome To MyPayment App");
		System.out.println("\t\t1)Create an Account");
		System.out.println("\t\t2)Show Balance");
		System.out.println("\t\t3)Deposit");
		System.out.println("\t\t4)Withdraw");
		System.out.println("\t\t5)Fund Transfer");
		System.out.println("\t\t6)View Transacctions");
		System.out.println("\t\t7)Exit");
		System.out.println("\t ********************************************************************************");
		int choice=console.nextInt();
		Customer customer;
		switch (choice) {
		case 1:
			Wallet wallet;
			System.out.print("\t\tEnter your Name    	 	: ");
			String name=console.next();
			System.out.print("\t\tEnter your Phone Number	 	: ");
			String mobileNo=console.next();
			System.out.print("\t\tEnter initial balance   	: ");
			BigDecimal amount=console.nextBigDecimal();
			
			customer=walletService.createAccount(name, mobileNo, amount);
			System.out.println("\tBank Account successfully created");
			break;
		case 2:
			System.out.println("\tEnter Phone Number");
			String phoneNumber=console.next();
			customer=walletService.showBalance(phoneNumber);
			BigDecimal balance=customer.getWallet().getBalance();
			System.out.println("\tAmount in your account is: "+balance);
			break;
		case 3:
			System.out.println("\tEnter Phone Number");
			String phoneNumber1=console.next();
			System.out.println("\tEnter Amount");
			BigDecimal amount1=console.nextBigDecimal();
			customer=walletService.depositAmount(phoneNumber1, amount1);
			break;
		case 4:
			System.out.println("\tEnter Phone Number");
			String phoneNumber2=console.next();
			System.out.println("\tEnter Amount");
			BigDecimal amount2=console.nextBigDecimal();
			customer=walletService.withdrawAmount(phoneNumber2, amount2);
			break;
		case 5:
			System.out.println("\tEnter Your Phone Number");
			String sourceMobileNo=console.next();
			System.out.println("\tEnter Beneficiary Phone Number");
			String targetMobileNo=console.next();
			System.out.print("\tEnter Amount   	: ");
			BigDecimal amount3=console.nextBigDecimal();
			customer=walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount3);
			break;
			
		case 6:
			Transactions transactions=new Transactions();
			
			System.out.println("\tEnter Phone Number");
			String phoneNumber3=console.next();
			List<Transactions> transactionList=walletService.viewTransactions(phoneNumber3);
			System.out.println("Account Number \t Date \t TransactionType \t Amount ");
			for(Transactions transaction:transactionList)
			
			{
			System.out.print(transaction.getMobileNo()+"\t");
			System.out.print(transaction.getDate()+"\t");
			System.out.print(transaction.getTransactionType()+"\t");
			System.out.print(transaction.getAmount()+"\t");
			System.out.println();
			}
			break;
			
		case 7:
			System.out.println("\tGoodBye");
			System.exit(0);
			break;
		}
	}
	public static void main(String[] args) 
	{
		PropertyConfigurator.configure("log4j.properties");
		Client client =new Client();
		while(true){
			try {
				client.menu();
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
