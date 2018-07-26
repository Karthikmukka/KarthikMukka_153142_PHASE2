
package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;


public class WalletServiceImpl implements WalletService
{
	static Logger myLogger=Logger.getLogger(WalletServiceImpl.class);
	private WalletRepo repo= new WalletRepoImpl();
	Customer customer;
	private Map<String, Customer> data;
	/*List<Transactions> data;*/
	public WalletServiceImpl(Map<String, Customer> data)
	{
		this.data=data;
	}
	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}

	public WalletServiceImpl() 
	{

	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount)  
	{
		myLogger.info("In createAccount method");
		if(!isValidName(name)||!isValidMobile(mobileNo)||!isValidAmount(amount))
		{
			myLogger.error("invalid inputs while creating");
			throw new InvalidInputException("Invalid Inputs");
		}
			else
		{
			
			Wallet wallet=new Wallet(amount);
			customer=new Customer();
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(wallet);
			
			repo.save(customer);
		
			
				myLogger.info("Account Successfully created");
				return customer;
			
		}
					
	}

	
	
	public Customer showBalance(String mobileNo) 
	{
		myLogger.info("In showBalance()");
		if(!isValidMobile(mobileNo))
		{
			
			throw new InvalidInputException("Incorrect mobile number");
		}
		else
		{
		Customer customer=repo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
		{
			myLogger.error("No account found");
			throw new InvalidInputException("Account with this mobile number not found");
		}
		}
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) 
	{
		myLogger.info("In fundTransfer()");
		if(!isValidMobile(sourceMobileNo)&&!isValidMobile(targetMobileNo)&&!isValidAmount(amount))
		{
			myLogger.error("Invalid inputs");
			throw new InvalidInputException("Invalid Inputs");
		}
		else
		{
			Customer customer1=repo.findOne(sourceMobileNo);
			Customer customer2=repo.findOne(targetMobileNo);
			if(customer1!=null&&customer2!=null)
			{
				Wallet wal1=customer1.getWallet();
				Wallet wal2=customer2.getWallet();
				BigDecimal balance1=wal1.getBalance();
				BigDecimal balance2=wal2.getBalance();
				if(balance1.compareTo(amount)>0)
				{
					wal1.setBalance(balance1.subtract(amount));
					repo.update(customer1, "Fund Transfer");
					wal2.setBalance(balance2.add(amount));
					repo.update(customer2, "Fund Transfer");
					return customer1;
				}
				else
				{
					throw new InsufficientBalanceException("Insufficient balance");
				}
			}
			else
			{
				myLogger.error("No account found");
				throw new InvalidInputException("Account with this mobile number not found");
			}
			
		}
		
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) 
	{
		myLogger.info("In depositAmount()");
		if(!isValidMobile(mobileNo)&&!isValidAmount(amount))
		{
			myLogger.error("Invalid inputs");
			throw new InvalidInputException("Invalid Inputs");
		}
			
		else
		{
			Customer customer=repo.findOne(mobileNo);
			if(customer!=null)
			{
			Wallet wal=customer.getWallet();
			wal.setBalance(wal.getBalance().add(amount));
			repo.update(customer, "Deposit");
			return customer;
			}
			else
			{
				myLogger.error("No account found");
				throw new InvalidInputException("Account with this mobile number not found");
			}
		}
			
		
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) 
	{
		myLogger.info("In withdraw()");
		if(!isValidMobile(mobileNo)&&!isValidAmount(amount))
		{
			myLogger.error("Invalid inputs");
		
			throw new InvalidInputException("invalid inputs");
		}
		else
		{
			Customer customer=repo.findOne(mobileNo);
			if(customer!=null)
			{
			Wallet wal=customer.getWallet();
			if(wal.getBalance().compareTo(amount)>0)
			{
			wal.setBalance(wal.getBalance().subtract(amount));
			repo.update(customer, "Withdraw");
			return customer;
			}
			else 
			{
				throw new InsufficientBalanceException("Insufficient balance");
			}
			}
			else
			{
				myLogger.error("No account found");
				throw new InvalidInputException("Account with this mobile number not found");
			}
		}
	}
	
	@Override
	public List<Transactions> viewTransactions(String mobileNo) 
	{
		List<Transactions> transactionList;
		Customer customer=repo.findOne(mobileNo);
		
		if(customer!=null)
		{
		
		transactionList=repo.transactions(mobileNo);
		return transactionList;
		}
		else
		{
			myLogger.error("No account found");
			throw new InvalidInputException("Account with this mobile number not found");
		}
	}
	//validation
	private boolean isValidName(String name) {
		if(String.valueOf(name).matches("[A-Za-z]+")) 
			return true;		
		else 
			return false;
	}
	private boolean isValidAmount(BigDecimal amount)
	{
		BigDecimal num=new BigDecimal("0");
		if(amount.compareTo(num)>0)
			return true;
		else 
			return false;
	}
	private boolean isValidMobile(String mobileNo) 
	{
		if(String.valueOf(mobileNo).matches("[1-9][0-9]{9}")) 
			return true;		
		else 
			return false;
	}
	
}
