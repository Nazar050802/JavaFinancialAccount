package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.application.dto.CategoryDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.dto.RecordDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.dto.DateAndTimeDTO;
import cz.cuni.mff.mozharon.financialaccounting.application.dto.RecordTypeDTO;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepository;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.util.Objects;

public class RecordService {

    private RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void addRecord(RecordDTO recordDTO) throws InvalidAmountException {
        Record record = convertToRecordEntity(recordDTO);

        recordRepository.save(record);
    }

    private Record convertToRecordEntity(RecordDTO recordDTO) throws InvalidAmountException {

        return new Record(
                recordDTO.getAmount(),
                recordDTO.getDescription(),
                convertToDateAndTimeEntity(recordDTO.getDateAndTime()),
                convertToCategoryEntity(recordDTO.getCategory()),
                convertToRecordTypeEntity(recordDTO.getRecordType())
        );


    }

    private DateAndTime convertToDateAndTimeEntity(DateAndTimeDTO dateAndTimeDTO) {
        if(!Objects.equals(dateAndTimeDTO.getTimeUnitsString(), "")){
            return new DateAndTime(dateAndTimeDTO.getTimeUnitsString());
        }

        return new DateAndTime(dateAndTimeDTO.getDay(), dateAndTimeDTO.getMonth(), dateAndTimeDTO.getYear(),
                dateAndTimeDTO.getSeconds(), dateAndTimeDTO.getMinutes(), dateAndTimeDTO.getHours());

    }

    private Category convertToCategoryEntity(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getName());
    }

    private RecordType convertToRecordTypeEntity(RecordTypeDTO recordTypeDTO){
        switch (recordTypeDTO) {
            case INCOME: return RecordType.INCOME;
            case EXPENSE: return RecordType.EXPENSE;
        }

        return null;
    }

}
