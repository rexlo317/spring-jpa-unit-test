package com.oocl.web.sampleWebApp;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleEntityTest {
    @Autowired
    private SingleEntityRepository singleEntityRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void should_not_store_too_long(){
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setId(1L);
        singleEntity.setName("12345678901");
        assertThrows(Exception.class, () -> {
            singleEntityRepository.save(singleEntity);
            singleEntityRepository.flush();});
    }

    @Test
    public void should_fetch_data(){
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setId(1L);
        singleEntity.setName("Tom");
        singleEntityRepository.save(singleEntity);
        singleEntityRepository.flush();
        entityManager.clear();
        assertEquals("Tom",singleEntityRepository.getOne(1L).getName());
    }


}
