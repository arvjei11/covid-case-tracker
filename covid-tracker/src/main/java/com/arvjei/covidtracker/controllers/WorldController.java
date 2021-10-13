package com.arvjei.covidtracker.controllers;

import com.arvjei.covidtracker.models.LocationStatistics;
import com.arvjei.covidtracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.jsf.DecoratingNavigationHandler;

import java.util.List;

@Controller
public class WorldController {

    @Autowired
    CoronavirusDataService coronavirusDataService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStatistics> allStatistics = coronavirusDataService.getAllStatistics();
        int totalCasesReported = allStatistics.stream().mapToInt(LocationStatistics::getTotalCases).sum();
        model.addAttribute("locationStatistics",allStatistics);
        model.addAttribute("totalCasesReported",totalCasesReported);
        return "home";
    }

}
