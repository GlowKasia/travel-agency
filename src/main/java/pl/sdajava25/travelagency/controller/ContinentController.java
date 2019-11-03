package pl.sdajava25.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.Continent;
import pl.sdajava25.travelagency.service.ContinentService;

@Controller
public class ContinentController {

    private ContinentService continentService;

    @Autowired
    public ContinentController (ContinentService continentService){
        this.continentService = continentService;
    }

    @GetMapping("/admin/add-continent")
    public String addContinent(Model model){
        model.addAttribute("newContinent", new Continent());
        return "continent/add-continent";
    }

    @PostMapping("/adim/add-continent")
    public String addContinentPOst(@ModelAttribute("newContinent") Continent continent){
        continentService.addNewContinent(continent);
        return "redirect:/continent/list";
    }

    @GetMapping("/continent/list")
    public String getAllContinentsSortedByName(Model model){
        model.addAttribute("continentList", continentService.getAllContinentsSortedByName());
        return "continent/list";
    }
}
