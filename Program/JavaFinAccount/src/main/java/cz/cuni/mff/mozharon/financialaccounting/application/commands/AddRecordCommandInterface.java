package cz.cuni.mff.mozharon.financialaccounting.application.commands;

import cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories.InMemoryRecordRepository;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;


public class AddRecordCommandInterface implements CommandInterface {
    private InMemoryRecordRepository inMemoryRecordRepository;
    private Double amount;
    private String description;
    private DateAndTime dateAndTime;
    private Category category;
    private RecordType recordType;

    public AddRecordCommandInterface(InMemoryRecordRepository inMemoryRecordRepository, Double amount, String description,
                                     DateAndTime dateAndTime, Category category, RecordType recordType) {
        this.inMemoryRecordRepository = inMemoryRecordRepository;
        this.amount = amount;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.category = category;
        this.recordType = recordType;
    }

    /**
     * Executes the command to add a new record.
     */
    public void execute() throws InvalidAmountException {
        Record record = new Record(amount, description, dateAndTime, category, recordType);
        inMemoryRecordRepository.addRecord(record);
    }
}
