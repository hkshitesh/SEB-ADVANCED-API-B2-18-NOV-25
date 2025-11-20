package com.example.serializationapi.controller;

import com.example.serializationapi.model.User;
import com.example.serializationapi.service.SerializationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SerializationController {

    private final SerializationService service;

    public SerializationController(SerializationService service) {
        this.service = service;
    }

    // ---------------- JSON ----------------
    @PostMapping("/json/serialize")
    public String serializeJson(@RequestBody User user) throws Exception {
        service.serializeJson(user, "user.json");
        return "JSON serialized to user.json";
    }

    @GetMapping("/json/deserialize")
    public User deserializeJson() throws Exception {
        return service.deserializeJson("user.json");
    }

    // ---------------- XML ----------------
    @PostMapping("/xml/serialize")
    public String serializeXml(@RequestBody User user) throws Exception {
        service.serializeXml(user, "user.xml");
        return "XML serialized to user.xml";
    }

    @GetMapping("/xml/deserialize")
    public User deserializeXml() throws Exception {
        return service.deserializeXml("user.xml");
    }

    // ---------------- BINARY ----------------
    @PostMapping("/bin/serialize")
    public String serializeBinary(@RequestBody User user) throws Exception {
        service.serializeBinary(user, "user.bin");
        return "Binary serialized to user.bin";
    }

    @GetMapping("/bin/deserialize")
    public User deserializeBinary() throws Exception {
        return service.deserializeBinary("user.bin");
    }
}
