package cz.cuni.mff.mozharon.financialaccounting.infrastructure.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;
import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRecordRepository implements RecordRepositoryInterface {

    private final ConcurrentHashMap<Long, Record> records;

    public InMemoryRecordRepository() {
        this.records = new ConcurrentHashMap<>();
    }

    public InMemoryRecordRepository(ConcurrentHashMap<Long, Record> records) {
        this.records = records;
    }

    @Override
    public void addRecord(Record record) {
        records.put(record.getId(), record);
    }

    @Override
    public void deleteRecord(Record record) {
        records.remove(record.getId());
    }

    @Override
    public Iterable<Record> findAll() {
        return records.values();
    }

    @Override
    public Record createRecord(BigDecimal amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException {
        return new Record(amount, description, dateAndTime, category, recordType);
    }

    @Override
    public Optional<Record> findById(Long id) {
        return Optional.ofNullable(records.get(id));
    }

}
