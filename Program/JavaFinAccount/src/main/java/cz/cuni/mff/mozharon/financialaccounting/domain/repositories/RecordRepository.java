package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.util.List;

public interface RecordRepository {

    List<Record> findAll();
    Record findById(Long id);
    void save(Record record);
    void delete(Record record);

    //List<Record> findByDateRange(DateAndTime start, DateAndTime end);
    
}
