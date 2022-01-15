package com.rybakov.kotiki;

import com.rybakov.kotiki.ENTITY.Kotik;
import com.rybakov.kotiki.repository.KotikRepository;
import com.rybakov.kotiki.services.KotikServices;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@Transactional
@SpringBootTest
class KotikiApplicationTests {

    @Autowired
    KotikRepository kotikRepository;

    @Autowired
    KotikServices kotikServices;

    private Long currentId = 0L;

    @BeforeEach
    public void init(){
        Kotik kotik = new Kotik();
        kotik.setName("Kirill");
        kotik.setCountOfVoting(0);
        kotik.setImgSource("image");

        Kotik save = kotikRepository.save(kotik);
        currentId = save.getId();

    }

    @AfterEach
    public void destroy(){
        kotikRepository.deleteById(currentId);
    }

    @Test
    void increaseVoteTest() {

        Kotik byId = kotikRepository.getById(currentId);
        int before = byId.getCountOfVoting();
        kotikServices.increaseVote(currentId);
        byId = kotikRepository.getById(currentId);
        int after = byId.getCountOfVoting();
        assertEquals(before+1, after);


    }

}
