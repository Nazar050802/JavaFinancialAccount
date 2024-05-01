package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Category;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.DateAndTime;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.RecordType;
import cz.cuni.mff.mozharon.financialaccounting.domain.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.Optional;

public interface RecordRepositoryInterface {

    Record createRecord(int id, BigDecimal amount, String description, DateAndTime dateAndTime, Category category, RecordType recordType) throws InvalidAmountException;

    Optional<Record> findById(Long id);
    Record addRecord(Record record);
    void deleteRecord(Record record);

    Iterable<Record> findAll();

    int getLastId();

    //List<Record> findByDateRange(DateAndTime start, DateAndTime end);

}
