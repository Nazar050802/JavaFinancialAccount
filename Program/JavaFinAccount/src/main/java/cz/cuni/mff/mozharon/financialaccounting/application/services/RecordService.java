package cz.cuni.mff.mozharon.financialaccounting.application.services;

import cz.cuni.mff.mozharon.financialaccounting.domain.repositories.RecordRepositoryInterface;
import cz.cuni.mff.mozharon.financialaccounting.domain.entities.Record;

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
}
