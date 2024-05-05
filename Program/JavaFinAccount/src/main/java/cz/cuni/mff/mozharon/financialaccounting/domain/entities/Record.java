package cz.cuni.mff.mozharon.financialaccounting.domain.entities;

import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;

/**
 * Represents a financial record in the accounting system.
 */
public class Record {
    private static long idCounter = 0;
    private Double amount;
    private long id;
    private String description;
    private DateAndTime dateAndTime;
    private Category category;
    private RecordType recordType;

    /**
     * Constructs a new Record with provided details.
     *
     * @param amount      the financial amount of the record (must be positive).
     * @param description the description of the record.
     * @param dateAndTime the date and time of the record occurrence.
     * @param category    the category this record belongs to.
     * @param recordType  the type of the record (income or expense).
     * @throws InvalidAmountException if the amount is non-positive.
     */
    public Record(Double amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException {
        setAmount(amount);
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.category = category;
        this.recordType = recordType;
        this.id = getNextId();
    }

    /**
     * Constructs a Record with the specified details, including an externally provided ID.
     * This constructor is typically used when recreating a Record from stored data where the ID is already defined.
     *
     * @param id           the unique identifier for the record.
     * @param amount       the financial amount of the record (must be positive).
     * @param description  the description of the record.
     * @param dateAndTime  the date and time of the record occurrence.
     * @param category     the category this record belongs to.
     * @param recordType   the type of the record (income or expense).
     * @throws InvalidAmountException if the amount is non-positive, ensuring the integrity of the financial data.
     */
    public Record(long id, Double amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException {
        setAmount(amount);
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.category = category;
        this.recordType = recordType;
        this.id = id;
    }

    /**
     * Gets the financial amount of the record.
     *
     * @return the amount as a Double.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the financial amount of the record.
     *
     * @param amount the financial amount (must be positive).
     * @throws InvalidAmountException if the amount is non-positive.
     */
    private void setAmount(Double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("The amount has invalid value. It must be greater than 0.");
        }
        this.amount = amount;

    }

    /**
     * Gets the unique ID of the record.
     *
     * @return the record's ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the type of the record (income or expense).
     *
     * @return the record type.
     */
    public RecordType getRecordType() {
        return recordType;
    }

    /**
     * Gets the description of the record.
     *
     * @return the description as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the date and time of the record.
     *
     * @return the record's date and time.
     */
    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Gets the category of the record.
     *
     * @return the category as a Category object.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Generates and retrieves the next ID for a new record.
     *
     * @return the next available ID.
     */
    private static synchronized long getNextId() {
        return ++idCounter;
    }


}
