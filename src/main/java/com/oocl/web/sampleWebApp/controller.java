package com.oocl.web.sampleWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class controller {

    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @GetMapping(path = "/", produces = {"application/json"})
    public ResponseEntity<String> getAll() {
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setId(1L);
        singleEntity.setName("Tom");
        singleEntityRepository.save(singleEntity);
        return ResponseEntity.ok("Hello: " + singleEntity.getName());
    }
}
