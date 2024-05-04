package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.logging.LoggerConfig;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticField {
    private static final Logger logger = LoggerConfig.getLogger(StatisticField.class);

    private Double income;
    private Double expense;
    private Double profitability;

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) throws InvalidStatisticField {
        if(income < 0){
            logger.log(Level.WARNING, "Income cannot be negative.");
            throw new InvalidStatisticField("Income cannot be negative.");
        }

        this.income = income;
        updateProfitability();
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) throws InvalidStatisticField {
        if(expense < 0){
            logger.log(Level.WARNING, "Expense cannot be negative.");
            throw new InvalidStatisticField("Expense cannot be negative.");
        }

        this.expense = expense;
        updateProfitability();
    }

    public Double getProfitability() {
        return profitability;
    }

    public StatisticField(Double income, Double expense) throws InvalidStatisticField {
        this.income = (double) 0;
        this.expense = (double) 0;

        setIncome(income);
        setExpense(expense);
        updateProfitability();
    }

    public StatisticField() throws InvalidStatisticField {
        this.income = (double) 0;
        this.expense = (double) 0;

        updateProfitability();
    }

    private void updateProfitability(){
        profitability = income - expense;
    }
}
