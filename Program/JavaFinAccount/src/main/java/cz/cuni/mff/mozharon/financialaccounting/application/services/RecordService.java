package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;

import java.math.BigDecimal;
import java.util.Optional;

public class RecordService {
    private final RecordRepositoryInterface recordRepository;

    public RecordService(RecordRepositoryInterface recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Record addRecord(Record record) {
        return recordRepository.addRecord(record);
    }

    public void deleteRecord(Record record) {
        recordRepository.deleteRecord(record);
    }

    public Optional<Record> getRecord(Long id) {
        return recordRepository.findById(id);
    }

    public Iterable<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record createRecord(BigDecimal amount, String description, String dateAndTime, Category category, String recordType) throws InvalidAmountException {
        return addRecord(recordRepository.createRecord(recordRepository.getLastId() + 1, amount, description, new DateAndTime(dateAndTime), category, RecordType.valueOf(recordType.toUpperCase())));
    }
}
