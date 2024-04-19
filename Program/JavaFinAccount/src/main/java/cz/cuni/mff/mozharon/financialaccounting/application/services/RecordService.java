package cz.cuni.mff.mozharon.financialaccounting.application.services;

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

    public void addRecord(Record record) throws InvalidAmountException {
        recordRepository.save(record);
    }

    public void deleteRecord(Record record) throws InvalidAmountException {
        recordRepository.delete(record);
    }

    private Category convertToCategoryEntity(Category category){
        return new Category(category.getName());
    }

    private RecordType convertToRecordTypeEntity(RecordType recordType){
        switch (recordType) {
            case INCOME: return RecordType.INCOME;
            case EXPENSE: return RecordType.EXPENSE;
        }

        return null;
    }

}
