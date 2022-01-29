package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Bank;

public interface BankDAO {

    Bank vindBankOpId(int bankId);

    int vindRekeningIdVanBank(Bank bank);

}
