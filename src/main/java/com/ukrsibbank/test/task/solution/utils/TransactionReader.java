package com.ukrsibbank.test.task.solution.utils;

import com.ukrsibbank.test.task.solution.models.Client;
import com.ukrsibbank.test.task.solution.models.Transaction;
import com.ukrsibbank.test.task.solution.repositories.TransactionRepository;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class TransactionReader {

    public static void readDocument(XMLStreamReader reader, TransactionRepository transactionRepository) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("transactions"))
                        readTransactions(reader, transactionRepository);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
    }

    private static void readTransactions(XMLStreamReader reader, TransactionRepository transactionRepository) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("transaction"))
                        transactionRepository.save(readTransaction(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
    }

    private static Transaction readTransaction(XMLStreamReader reader) throws XMLStreamException {
        Transaction transaction = new Transaction();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("place"))
                        transaction.setPlace(readCharacters(reader));
                    else if (elementName.equals("amount"))
                        transaction.setAmount(Double.parseDouble(readCharacters(reader)));
                    else if (elementName.equals("currency"))
                        transaction.setCurrency(readCharacters(reader));
                    else if (elementName.equals("card"))
                        transaction.setCard(readCharacters(reader));
                    else if (elementName.equals("client"))
                        transaction.setClient(readClient(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return transaction;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }

    private static Client readClient(XMLStreamReader reader) throws XMLStreamException {
        Client client = new Client();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    switch (elementName) {
                        case "firstName":
                            client.setFirstName(readCharacters(reader));
                            break;
                        case "lastName":
                            client.setLastName(readCharacters(reader));
                            break;
                        case "middleName":
                            client.setMiddleName(readCharacters(reader));
                            break;
                        case "inn":
                            client.setInn(readCharacters(reader));
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return client;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }

    private static String readCharacters(XMLStreamReader reader) throws XMLStreamException {
        StringBuilder result = new StringBuilder();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.CHARACTERS:
                case XMLStreamReader.CDATA:
                    result.append(reader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return result.toString();
            }
        }
        throw new XMLStreamException("Premature end of file");
    }
}
