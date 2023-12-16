package cz.cuni.mff.mozharon.financialaccounting.domain.repositories;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

import java.util.List;
import java.util.Optional;

public interface RecordRepository {

    List<Record> findAll();
    Optional<Record> findById(Long id);
    void save(Record record);
    void delete(Record record);

    //List<Record> findByDateRange(DateAndTime start, DateAndTime end);

}
