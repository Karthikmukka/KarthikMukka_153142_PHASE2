package com.cg.mypaymentapp.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
WalletService service;
	
	@Before
	public void initData(){
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
			
	}
	@Test(expected=Exception.class)
	public void test2()
	{
		Customer customer=service.createAccount(null, null, new BigDecimal(7000));
		assertNotNull(customer);
	}
	@Test
	public void test3()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 data.put("9900112212", cust1);
		 service= new WalletServiceImpl(data);
		 assertEquals(cust1.getName(), "Amit");
	}
	@Test
	public void test4()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 data.put("9963242422", cust2);	
		 service= new WalletServiceImpl(data);
		 assertEquals(cust2.getMobileNo(), "9963242422");
	}
	@Test
	public void test5()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
			Wallet w1=cust3.getWallet();
		 assertEquals(w1.getBalance(), new BigDecimal(7000));
	}
	@Test
	public void test6()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer(null, "9900112212",new Wallet(new BigDecimal(9000)));
		// data.put("9900112212", cust1);
		 //service= new WalletServiceImpl(data);
		assertNull(cust1.getName());
	}
	@Test
	public void test7()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust2=new Customer("Ajay", null,new Wallet(new BigDecimal(6000)));
		 //data.put("9963242422", cust2);	
		// service= new WalletServiceImpl(data);
		 assertNull(cust2.getMobileNo());
	}
	@Test
	public void test8()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(null));
		 Wallet w1=cust2.getWallet();
		 assertNull(w1.getBalance());
	}
	@Test
	public void test9()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Ajay", "9900112212",new Wallet(new BigDecimal(9000)));
		// data.put("9900112212", cust1);
		 //service= new WalletServiceImpl(data);
		assertNotNull(cust1.getName());
	}
	@Test
	public void test10()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 //data.put("9963242422", cust2);	
		// service= new WalletServiceImpl(data);
		 assertNotNull(cust2.getMobileNo());
	}
	@Test
	public void test11()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer(null, "9900112212",new Wallet(new BigDecimal(9000)));
		 Wallet w1=cust1.getWallet();
		 assertNotNull(w1.getBalance());
	}
	
	@Test
	public void test13()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		Customer cust1=new Customer(null, "9900112212",new Wallet(new BigDecimal(9000)));
	}
	@Test
	public void test12()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		Customer cust2=new Customer("Ajay", "9963242422",new Wallet(null));
	}
	@Test
	public void test14()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
			Wallet w1=cust3.getWallet();
		 assertNotSame(w1.getBalance(), new BigDecimal(8000));
	}
	@Test
	public void test15()
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 data.put("9900112212", cust1);
		 service= new WalletServiceImpl(data);
		 assertNotSame(cust1.getName(), "Ajay");
	}
	
	@Test(expected=InvalidInputException.class)
	public void test16()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount(null, null, null);
	}
	@Test(expected=NullPointerException.class)
	public void test17()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount(null, "9671221222", null);
	}
	@Test(expected=InvalidInputException.class)
	public void test18()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount("mndp",null, null);
	}
	@Test(expected=InvalidInputException.class)
	public void test19()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount("mndp","9887", null);
	}
	@Test(expected=InvalidInputException.class)
	public void test20()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount("mndp","0887111111", null);
	}
	@Test(expected=InvalidInputException.class)
	public void test21()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount("mndp","hgkljh", null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void test23()
	{
		WalletService customer=new WalletServiceImpl();
		customer.showBalance("123456789");
	}
	@Test(expected=InvalidInputException.class)
	public void test24()
	{
		WalletService customer=new WalletServiceImpl();
		customer.showBalance("0213211111");
	}
	@Test(expected=InvalidInputException.class)
	public void test25()
	{
		WalletService customer=new WalletServiceImpl();
		customer.createAccount("sd", "3213213211",new BigDecimal(-10));
	}
	@Test(expected=InvalidInputException.class)
	public void test26()
	{
		WalletService customer=new WalletServiceImpl();
		customer.fundTransfer("9247473364", "9848619944",new BigDecimal(-100));
	}
	@Test(expected=InvalidInputException.class)
	public void test28()
	{
		WalletService customer=new WalletServiceImpl();
		customer.fundTransfer("9247473384", "1234567890",new BigDecimal(-10));
	}
	
	@Test(expected=InvalidInputException.class)
	public void test30()
	{
		WalletService customer=new WalletServiceImpl();
		customer.withdrawAmount("9848619945",new BigDecimal(40));
	}
	@Test(expected=InvalidInputException.class)
	public void test31()
	{
		WalletService customer=new WalletServiceImpl();
		customer.depositAmount("9848619922",new BigDecimal(-190));
	}
	@Test(expected=InvalidInputException.class)
	public void test32()
	{
		WalletService customer=new WalletServiceImpl();
		customer.withdrawAmount("9848619945",new BigDecimal(190));
	}
	@Test(expected=InvalidInputException.class)
	public void test33()
	{
		WalletService customer=new WalletServiceImpl();
		customer.depositAmount("0987233333",new BigDecimal(190));
	}
	@Test(expected=InvalidInputException.class)
	public void test34()
	{
		WalletService customer=new WalletServiceImpl();
		customer.viewTransactions("0123456789");
	}
	@Test(expected=InvalidInputException.class)
	public void test35()
	{
		WalletService customer=new WalletServiceImpl();
		customer.viewTransactions("984861992");
	}


	
}