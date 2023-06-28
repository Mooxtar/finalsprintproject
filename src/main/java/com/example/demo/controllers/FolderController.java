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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FolderController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskCategoryService taskCategoryService;
    @Autowired
    private FolderService folderService;

    @GetMapping("/folderdetails/{id}")
    public String folderDetails(@PathVariable Long id, Model model) {
        Folder folder = folderService.findFolderById(id);
        List<Task> tasks = taskService.findByfolder(folder);
        List<TaskCategory> taskCategories = taskCategoryService.findAll();
        model.addAttribute("folder", folder);
        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", taskCategories);
        return "folderdetails";
    }

    @PostMapping("/deletecategory")
    public String deleteCategory(@RequestParam(name = "category_id") Long categoryId,
                                 @RequestParam(name = "folder_id") Long folderId) {
        Folder folder = folderService.findFolderById(folderId);
        TaskCategory taskCategory = taskCategoryService.findById(categoryId);
        List<TaskCategory> taskCategories = folder.getCategoriesList();
        taskCategories.remove(taskCategory);
        folderService.updateFolder(folder);
        return "redirect:/folderdetails/" + folderId;
    }

    @PostMapping("/addtaskcategory")
    public String addTaskCategory(@RequestParam(name = "category_id") Long categoryId,
                                  @RequestParam(name = "folder_id") Long folderId) {
        Folder folder = folderService.findFolderById(folderId);
        TaskCategory taskCategory = taskCategoryService.findById(categoryId);
        List<TaskCategory> taskCategories = folder.getCategoriesList();
        if (!taskCategories.contains(taskCategory)) {
            taskCategories.add(taskCategory);
        }
        folderService.updateFolder(folder);
        return "redirect:/folderdetails/" + folderId;
    }

    @PostMapping("/addtask")
    public String addTask(@RequestParam(name = "folder_id") Long folderId, Task task) {
        Folder folder = folderService.findFolderById(folderId);
        taskService.addNewTask(task, folder);
        folderService.updateFolder(folder);
        return "redirect:/folderdetails/" + folderId;
    }
}
