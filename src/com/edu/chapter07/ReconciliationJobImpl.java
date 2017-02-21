package com.edu.chapter07;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReconciliationJobImpl implements ReconciliationJob {
	private final FinancialTransactionDAO financialTxDAO;
	private final MembershipDAO membershipDAO;
	private final PayPalFacade payPalFacade;
	
	public ReconciliationJobImpl(FinancialTransactionDAO financialTxDAO,
			MembershipDAO membershipDAO, PayPalFacade payPalFacade) {
		this.financialTxDAO = financialTxDAO;
		this.membershipDAO = membershipDAO;
		this.payPalFacade = payPalFacade;
	}
	
	@Override
	public int reconcile() {
		List<TransactionDto> unsettleTxs = financialTxDAO.retrieveUnsettledTransactions();
		
		Map<String, BigDecimal> mPaymentByDev = new LinkedHashMap<>();
		
		for(TransactionDto txDto : unsettleTxs)  {
			MembershipStatusDto membership = membershipDAO.getStatusFor(
					txDto.getDeveloperId());
			
			BigDecimal payableAmount = 
					txDto.getAmount().subtract(txDto.getAmount().multiply(membership.getDeductable()));
			
			if (mPaymentByDev.containsKey(txDto.getTargetPayPalId())) {
				BigDecimal amount = mPaymentByDev.get(txDto.getTargetPayPalId());
				amount = amount.add(payableAmount);
				mPaymentByDev.put(txDto.getTargetPayPalId(), amount);
			} else {
				mPaymentByDev.put(txDto.getTargetPayPalId(), payableAmount);
			}
		}

		for(Map.Entry<String, BigDecimal> entry : mPaymentByDev.entrySet()) {
			payPalFacade.sendAdvice(new PaymentAdviceDto(entry.getValue(), 
					entry.getKey(), "Post payment for developer" /*+
				txDto.getDeveloperId()*/));
		}

		return unsettleTxs.size();
	}
}
