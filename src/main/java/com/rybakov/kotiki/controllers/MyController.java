package com.rybakov.kotiki.controllers;

import com.rybakov.kotiki.ENTITY.Kotik;
import com.rybakov.kotiki.config.MyConfig;
import com.rybakov.kotiki.services.Implement.GeneratePairService;
import com.rybakov.kotiki.services.KotikServices;
import lombok.RequiredArgsConstructor;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes(types = MyConfig.class)
public class MyController {

    private final KotikServices kotikServices;
    @Resource(name = "sessionScopedBean")
    private final GeneratePairService generatePairService;
    @GetMapping()
    public String voteKotik(Model model){

        List<Kotik> pairKotiks = generatePairService.getGeneratedPair();


        if(pairKotiks.isEmpty()){
            return top10(model);
        }
        Kotik kotik1 =pairKotiks.get(0);
        Kotik kotik2 = pairKotiks.get(1);

        model.addAttribute("k1", kotik1);
        model.addAttribute("k2", kotik2);

        model.addAttribute("pairKotiks", pairKotiks);

        return "voteKotik";
    }

    @PostMapping
    @Transactional
    public String voteForKotik(Model model, @RequestBody String kotikInfo) {
        String[] strings = kotikInfo.split("=");
        if(strings[1] != null) {
            kotikServices.increaseVote(Integer.parseInt(strings[1]));
        }

        return voteKotik(model);
    }


    @GetMapping("top10Kotiks")
    public String top10(Model model) {
        System.out.println("top10");
        List<Kotik> topKotiks = kotikServices.getFirst10();
        System.out.println(topKotiks);
        model.addAttribute("top10Kotiks", topKotiks);



        return "top10Kotiks";
    }

}