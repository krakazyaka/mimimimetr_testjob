package com.rybakov.kotiki.ENTITY;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kotik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer countOfVoting;

}
