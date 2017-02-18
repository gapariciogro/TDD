package com.edu.chapter07;

import java.util.List;

/**
 * @author jshernandez
 *
 */
public interface FinancialTransactionDAO {
	/**
	 * Get the transactions to process
	 * @return
	 */
	public List<TransactionDto> retrieveUnsettledTransactions();
}
