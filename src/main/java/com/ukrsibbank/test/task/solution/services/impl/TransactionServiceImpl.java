package com.ukrsibbank.test.task.solution.services.impl;

import com.ukrsibbank.test.task.solution.models.Transaction;
import com.ukrsibbank.test.task.solution.repositories.TransactionRepository;
import com.ukrsibbank.test.task.solution.services.TransactionService;
import com.ukrsibbank.test.task.solution.utils.TransactionReader;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void persist(InputStream inputStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

        TransactionReader.readDocument(reader, transactionRepository);
    }

}
