package com.test.carfines.controler;

import com.test.carfines.domain.FinesInformation;
import com.test.carfines.domain.FrequentFinesDTO;
import com.test.carfines.domain.LicensePlateNumber;
import com.test.carfines.domain.Owner;
import com.test.carfines.service.impl.FinesInformationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {

    private final FinesInformationServiceImpl service;

    public MainController(FinesInformationServiceImpl finesInformation) {
        this.service = finesInformation;
    }


    @GetMapping({"/", "/main"})
    public String filter(Model model) {
        Iterable<FinesInformation> finesInformations = service.getAll();
        model.addAttribute( "finesInformations", finesInformations );
        model = addAttributeForFoundingForm( model );
        return "main";
    }

    @GetMapping("/report")
    public String getReport(Model model) {
        Iterable<FrequentFinesDTO> finesDTOS = service.getFrequentFines();
        model.addAttribute( "frequentFines", finesDTOS );
        return "frequentfines";
    }

    private Model addAttributeForFoundingForm(Model model) {
        model.addAttribute( "owner", new Owner() );
        model.addAttribute( "licensePlateNumber", new LicensePlateNumber() );
        return model;
    }

    @PostMapping("/filter")
    public String filter(@Valid Owner owner, BindingResult bindingResultOwner,
                         @Valid LicensePlateNumber licensePlateNumber,
                         BindingResult bindingResultLPN,
                         Model model) {
        model = addAttributeForFoundingForm( model );

        if (bindingResultOwner.hasErrors() && bindingResultLPN.hasErrors()) {
            Map<String, String> mapOwnersErrors = ControllerUtils.getErrors( bindingResultOwner );
            Map<String, String> mapLPNErrors = ControllerUtils.getErrors( bindingResultLPN );

            model.addAllAttributes( mapOwnersErrors );
            model.addAllAttributes( mapLPNErrors );

            return "main";
        }

        if (bindingResultOwner.hasErrors()) {
            Map<String, String> mapOwnersErrors = ControllerUtils.getErrors( bindingResultOwner );
            model.addAllAttributes( mapOwnersErrors );
            return "main";
        }

        if (bindingResultLPN.hasErrors()) {
            Map<String, String> mapLPNErrors = ControllerUtils.getErrors( bindingResultLPN );
            model.addAllAttributes( mapLPNErrors );
            return "main";
        }


        Iterable<FinesInformation> finesInformations = service.getFinesInformation( owner.getNameOwner(), licensePlateNumber.getLicensePlateNumbersName() );
        model.addAttribute( "finesInformations", finesInformations );

        return "main";
    }
}
