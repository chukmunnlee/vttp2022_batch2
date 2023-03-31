package com.github.chukmunnlee.dovbear.controllers;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.chukmunnlee.dovbear.CliOption;
import com.github.chukmunnlee.dovbear.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.github.chukmunnlee.dovbear.Constants.*;

@Controller
@RequestMapping(path = { "/", "/index.html" })
public class DovBearController {

    @Autowired private CliOption cliOpt;

    @GetMapping(produces = { MediaType.TEXT_HTML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public String getIndex(final Model model,
        @RequestParam(name = "num", defaultValue = "4") Integer num) {

        model.addAttribute("instanceName", cliOpt.getName());
        model.addAttribute("instanceHash", cliOpt.getHash());
        model.addAttribute("dovs"
            , randomList(num).stream().map(v -> "~/images/dov%d.gif".formatted(v)).toList()
        );
        return "index.html";
    }

    private List<Integer> randomList(int size) {
        List<Integer> nums = new LinkedList<>(IntStream.range(0, MAX_IMAGES).boxed().toList());
        Collections.shuffle(nums);
        return nums.subList(0, size);
    }
    
}
