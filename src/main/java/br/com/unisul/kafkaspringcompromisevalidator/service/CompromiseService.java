package br.com.unisul.kafkaspringcompromisevalidator.service;

import br.com.unisul.kafkaspringcompromisevalidator.model.Compromise;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CompromiseService {

    public void validateCompromise(Compromise compromise) throws Exception {
        this.validateRequirements(compromise);
        this.validateDueDate(compromise.getDueDate());
    }

    private void validateRequirements(Compromise compromise) throws Exception {
        String bank = compromise.getBank();
        if (bank == null || bank.isEmpty()) {
            throw new Exception("Bank is required");
        }

        if (compromise.getDueDate() == null) {
            throw new Exception("Due date is required");
        }

        BigDecimal amount = compromise.getAmount();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Amount is required");
        }

    }

    private void validateDueDate(LocalDate dueDate) throws Exception {
        LocalDate today = LocalDate.now();

        if (dueDate.compareTo(today) < 0) {
            throw new Exception("Due date cannot be lower than current date");
        }

    }
}
