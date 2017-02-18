package com.edu.chapter07;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

/**
 * @author gaparicio
 *
 */
public class ReconciliationJobTest {
	ReconciliationJob job;
	@Mock
	FinancialTransactionDAO financialTransactionDAO; 
	@Mock
	MembershipDAO membershipDAO; 
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		job = new ReconciliationJobImpl(financialTransactionDAO, membershipDAO);
		
		MembershipStatusDto basicMembership = new MembershipStatusDto();
		basicMembership.setDeductable(new BigDecimal("0.30"));
		
		when(membershipDAO.getStatusFor(anyString())).
				thenReturn(basicMembership);
		
		
	}
	
	@Test
	public void when_no_transactions_job_return_0_records_processed() {
		
		assertEquals("No transactions expected", job.reconcile(), 0);
		
	}
	
	@Test
	public void reconcile_returns_transaction_count() {
		List<TransactionDto> singleTxList = new ArrayList<>();
		singleTxList.add(new TransactionDto());
		
		when(financialTransactionDAO.retrieveUnsettledTransactions()).
				thenReturn(singleTxList);
		assertEquals("One and just one transaction Expected", job.reconcile(), 1);
	}
	
	@Test
	public void reconcile_calls_membershipDAO_to_fetch_membership_details() {
		List<TransactionDto> txList = new ArrayList<>();
		TransactionDto tx1 = new TransactionDto();
		tx1.setDeveloperId("DEV011");
		txList.add(tx1);

		TransactionDto tx2 = new TransactionDto();
		tx1.setDeveloperId("DEV020");
		txList.add(tx2);

		TransactionDto tx3 = new TransactionDto();
		tx1.setDeveloperId("DEV001");
		txList.add(tx3);
		when(financialTransactionDAO.retrieveUnsettledTransactions()).
				thenReturn(txList);
		
		job.reconcile();
		
		ArgumentCaptor<String> argCaptor = 
				ArgumentCaptor.forClass(String.class);
				
		verify( membershipDAO, new Times(txList.size()) ).getStatusFor(argCaptor.capture());
		
		List<String> passedValues = argCaptor.getAllValues();
		
		assertEquals(tx1.getDeveloperId(), passedValues.get(0));
		assertEquals(tx2.getDeveloperId(), passedValues.get(1));
		assertEquals(tx3.getDeveloperId(), passedValues.get(2));
		
	}
}
