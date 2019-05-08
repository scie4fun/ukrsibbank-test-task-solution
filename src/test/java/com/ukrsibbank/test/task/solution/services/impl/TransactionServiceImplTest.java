package com.ukrsibbank.test.task.solution.services.impl;

import com.ukrsibbank.test.task.solution.models.Transaction;
import com.ukrsibbank.test.task.solution.services.TransactionService;
import com.ukrsibbank.test.task.solution.utils.XmlDocumentAnalizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;

    private static final String INPUT_FILE_PATH = "src/test/resources/xml-data-sample/test.xml";
    private static  final int XML_DOC_TRANSACTIONS_COUNT = XmlDocumentAnalizer.getElementCount(INPUT_FILE_PATH, "transaction");

    @Test
    public void persist() throws FileNotFoundException, XMLStreamException {
        int actualSize = transactionService.getAll().size();
        final int EXPECTED_SIZE = actualSize + XML_DOC_TRANSACTIONS_COUNT;

        File xmlFile = new File(INPUT_FILE_PATH);
        InputStream inputStream = new FileInputStream(xmlFile);
        transactionService.persist(inputStream);

        List<Transaction> transactions = transactionService.getAll();

        actualSize = transactions.size();

        assertFalse(transactions.isEmpty());
        assertEquals(EXPECTED_SIZE, actualSize);
    }

}