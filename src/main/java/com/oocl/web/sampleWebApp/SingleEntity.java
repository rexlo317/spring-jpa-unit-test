package com.oocl.web.sampleWebApp;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SingleEntity {
    @Id
    private Long id;
    @Column(length = 10)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface SingleEntityRepository extends JpaRepository<SingleEntity, Long>{};