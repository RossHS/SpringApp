package com.khapilov.currency.converter.controllers;

import com.khapilov.currency.converter.domain.History;
import com.khapilov.currency.converter.repos.HistoryConverterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер страницы "история поиска"
 *
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Controller
public class HistoryController {
    @Autowired
    private HistoryConverterRepo historyRepo;

    @GetMapping("/usage_history")
    public String main(Model model) {
        Iterable<History> histories = historyRepo.findAll();

        model.addAttribute("histories", histories);

        return "usage_history";
    }
}
