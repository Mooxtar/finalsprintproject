package com.example.demo.services;

import com.example.demo.entities.Folder;
import com.example.demo.entities.Task;
import com.example.demo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByfolder(Folder folder) {
        return taskRepository.findByFolder(folder);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void addNewTask(Task task, Folder folder) {
        task.setStatus(0);
        task.setFolder(folder);
        taskRepository.save(task);
    }

    public void editTask(Task task, Folder folder) {
        task.setFolder(folder);
        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
