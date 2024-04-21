package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

public class RecordService {

    private RecordRepositoryInterface recordRepositoryInterface;

    public RecordService(RecordRepositoryInterface recordRepositoryInterface) {
        this.recordRepositoryInterface = recordRepositoryInterface;
    }

    public void addRecord(Record record) throws InvalidAmountException {
        recordRepositoryInterface.save(record);
    }

    public void deleteRecord(Record record) throws InvalidAmountException {
        recordRepositoryInterface.delete(record);
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
