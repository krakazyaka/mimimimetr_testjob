package com.rybakov.kotiki.services.Implement;


import com.rybakov.kotiki.ENTITY.Kotik;
import com.rybakov.kotiki.repository.KotikRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GeneratePairService {

    private final KotikRepository kotikRepository;

    private int order = 0;

    public ArrayList<Integer> getOrderPair = new ArrayList<>();


    public GeneratePairService(KotikRepository kotikRepository) {
        this.kotikRepository = kotikRepository;
    }


    public void increaseOrder(){
        this.order+=2;
    }

    public ArrayList<Kotik> getGeneratedPair(){
        ArrayList<Kotik> allKotiks =kotikRepository.findAll();
        ArrayList<Kotik> list = new ArrayList<Kotik>();
        if(order == getOrderPair.size()){
            return list;
        }else {
            list.add(allKotiks.get(getOrderPair.get(this.order)));
            list.add(allKotiks.get(getOrderPair.get(this.order + 1)));
            increaseOrder();
            return list;
        }

    }

    @PostConstruct
    public void init() {

        Integer kotikCount = (int)kotikRepository.count();

        List<Integer> kotiksIds = IntStream.range(0, kotikCount).boxed().collect(Collectors.toList());
        Collections.shuffle(kotiksIds);

        int j,c;
        for(j = 0; j< kotikCount-1; j++) {
            for (c = j + 1; c <kotikCount; c++) {
                getOrderPair.add(kotiksIds.get(j));
                getOrderPair.add(kotiksIds.get(c));
            }
        }



    }

}
