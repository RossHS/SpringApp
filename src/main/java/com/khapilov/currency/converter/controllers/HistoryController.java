package com.khapilov.currency.converter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер страницы "история поиска"
 *
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Controller
public class HistoryController {
    @GetMapping("/usage_history")
    public String main() {

        return "usage_history";
    }
}
