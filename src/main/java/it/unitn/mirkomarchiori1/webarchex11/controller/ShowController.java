package it.unitn.mirkomarchiori1.webarchex11.controller;

import it.unitn.mirkomarchiori1.webarchex11.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping(value = "/show")
    public String showPage() {
        return "show";
    }

    @GetMapping("/showData")
    public String showData(Model model) {
        String resultAsHtml = showService.showData();
        model.addAttribute("resultAsHtml", resultAsHtml);
        return "showResult";
    }

}
