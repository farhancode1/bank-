package banking_Website;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class RBI {
public static void main(String[] args) {
	System.out.println("****Welcome to The Reserve Bank Of India*****");
	System.out.println("\n");
	System.out.println("Do you want to open an account: 1. Yes 2. No");
	Scanner sc=new Scanner(System.in);
	String choice = sc.nextLine();
	if(choice.equalsIgnoreCase("Yes"))
	{
		OpenAccount obj=new OpenAccount();
		obj.createAccount();
		BankAccount obj1=new BankAccount();
		obj1.showMenu();
	}
	if(choice.equalsIgnoreCase("No")) {
		System.out.println("Do you already have account: 1. Yes 2. No");
		String valid=sc.nextLine();    
		if(valid.equalsIgnoreCase("Yes")) {
		BankAccount obj1=new BankAccount();
		obj1.showMenu();}
		else {
			System.out.println("Thank you for using our services");
		}
	}
}
}
class OpenAccount{
	String name;
	int accountNum;
	String accountType;
	String dob;
	String bank;
	int choice;
	
	void createAccount() {
		Scanner sc=new Scanner(System.in);
		System.out.println("In which bank do you want to open the account: 1.SBI  2.BOB  3.PNB");
		 choice=sc.nextInt();
		if(choice==1) {
			bank="SBI";
		}
		if(choice==2) {
			bank="BOB";
		}
		if(choice==3) {
			bank="PNB";
		}
		System.out.println("Please enter your full name");
		sc.nextLine();
		name=sc.nextLine();
		System.out.println("Enter date of birth(dd/mm/yyyy)");
		
		dob=sc.nextLine();
		System.out.println("Which type of account you want to open 1.Saving  2.Current");
		int choice1=sc.nextInt();
		if(choice1==1) {
			accountType="Saving";
		}
		if(choice1==2) {
			accountType="Current";
		}
		System.out.println("Processing**********************************");
		System.out.println("Your account has been created");
		System.out.println("Bank:"+bank);
		System.out.println("Name:"+name);
		System.out.println("DOB:"+dob);
		System.out.println("AccountType:-"+accountType);
		System.out.println("AccountNumber:-"+Math.random());
		System.out.println("Thankyou for choosing "+bank);
		System.out.println("\n");
		BankAccount obj1=new BankAccount();
		
		}
		
		}
class BankAccount{
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	String accountType;
	double totalinterest;
	
	void calculateInterest(double balance) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Which type of account you  have? 1.Saving  2.Current");
		int choice2=sc.nextInt();
		/*OpenAccount ob=new OpenAccount();
		int choice2=ob.choice;*/
		if(choice2==1) {
			accountType="Saving";
			int r=5;
			System.out.println("For how many year you want to calculate interest");
			int t=sc.nextInt();
			double totalAmount=balance*(1+(r*t));
			totalinterest=totalAmount-balance;
			System.out.println("Total interest earned:"+totalinterest);
			
		}
		if(choice2==2) {
			accountType="Current";
			int r=8;
			System.out.println("For how many year you want to calculate interest");
			int t=sc.nextInt();
			System.out.println("enter for how many times compound interest apply");
			int n=sc.nextInt();
			
			double totalAmount=balance*(Math.pow((1+r/n), n*t));
			totalinterest=totalAmount-balance;
			System.out.println("Total interest earned:"+totalinterest);
			
		}
		
		}
	void deposit(int amount) {
		if(amount!=0) {
			balance=balance+amount;
			System.out.println("Balance after deposit:"+balance+"Rs");
			previousTransaction=amount;
		}
	}
	void withdraw(int amount) {
		
		if(balance!=0&&amount!=0) {
			balance=balance-amount;
			System.out.println("Balance after widtdraw:"+balance+"Rs");
			previousTransaction= -amount;
		}
		else {
			System.out.println("Low Balance");
		}
		
	}
	void getPreviousTransaction() {
		System.out.println("Previous transaction is"+previousTransaction);
	}
	void reciept(){
		String s="";
		OpenAccount r=new OpenAccount();
		if(balance==0) {
			
			System.out.println("******************************************");
			System.out.println("Account No:"+Math.random());
			System.out.println("No transaction yet");
			System.out.println("******************************************");
		}
		else {
		try {
				File f= new File("reciept.txt");
				f.createNewFile();
				FileWriter fw=new FileWriter(f);
				
				String n="*************************************************"+"\n"+"Account No:"+Math.random()+"\n"+"Account Balance:"+balance+"\n"+"Previous transaction:"+previousTransaction+"\n"+"TotalInterest"+totalinterest;
				fw.write(n);
				fw.flush();
				fw.close();
				FileReader fr=new FileReader(f);
				char[]ch=new char[(int)f.length()];
				fr.read(ch);
				for(char ch1:ch)
				{s+=ch1;
				}
		System.out.println(s);}
		catch(Exception e) {
			System.out.println("No Transaction");
		}
		}
	}
	void showMenu() {
		char option=' ';
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the main menu");
		System.out.println("\n");
		System.out.println("A. check Balance");
		System.out.println("B. Deposit Amount");
		System.out.println("C. Withdraw Amount");
		System.out.println("D. See Previous transaction");
		System.out.println("E. Calculate Interest");
		System.out.println("F. Print mini receipt");
		System.out.println("G. Exit");
		
		do {
			System.out.println("************************************************");
			System.out.println("Enter an option");
			System.out.println("************************************************");
			option=sc.next().charAt(0);
			System.out.println("\n");
			switch(option) {
			case 'A':
				System.out.println("------------------------------------------------------");
				System.out.println("Balance ="+ balance);
				System.out.println("\n");
				break;
			
			case 'B':
				System.out.println("------------------------------------------------------");
				System.out.println("Enter the amount to deposit");
			
				int x=sc.nextInt();
				deposit(x);
				System.out.println("\n");
				break;
				
			case 'C':
				System.out.println("------------------------------------------------------");
				System.out.println("Enter the amount to withdraw");
			
				int y=sc.nextInt();
				withdraw(y);
				System.out.println("\n");
				break;
				
			case 'D':
				System.out.println("------------------------------------------------------");
				getPreviousTransaction();
				break;
			case 'E':
				System.out.println("------------------------------------------------------");
				calculateInterest(balance);
				System.out.println("\n");
				break;
			case 'F':
			reciept();
			break;
			default:
				System.out.println("Enter Invalid option.please enter again");
				break;
			}
		}while(option!='G');
		System.out.println("Thank you for using our services");
		
		
	}
	}



