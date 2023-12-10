package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.config.LoggerConfig;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticField {
    private static final Logger logger = LoggerConfig.getLogger(StatisticField.class);

    private BigDecimal income;
    private BigDecimal expense;
    private BigDecimal profitability;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) throws InvalidStatisticField {
        if(income.compareTo(BigDecimal.ZERO) < 0){
            logger.log(Level.WARNING, "Income cannot be negative.");
            throw new InvalidStatisticField("Income cannot be negative.");
        }

        this.income = income;
        updateProfitability();
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) throws InvalidStatisticField {
        if(expense.compareTo(BigDecimal.ZERO) < 0){
            logger.log(Level.WARNING, "Expense cannot be negative.");
            throw new InvalidStatisticField("Expense cannot be negative.");
        }

        this.expense = expense;
        updateProfitability();
    }

    public BigDecimal getProfitability() {
        return profitability;
    }

    public StatisticField(BigDecimal income, BigDecimal expense) throws InvalidStatisticField {
        this.income = BigDecimal.ZERO;
        this.expense = BigDecimal.ZERO;

        setIncome(income);
        setExpense(expense);
        updateProfitability();
    }

    public StatisticField() throws InvalidStatisticField {
        this.income = BigDecimal.ZERO;
        this.expense = BigDecimal.ZERO;

        updateProfitability();
    }

    private void updateProfitability(){
        profitability = income.subtract(expense);
    }
}
