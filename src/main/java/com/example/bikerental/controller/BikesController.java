package com.example.bikerental.controller;

import com.example.bikerental.model.Person;
import com.example.bikerental.model.Bike;
import com.example.bikerental.repository.BikeRepository;
import com.example.bikerental.repository.PersonRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/bikes")
public class BikesController {

    private final BikeRepository bikeRepository;
    private final PersonRepository personRepository;


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bikes", bikeRepository.index());
        return "bikes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("bike", bikeRepository.show(id));

        Optional<Person> bikeOwner = bikeRepository.getBikeOwner(id);

        if (bikeOwner.isPresent())
            model.addAttribute("owner", bikeOwner.get());
        else
            model.addAttribute("people", personRepository.index());

        return "bikes/show";
    }

    @GetMapping("/new")
    public String newBike(@ModelAttribute("bike") Bike Bike) {
        return "bikes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("bike") @Valid Bike Bike,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bikes/new";

        bikeRepository.save(Bike);
        return "redirect:/bikes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("bike", bikeRepository.show(id));
        return "bikes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bike") @Valid Bike bike, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "bikes/edit";

        bikeRepository.update(id, bike);
        return "redirect:/bikes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        bikeRepository.delete(id);
        return "redirect:/bikes";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") Long id) {
        bikeRepository.release(id);
        return "redirect:/bikes/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Long id, @ModelAttribute("person") Person selectedPerson) {
        bikeRepository.assign(id, selectedPerson);
        return "redirect:/bikes/" + id;
    }
}