package com.rybakov.kotiki.services.Implement;


import com.rybakov.kotiki.ENTITY.Kotik;
import com.rybakov.kotiki.repository.KotikRepository;
import com.rybakov.kotiki.services.KotikServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KotikServicesImpl implements KotikServices {

    private final KotikRepository kotikRepository;



    @Override
    public List<Kotik> getFirst10() {
        List<Kotik> top10Kotiks = kotikRepository.findTop10ByOrderByCountOfVotingDesc();
        if(top10Kotiks.size() > 10) {
            return top10Kotiks.subList(0, 9);
        }
        else {
            return top10Kotiks;
        }
    }


    @Override
    public void increaseVote(long id){
        Kotik kotik = kotikRepository.getById(id);
        kotik.setCountOfVoting(kotik.getCountOfVoting()+1);
        kotikRepository.save(kotik);
    }


}
