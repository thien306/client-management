package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView customerList() {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Customer> customers = customerService.listCustomer();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customers", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer, RedirectAttributes attributes) {
        customer.setId((long) (Math.random() * 1000));
        customerService.save(customer);
        attributes.addFlashAttribute("message", "Successfully added new customers");
        return "redirect:/customers";
    }


    @GetMapping("/update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Customer customer = customerService.findById(id);

        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/update");
            modelAndView.addObject("customers", customer);
            return modelAndView;

        } else {
            return new ModelAndView("/error_404");
        }
    }


    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes attributes) {
        customerService.save(customer);
        attributes.addFlashAttribute("message", "Successful customer update");
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id,Customer customer, RedirectAttributes attributes) {
        customerService.delete(customer.getId());
        attributes.addFlashAttribute("message", "Delete Customer Success");
        return "redirect:/customers";
    }

    @GetMapping("/views/{id}")
    public ModelAndView viewsCustomer(@PathVariable Long id) {
        ModelAndView modelAndView =  new ModelAndView("/view");
        modelAndView.addObject("customers", customerService.findById(id));
        return modelAndView;
    }
}
