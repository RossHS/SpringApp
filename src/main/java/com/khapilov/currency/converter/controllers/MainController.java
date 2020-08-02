package com.khapilov.currency.converter.controllers;

import com.khapilov.currency.converter.domain.Currency;
import com.khapilov.currency.converter.domain.Rate;
import com.khapilov.currency.converter.repos.CurrencyRepo;
import com.khapilov.currency.converter.repos.RateRepo;
import com.khapilov.currency.converter.repos.UserRepo;
import com.khapilov.currency.converter.service.CurrencyImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * класс контроллер- способ взаимодействия пользователя с моделью.
 *
 * @author Ross Khapilov
 * @version 1.0 01.08.2020
 */
@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CurrencyImpService currencyImpService;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private RateRepo rateRepo;

    /**
     * Данный метод запускается при переходе на страницу с адресом /greeting.
     * т.е. обрабатываем GET запрос для /greeting
     *
     * @param name  входной параметр.
     * @param model модель, с которой будем взаимодействовать.
     * @return отобращаемый html файл.
     */
    @GetMapping("/")
    public String greeting(
            //параметр name не обязательный, по дефолту принимает значение World
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        //записываем в модель по ключу "name", входящее значение name
        model.addAttribute("name", name);
        return "greeting";
    }

    //Аналогично с выше сказанным, за тем исключением, что запускается при переходне на главную страницу
    @GetMapping("/main")
    public String main(
            @RequestParam(required = false) Currency convertFrom,
            @RequestParam(required = false) Currency convertTo,
            @RequestParam(required = false, defaultValue = "") String value,
            @RequestParam(required = false, defaultValue = "") String result,
            Model model) {

        currencyImpService.importCurrency();
        List<Currency> currencies = currencyRepo.findAll(Sort.by("name"));


        model.addAttribute("currencies", currencies);

        if (value != null && !value.isEmpty()) {
            try {
                double valueFrom = Double.parseDouble(value);
                model.addAttribute("value", value);

                double valueResult = convert(convertFrom, convertTo, valueFrom);
                model.addAttribute("result", valueResult);

                model.addAttribute("message", "Перевели " + value + " "
                        + convertFrom.getName() + " в "
                        + valueResult + ": "
                        + convertTo.getName());
            } catch (NumberFormatException e) {
                model.addAttribute("message", "Введен неправильный формат цифр " + value);
                e.printStackTrace();
            }
        }

        return "main";
    }

    private double convert(Currency from, Currency to, double value) {
        if (from.equals(to)) return value;
        Rate fromRate = rateRepo.findFirstByCurrencyOrderByDateDesc(from);
        Rate toRate = rateRepo.findFirstByCurrencyOrderByDateDesc(to);
        return value * fromRate.getValue() * to.getNominal() / from.getNominal() / toRate.getValue();
    }
}
