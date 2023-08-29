package com.example.reactrockpickersauth0be.rocks;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RockController {
    private RockService rockService;

    public RockController(RockService rockService) {
        this.rockService = rockService;
    }

    @GetMapping("/getAllRocks")
    public ResponseEntity<List<Rock>> getAllRocks() {
        return ResponseEntity.status(HttpStatus.OK).body(rockService.getAllRocks());
    }
}
