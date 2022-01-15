package com.rybakov.kotiki.controllers;

import com.rybakov.kotiki.ENTITY.Kotik;
import com.rybakov.kotiki.services.Implement.GeneratePairService;
import com.rybakov.kotiki.services.KotikServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final KotikServices kotikServices;
    private final GeneratePairService generatePairService;
    @GetMapping()
    public String voteKotik(Model model){

        System.out.println("hihih");
        List<Kotik> pairKotiks = generatePairService.getGeneratedPair();

        if(pairKotiks.isEmpty()){
            return top10(model);
        }
        Kotik kotik1 =pairKotiks.get(0);
        Kotik kotik2 = pairKotiks.get(1);

        System.out.println(kotik1);
        System.out.println(kotik2);

        model.addAttribute("k1", kotik1);
        model.addAttribute("k2", kotik2);


        return "voteKotik";
    }

    @PostMapping
    @Transactional
    public String voteForKotik(Model model, @RequestBody String catInfo) {
        String[] strings = catInfo.split("=");
        if(strings[1] != null) {
            kotikServices.increaseVote(Integer.parseInt(strings[1]));
        }

        return voteKotik(model);
    }


    @GetMapping("top10")
    public String top10(Model model) {
        List<Kotik> topKotiks = kotikServices.getFirst10();
        model.addAttribute("top10Cats", topKotiks);

        return "top10Kotiks";
    }

}
