package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.stock.StockRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.stock.StockResponseDTO;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.StockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    private final UrlHelper urlHelper;

    public StockController(StockService stockService, UrlHelper urlHelper) {
        this.stockService = stockService;
        this.urlHelper = urlHelper;
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> create(
            @RequestBody @Valid StockRequestDTO request) {

        StockResponseDTO created = stockService.create(request);
        return ResponseEntity
                .created(urlHelper.getCurrentUrlWithId(created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAll() {
        return ResponseEntity.ok(stockService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
