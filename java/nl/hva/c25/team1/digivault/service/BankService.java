// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Bank;
import nl.hva.c25.team1.digivault.repository.BankDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private BankDAO bankDAO;
    private RootRepository rootRepository;

    @Autowired
    public BankService(BankDAO bankDAO, RootRepository rootRepository) {
        this.bankDAO = bankDAO;
        this.rootRepository = rootRepository;
    }

    public Bank vindBankOpId(int bankId) {
        return rootRepository.vindBankOpId(bankId);
    }

    // methode tbv integratietest
    public int telOp(int i, int j) {
        return i + j;
    }

}