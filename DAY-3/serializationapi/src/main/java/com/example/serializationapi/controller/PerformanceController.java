package com.example.serializationapi.controller;

import com.example.serializationapi.model.User;
import com.example.serializationapi.service.SerializationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/performance")
public class PerformanceController {

    private final SerializationService service;
    private final User sampleUser = new User(1L, "John Doe", "john@example.com");

    public PerformanceController(SerializationService service) {
        this.service = service;
    }

    @GetMapping
    public String testPerformance() throws Exception {

        long start, end;

        // JSON
        start = System.nanoTime();
        service.serializeJson(sampleUser, "user.json");
        service.deserializeJson("user.json");
        end = System.nanoTime();
        long jsonTime = end - start;

        // XML
        start = System.nanoTime();
        service.serializeXml(sampleUser, "user.xml");
        service.deserializeXml("user.xml");
        end = System.nanoTime();
        long xmlTime = end - start;

        // Binary
        start = System.nanoTime();
        service.serializeBinary(sampleUser, "user.bin");
        service.deserializeBinary("user.bin");
        end = System.nanoTime();
        long binTime = end - start;

        return "JSON: " + jsonTime + " ns\n" +
                "XML: " + xmlTime + " ns\n" +
                "Binary: " + binTime + " ns\n";
    }
}
