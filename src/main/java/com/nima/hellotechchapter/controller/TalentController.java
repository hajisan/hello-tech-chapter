package com.nima.hellotechchapter.controller;

import com.nima.hellotechchapter.data.TalentStore;
import com.nima.hellotechchapter.exception.NotFoundException;
import com.nima.hellotechchapter.model.Document;
import com.nima.hellotechchapter.model.Talent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent")
@Tag(name = "Talent", description = "Talent API")
public class TalentController {

    private final TalentStore store;

    public TalentController(TalentStore store) {
        this.store = store;
    }

    @GetMapping
    @Operation(summary = "Get a list of talents")
    @ApiResponse(responseCode = "200", description = "List of all talents")
    public ResponseEntity<List<Talent>> getAllTalents() {
        return ResponseEntity.ok(store.getAllTalents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific talent")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Talent found"),
        @ApiResponse(responseCode = "404", description = "Talent not found")
    })
    public ResponseEntity<Talent> getTalentById(
            @Parameter(description = "Talent ID") @PathVariable String id) {
        return store.getTalentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Talent not found: " + id));
    }

    @GetMapping("/{id}/documents")
    @Operation(summary = "Get documents for a specific talent")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of documents for talent"),
        @ApiResponse(responseCode = "404", description = "Talent not found")
    })
    public ResponseEntity<List<Document>> getDocuments(
            @Parameter(description = "Talent ID") @PathVariable String id) {
        return store.getTalentById(id)
                .map(talent -> ResponseEntity.ok(store.getDocumentsByTalentId(id)))
                .orElseThrow(() -> new NotFoundException("Talent not found: " + id));
    }

    @GetMapping("/{id}/documents/{documentId}")
    @Operation(summary = "Get a specific document for a talent")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Document found"),
        @ApiResponse(responseCode = "404", description = "Talent or document not found")
    })
    public ResponseEntity<Document> getDocument(
            @Parameter(description = "Talent ID") @PathVariable String id,
            @Parameter(description = "Document ID") @PathVariable String documentId) {
        return store.getTalentById(id)
                .flatMap(talent -> store.getDocumentById(id, documentId))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Document not found: " + documentId));
    }
}
