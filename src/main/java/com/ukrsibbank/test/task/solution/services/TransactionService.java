package com.ukrsibbank.test.task.solution.services;

import com.ukrsibbank.test.task.solution.models.Transaction;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    void persist(InputStream inputStream) throws XMLStreamException;
}
