package com.example.ebankservice.services;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.repo.BankAccountRepository;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @McpTool(description = "Get All Bank Accounts")
    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @McpTool(description = "Get A Bank Account By Id")
    public BankAccount getBankAccountById(@McpArg(description = "The bank account id") String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
    }

    @McpTool(description = "Add new Bank Account")
    public BankAccount saveAccount(@McpArg(description = "New Bank Account (id, createdAt, balance, type)") BankAccount bankAccount) {
        bankAccount.setCreatedAt(new Date());
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }
}
