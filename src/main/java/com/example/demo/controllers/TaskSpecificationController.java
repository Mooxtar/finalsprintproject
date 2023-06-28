package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Folder;
import com.example.demo.entities.Task;
import com.example.demo.services.CommentService;
import com.example.demo.services.FolderService;
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
public class TaskSpecificationController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/taskdetails/{id}")
    public String taskDetails(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        List<Comment> comments = commentService.findAllbyTask(task);
        model.addAttribute("task", task);
        model.addAttribute("comments", comments);
        return "taskdetails";
    }

    @PostMapping("edittask")
    public String editTask(@RequestParam(name = "folder_id") Long folderId, Task task) {
        Folder folder = folderService.findFolderById(folderId);
        taskService.editTask(task, folder);
        return "redirect:/folderdetails/" + folder.getId();
    }

    @PostMapping("deletetask")
    public String deletTask(@RequestParam Long id,
                            @RequestParam(name = "folder_id") Long folderId) {
        Task task = taskService.findById(id);
        List<Comment> comments = commentService.findAllbyTask(task);
        commentService.deleteComment(comments);
        taskService.deleteTask(task);
        return "redirect:/folderdetails/" + folderId;
    }

    @PostMapping("addcomment")
    public String addComment(@RequestParam Long id, String comment) {
        Task task = taskService.findById(id);
        commentService.addComment(comment, task);
        return "redirect:/taskdetails/" + id;
    }
}
