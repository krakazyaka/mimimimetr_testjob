package com.rybakov.kotiki.services;

import com.rybakov.kotiki.ENTITY.Kotik;

import java.util.ArrayList;
import java.util.List;

public interface KotikServices {

    List<Kotik> getAllKotik();

    Kotik getById(long id);

    List<Kotik> getFirst10();

    void increaseVote(long id);


}
