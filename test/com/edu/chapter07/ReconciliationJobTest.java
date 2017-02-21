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
	@Mock PayPalFacade payPalFacade;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		job = new ReconciliationJobImpl(financialTransactionDAO, 
				membershipDAO, payPalFacade);
		
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
		
		singleTxList.add(createTxDto("X","Y", new BigDecimal("0")));
		
		when(financialTransactionDAO.retrieveUnsettledTransactions()).
				thenReturn(singleTxList);
		

		assertEquals("One and just one transaction Expected", job.reconcile(), 1);
	}
	
	@Test
	public void reconcile_calls_membershipDAO_to_fetch_membership_details() {
		List<TransactionDto> txList = new ArrayList<>();
		TransactionDto tx1 = new TransactionDto();
		tx1.setDeveloperId("DEV011");
		tx1.setAmount(new BigDecimal("0"));
		txList.add(tx1);

		TransactionDto tx2 = new TransactionDto();
		tx2.setDeveloperId("DEV020");
		tx2.setAmount(new BigDecimal("0"));
		txList.add(tx2);

		TransactionDto tx3 = new TransactionDto();
		tx3.setDeveloperId("DEV001");
		tx3.setAmount(new BigDecimal("0"));
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
	
	@Test
	public void when_transaction_exists_then_sends_payment_to_paypal() {
		List<TransactionDto> davidsTransactionList = new ArrayList<>();
		
		String davidsDeveloperId = "dev999";
		String davidsPayPalId = "david@paypal.com";
		BigDecimal davidsSuperMarioGamePrice = new BigDecimal("100.00");
		
		davidsTransactionList.add(
				createTxDto(davidsDeveloperId, davidsPayPalId,davidsSuperMarioGamePrice));
		
		when(financialTransactionDAO.retrieveUnsettledTransactions()).
				thenReturn(davidsTransactionList);
		
		assertEquals(1, job.reconcile());
		verify(payPalFacade).sendAdvice(isA(PaymentAdviceDto.class));
		
	}
	
	
	@Test
	public void calculates_payable() {
		List<TransactionDto> ronaldosTransactions = 
				new ArrayList<>();
		String ronaldosDeveloperId = "ronaldo007";
		String ronaldosPayPalId = "Ronaldo@realmadrid.com";
		BigDecimal ronaldosSoccerFee = new BigDecimal("100.00");
		ronaldosTransactions.add(createTxDto(
				ronaldosDeveloperId, ronaldosPayPalId, ronaldosSoccerFee));
		
		when(financialTransactionDAO.retrieveUnsettledTransactions()).
		thenReturn(ronaldosTransactions);

		assertEquals(1, job.reconcile());
		
		ArgumentCaptor<PaymentAdviceDto> calculatedAdvice = 
				ArgumentCaptor.forClass(PaymentAdviceDto.class);
		verify(payPalFacade).sendAdvice(calculatedAdvice.capture());
		
		assertTrue(new BigDecimal("70.00").compareTo(calculatedAdvice.getValue().getAmount()) == 0);
	}
	
	@Test
	public void calculates_payable_with_multiple_transaction() {
		List<TransactionDto> transactionList = new ArrayList<>();
		String johnsDeveloperId = "john001";
		String johnsPayPalId = "john@gmail.com";
		BigDecimal johnsGameFee = new BigDecimal("200");
		
		transactionList.add(createTxDto(johnsDeveloperId, johnsPayPalId, johnsGameFee));
		
		String davesDeveloperId = "dave888";
		String davesPaypalId = "Iamdave009@yahoo.com";
		BigDecimal davesGameFee = new BigDecimal("150");
		
		transactionList.add(createTxDto(davesDeveloperId, davesPaypalId, davesGameFee));

		when(financialTransactionDAO.retrieveUnsettledTransactions()).
				thenReturn(transactionList);
		
		MembershipStatusDto m1 = new MembershipStatusDto();
		m1.setDeductable(new BigDecimal("0.15"));
		MembershipStatusDto m2 = new MembershipStatusDto();
		m2.setDeductable(new BigDecimal("0.10"));
		
		when(membershipDAO.getStatusFor(eq(johnsDeveloperId))).thenReturn(m1);
		when(membershipDAO.getStatusFor(eq(davesDeveloperId))).thenReturn(m2);

		assertEquals(2, job.reconcile());
		
		ArgumentCaptor<PaymentAdviceDto> calculatedAdvice = 
				ArgumentCaptor.forClass(PaymentAdviceDto.class);
		verify(payPalFacade, new Times(2)).sendAdvice(calculatedAdvice.capture());
		
		assertTrue(new BigDecimal("170.00").compareTo(
				calculatedAdvice.getAllValues().get(0).getAmount()) == 0);
		assertTrue(new BigDecimal("135.00").compareTo(
				calculatedAdvice.getAllValues().get(1).getAmount()) == 0);

		
	}


	@Test
	public void calculates_payable_with_multiple_Transaction_For_same_developer() {
		List<TransactionDto> janetsGameFees =
				new ArrayList<TransactionDto>();
		String janetsDeveloperId = "janet12567";
		String janetsPayPalId = "JanetTheJUnitGuru@gmail.com";
		BigDecimal fishPondGameFee = new BigDecimal("200");
		BigDecimal ticTacToeGameFee = new BigDecimal("100");
	
		janetsGameFees.add(createTxDto(janetsDeveloperId, janetsPayPalId, fishPondGameFee));
	
		janetsGameFees.add(createTxDto(janetsDeveloperId, janetsPayPalId, ticTacToeGameFee));
		
		when(financialTransactionDAO.retrieveUnsettledTransactions())
				.thenReturn(janetsGameFees);
		assertEquals(2, job.reconcile());
		ArgumentCaptor<PaymentAdviceDto> calculatedAdvice
				= ArgumentCaptor.forClass(PaymentAdviceDto.class);
		
		verify(payPalFacade, new Times(1)).sendAdvice(calculatedAdvice.capture());
		
		assertTrue(new BigDecimal("210.00").compareTo(calculatedAdvice.getValue().getAmount()) == 0);
	}

	
	
	
	private TransactionDto createTxDto(String devId, String payPalId, BigDecimal amount) {
		TransactionDto tx = new TransactionDto();
		tx.setDeveloperId(devId);
		tx.setTargetPayPalId(payPalId);
		tx.setAmount(amount);
		return tx;
	}
	
	
	
	
}
