package com.test.carfines.controler;

import com.test.carfines.domain.FinesInformation;
import com.test.carfines.domain.FrequentFinesDTO;
import com.test.carfines.service.impl.FinesInformationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final FinesInformationServiceImpl service;

    public MainController(FinesInformationServiceImpl finesInformation) {
        this.service = finesInformation;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @GetMapping("/filter")
    public String filter(Model model){
        return "main";
    }

    @GetMapping("/report")
    public String getReport(Model model ){
        Iterable<FrequentFinesDTO> finesDTOS = service.getFrequentFines();
        model.addAttribute("frequentFines", finesDTOS  );
        return "frequentfines";
    }

    @PostMapping("/filter2")
     public String filter (@RequestParam String filterNameOwner,
                           @RequestParam String filterLPN,
                           Model model){
        Iterable<FinesInformation> finesInformations =service.getFinesInformation( filterNameOwner,filterLPN );
        model.addAttribute( "finesInformations", finesInformations );
        return "main";
    }
}
