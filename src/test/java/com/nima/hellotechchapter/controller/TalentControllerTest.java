package com.nima.hellotechchapter.controller;

import com.nima.hellotechchapter.data.TalentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TalentControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired TalentStore store;

    private String talentId;
    private String docId;

    @BeforeEach
    void setUp() {
        talentId = store.getAllTalents().get(0).getId();
        docId = store.getDocumentsByTalentId(talentId).get(0).getId();
    }

    @Test
    void getAllTalents_returns200AndList() throws Exception {
        mockMvc.perform(get("/talent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Nima Salami"));
    }

    @Test
    void getTalentById_validId_returns200() throws Exception {
        mockMvc.perform(get("/talent/{id}", talentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(talentId))
                .andExpect(jsonPath("$.name").value("Nima Salami"));
    }

    @Test
    void getTalentById_invalidId_returns404() throws Exception {
        mockMvc.perform(get("/talent/{id}", "ikke-et-gyldigt-id"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void getDocuments_validTalentId_returns200AndList() throws Exception {
        int expectedCount = store.getDocumentsByTalentId(talentId).size();
        mockMvc.perform(get("/talent/{id}/documents", talentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedCount));
    }

    @Test
    void getDocuments_invalidTalentId_returns404() throws Exception {
        mockMvc.perform(get("/talent/{id}/documents", "ikke-et-gyldigt-id"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void getDocument_validIds_returns200() throws Exception {
        mockMvc.perform(get("/talent/{id}/documents/{docId}", talentId, docId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(docId));
    }

    @Test
    void getDocument_invalidDocId_returns404() throws Exception {
        mockMvc.perform(get("/talent/{id}/documents/{docId}", talentId, "ikke-et-gyldigt-doc-id"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }
}