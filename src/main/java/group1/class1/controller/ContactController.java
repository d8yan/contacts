package group1.class1.controller;

import group1.class1.model.Contact;
import group1.class1.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("contacts",contactRepository.findAll());
        return "contacts";
    }
    @GetMapping("/add")
    public String addContact (Model model){
        Contact contact = new Contact();
        model.addAttribute("contact",contact);
        return "addcontact";
    }

    @PostMapping("/add")
    public String saveContact (@ModelAttribute Contact contact)
    {
        contactRepository.save(contact);
        return "redirect:/contact";
    }
    @GetMapping("/update/{id}")
    public String updateContact (@PathVariable long id, Model model){
        Contact contact = contactRepository.findById(id).get();
        model.addAttribute("contact",contact);
        return "updatecontact";
    }
    @PostMapping("/update/{id}")
    public String editContact (@PathVariable long id, @ModelAttribute Contact contact)
    {
        Contact found = contactRepository.findById(id).get();
        found.setFirstname(contact.getFirstname());
        found.setLastname(contact.getLastname());
        found.setEmail(contact.getEmail());
        found.setPhoto(contact.getPhoto());
        contactRepository.save(found);
        return "redirect:/contact";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id)
    {
        contactRepository.deleteById(id);
        return "redirect:/contact";
    }
}
