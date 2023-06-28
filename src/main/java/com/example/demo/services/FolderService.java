package com.example.demo.services;

import com.example.demo.entities.Folder;
import com.example.demo.entities.TaskCategory;
import com.example.demo.repositories.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    public Folder findFolderById(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    public void addFolder(Folder folder) {
        folderRepository.save(folder);
    }

    public void updateFolder(Folder folder) {
        folderRepository.save(folder);
    }

    public List<Folder> findByCategoriesListContaining(TaskCategory taskCategory) {
        return folderRepository.findByCategoriesListContaining(taskCategory);
    }
}

