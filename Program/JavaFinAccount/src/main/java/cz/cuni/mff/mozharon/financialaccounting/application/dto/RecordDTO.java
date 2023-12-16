package cz.cuni.mff.mozharon.financialaccounting.application.dto;

import java.math.BigDecimal;

public class RecordDTO {

    private long id;
    private BigDecimal amount;
    private String description;
    private DateAndTimeDTO dateAndTime;
    private CategoryDTO category;
    private RecordTypeDTO recordType;

    public RecordDTO(BigDecimal amount, String description, DateAndTimeDTO dateAndTime,
                     CategoryDTO category, RecordTypeDTO recordType) {
        this.amount = amount;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.category = category;
        this.recordType = recordType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public DateAndTimeDTO getDateAndTime() {
        return dateAndTime;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public RecordTypeDTO getRecordType() {
        return recordType;
    }
}
