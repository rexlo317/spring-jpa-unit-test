package com.oocl.web.sampleWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import java.net.URI;

@RestController
@RequestMapping("/")
public class controller {

    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @GetMapping(path = "/name", produces = {"application/json"})
    public MessageResponse getAll() {
        return new MessageResponse(singleEntityRepository.getOne(1L).getName());
    }

    @PostMapping(path = "/name", produces = {"application/json"})
    public ResponseEntity<String> post() {
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setId(1L);
        singleEntity.setName("Tom");
        singleEntityRepository.save(singleEntity);
        return ResponseEntity.created(URI.create("/name")).body("");
    }
}
