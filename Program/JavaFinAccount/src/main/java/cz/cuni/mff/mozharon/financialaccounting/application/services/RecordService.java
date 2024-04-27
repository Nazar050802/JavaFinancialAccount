package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.math.BigDecimal;
import java.util.Optional;

public class RecordService {
    private final RecordRepositoryInterface recordRepository;

    public RecordService(RecordRepositoryInterface recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void addRecord(Record record) {
        recordRepository.addRecord(record);
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

    public Record createRecord(BigDecimal amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException {
        return recordRepository.createRecord(amount, description, dateAndTime, category, recordType);
    }
}
