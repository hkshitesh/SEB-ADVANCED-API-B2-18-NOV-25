package com.example.serializationapi.service;

import com.example.serializationapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.*;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SerializationService {

    private final ObjectMapper mapper = new ObjectMapper();

    // ---------- JSON ----------
    public void serializeJson(User user, String path) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), user);
    }

    public User deserializeJson(String path) throws Exception {
        return mapper.readValue(new File(path), User.class);
    }

    // ---------- XML ----------
    public void serializeXml(User user, String path) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(User.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(user, new File(path));
    }

    public User deserializeXml(String path) throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(User.class);
        Unmarshaller um = ctx.createUnmarshaller();
        return (User) um.unmarshal(new File(path));
    }

    // ---------- BINARY ----------
    public void serializeBinary(User user, String path) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(user);
        oos.close();
    }

    public User deserializeBinary(String path) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        User user = (User) ois.readObject();
        ois.close();
        return user;
    }
}
