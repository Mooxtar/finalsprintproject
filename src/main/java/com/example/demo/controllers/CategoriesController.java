package com.example.demo.controllers;

import com.example.demo.entities.Folder;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskCategory;
import com.example.demo.services.FolderService;
import com.example.demo.services.TaskCategoryService;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoriesController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskCategoryService taskCategoryService;
    @Autowired
    private FolderService folderService;

    @GetMapping("/categories")
    public String allCategories(Model model) {
        List<Task> tasks = taskService.findAll();
        List<TaskCategory> taskCategories = taskCategoryService.findAll();
        model.addAttribute("taskCategories", taskCategories);
        model.addAttribute("tasks", tasks);
        return "categories";
    }

    @PostMapping("/sortcategory")
    public String sortByCategory(@RequestParam(name = "category_id") Long categoryId, Model model) {
        TaskCategory taskCategory = taskCategoryService.findById(categoryId);
        List<Folder> folders = folderService.findByCategoriesListContaining(taskCategory);
        List<Task> tasks = new ArrayList<>();
        for (Folder folder : folders) {
            tasks.addAll(taskService.findByfolder(folder));
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("categoryname", taskCategory.getName());
        return "sortedcategories";
    }
}
