package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Métodos customizados podem ser definidos aqui, se necessário
}
