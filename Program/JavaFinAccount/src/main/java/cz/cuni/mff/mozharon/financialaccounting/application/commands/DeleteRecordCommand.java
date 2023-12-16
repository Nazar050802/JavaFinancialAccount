package cz.cuni.mff.mozharon.financialaccounting.application.commands;

import cz.cuni.mff.mozharon.financialaccounting.application.dto.CategoryDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.dto.DateAndTimeDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.dto.RecordDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.dto.RecordTypeDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.services.RecordService;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public class DeleteRecordCommand {

    private RecordService recordService;

    private BigDecimal amount;
    private String description;
    private DateAndTimeDTO dateAndTime;
    private CategoryDTO category;
    private RecordTypeDTO recordType;

    public DeleteRecordCommand(RecordService recordService, BigDecimal amount, String description,
                            DateAndTimeDTO dateAndTime, CategoryDTO category, RecordTypeDTO recordType) {
        this.recordService = recordService;
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
        RecordDTO recordDTO = new RecordDTO(amount, description, dateAndTime, category, recordType);
        recordService.addRecord(recordDTO);
    }

}
