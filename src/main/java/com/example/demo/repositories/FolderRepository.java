package com.example.demo.repositories;

import com.example.demo.entities.Folder;
import com.example.demo.entities.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findByCategoriesListContaining(TaskCategory category);
}
