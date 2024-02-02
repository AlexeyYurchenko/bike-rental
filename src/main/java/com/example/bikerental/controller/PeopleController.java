package com.example.bikerental.controller;

import com.example.bikerental.repository.PersonRepository;
import com.example.bikerental.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.bikerental.model.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {

    private final PersonRepository personRepository;
    private final PersonValidator personValidator;



    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personRepository.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personRepository.show(id));
        model.addAttribute("bikes", personRepository.getBikeByPersonId(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", personRepository.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personRepository.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        personRepository.delete(id);
        return "redirect:/people";
    }
}
