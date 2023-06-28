package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Task;
import com.example.demo.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    public List<Comment> findAllbyTask(Task task) {
        return commentRepository.findAllByTask(task);
    }

    public void addComment(String comment, Task task) {
        Comment newcomment = new Comment();
        newcomment.setComment(comment);
        newcomment.setTask(task);
        commentRepository.save(newcomment);
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(List<Comment> comments) {
        commentRepository.deleteAll(comments);
    }
}
