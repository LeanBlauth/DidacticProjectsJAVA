package model.service;

import model.entities.Contract;
import model.entities.Installment;

public abstract class ContractService {
	
	public static void processContract(Contract contract, OnlinePaymentService paymentService) {
	
		for(Installment installment : contract.getInstallmentList()) {
			installment.setFinalAmount(paymentService.calculations(installment.getAmount(),installment.getMonth()));
		}
	}
}
