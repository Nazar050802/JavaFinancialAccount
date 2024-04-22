package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.util.Optional;

public interface RecordRepositoryInterface {

    Optional<Record> findById(Long id);
    void addRecord(Record record);
    void deleteRecord(Record record);

    //List<Record> findByDateRange(DateAndTime start, DateAndTime end);

}
