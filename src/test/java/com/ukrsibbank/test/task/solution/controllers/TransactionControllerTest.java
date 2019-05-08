package com.ukrsibbank.test.task.solution.controllers;

import com.ukrsibbank.test.task.solution.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    private static final String INPUT_FILE_PATH = "src/test/resources/xml-data-sample/test.xml";
    private static final String BASE_URL = "https://localhost:8080";
    private static final String UPLOAD_URL = "/transaction/upload";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void upload() throws Exception {
        File xmlFile = new File(INPUT_FILE_PATH);
        InputStream inputStream = new FileInputStream(xmlFile);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.xml",
                "text/plain", inputStream);
        this.mvc.perform(MockMvcRequestBuilders.multipart(BASE_URL + UPLOAD_URL)
                .file(multipartFile))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }
}