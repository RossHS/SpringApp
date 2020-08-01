package com.khapilov.currency.converter.controllers;

import com.khapilov.currency.converter.domain.User;
import com.khapilov.currency.converter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
            Map<String, Object> model) {
        //записываем в модель по ключу "name", входящее значение name
        model.put("name", name);
        return "greeting";
    }

    //Аналогично с выше сказанным, за тем исключением, что запускается при переходне на главную страницу
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<User> messages = userRepo.findAll();
        model.put("some", messages);
        model.put("kek", "что-то");
        return "main";
    }
}
