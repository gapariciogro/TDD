package com.edu.chapter07;

import java.util.List;

public class ReconciliationJobImpl implements ReconciliationJob {
	private final FinancialTransactionDAO financialTxDAO;
	private final MembershipDAO membershipDAO;
	
	public ReconciliationJobImpl(FinancialTransactionDAO financialTxDAO,
			MembershipDAO membershipDAO) {
		this.financialTxDAO = financialTxDAO;
		this.membershipDAO = membershipDAO;
	}
	
	@Override
	public int reconcile() {
		List<TransactionDto> unsettleTxs = financialTxDAO.retrieveUnsettledTransactions();
		return unsettleTxs.size();
	}

}
