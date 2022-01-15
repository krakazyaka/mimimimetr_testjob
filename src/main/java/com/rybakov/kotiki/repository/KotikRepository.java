package com.rybakov.kotiki.repository;

import com.rybakov.kotiki.ENTITY.Kotik;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface KotikRepository extends JpaRepository<Kotik,Long> {

    ArrayList<Kotik> findAll();


}
