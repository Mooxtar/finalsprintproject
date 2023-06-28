package com.example.demo.controllers;

import com.example.demo.entities.Folder;
import com.example.demo.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private FolderService folderService;

    @GetMapping("/")
    public String homePage(Model model){
        List<Folder> folders = folderService.findAll();
        model.addAttribute("folders",folders);
        return "home";
    }
    @PostMapping("/addFolder")
    public String addFolderPage(Folder folder){
        folderService.addFolder(folder);
        return "redirect:/";
    }
}
