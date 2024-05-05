package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidStatisticField;


/**
 * Represents a field in a financial statistic, including income, expense, and profitability calculations.
 */
public class StatisticField {

    private Double income;
    private Double expense;
    private Double profitability;

    /**
     * Constructs a new StatisticField with specified income and expense.
     *
     * @param income  the initial income value.
     * @param expense the initial expense value.
     * @throws InvalidStatisticField if the income or expense are negative.
     */
    public StatisticField(Double income, Double expense) throws InvalidStatisticField {
        this.income = (double) 0;
        this.expense = (double) 0;

        setIncome(income);
        setExpense(expense);
        updateProfitability();
    }

    /**
     * Constructs a new StatisticField with initialized values.
     *
     * @throws InvalidStatisticField if the initial values are invalid.
     */
    public StatisticField() throws InvalidStatisticField {
        this.income = (double) 0;
        this.expense = (double) 0;

        updateProfitability();
    }


    /**
     * Gets the current income value.
     *
     * @return the income value.
     */
    public Double getIncome() {
        return income;
    }

    /**
     * Sets the income value.
     *
     * @param income the new income value, must not be negative.
     * @throws InvalidStatisticField if the income is negative.
     */
    public void setIncome(Double income) throws InvalidStatisticField {
        if(income < 0){
            throw new InvalidStatisticField("Income cannot be negative.");
        }

        this.income = income;
        updateProfitability();
    }

    /**
     * Gets the current expense value.
     *
     * @return the expense value.
     */
    public Double getExpense() {
        return expense;
    }

    /**
     * Sets the expense value.
     *
     * @param expense the new expense value, must not be negative.
     * @throws InvalidStatisticField if the expense is negative.
     */
    public void setExpense(Double expense) throws InvalidStatisticField {
        if(expense < 0){
            throw new InvalidStatisticField("Expense cannot be negative.");
        }

        this.expense = expense;
        updateProfitability();
    }

    /**
     * Gets the profitability calculated as income minus expense.
     *
     * @return the profitability value.
     */
    public Double getProfitability() {
        return profitability;
    }

    /**
     * Updates the profitability based on the current income and expense.
     */
    private void updateProfitability(){
        profitability = income - expense;
    }
}
