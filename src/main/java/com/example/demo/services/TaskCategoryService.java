package com.example.demo.services;

import com.example.demo.entities.TaskCategory;
import com.example.demo.repositories.TaskCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCategoryService {

    private final TaskCategoryRepository taskCategoryRepository;

    public TaskCategory findById(Long id) {
        return taskCategoryRepository.findById(id).orElse(null);
    }

    public List<TaskCategory> findAll() {
        return taskCategoryRepository.findAll();
    }
}
