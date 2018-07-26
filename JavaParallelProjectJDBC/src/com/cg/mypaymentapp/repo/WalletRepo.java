package com.cg.mypaymentapp.repo;

import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;

public interface WalletRepo {

public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);
	public void update(Customer customer,String transactionType);

	public List<Transactions> transactions(String mobileNo);
}
