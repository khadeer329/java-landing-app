package com.example.landing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About");
        model.addAttribute("pageHeading", "Built for modern teams");
        model.addAttribute("pageText", "This sample Java application demonstrates a simple GitOps delivery workflow with Kubernetes and Argo CD.");
        return "info";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("pageTitle", "Services");
        model.addAttribute("pageHeading", "What we deliver");
        model.addAttribute("pageText", "Cloud-native applications, reliable deployments, automated delivery pipelines, and observable services.");
        return "info";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("pageTitle", "Contact");
        model.addAttribute("pageHeading", "Let's build something");
        model.addAttribute("pageText", "This is a demo contact page. Replace this text with your business email, phone number, or contact form.");
        return "info";
    }
}
