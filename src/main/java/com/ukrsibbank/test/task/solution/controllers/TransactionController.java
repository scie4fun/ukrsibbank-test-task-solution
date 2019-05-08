package com.ukrsibbank.test.task.solution.controllers;

import com.ukrsibbank.test.task.solution.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam MultipartFile file) throws XMLStreamException, IOException {
        transactionService.persist(file.getInputStream());

        return ResponseEntity
                .ok()
                .body("success");
    }
}
