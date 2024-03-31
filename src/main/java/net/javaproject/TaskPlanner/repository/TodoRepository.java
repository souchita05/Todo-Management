package net.javaproject.TaskPlanner.repository;

import net.javaproject.TaskPlanner.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <Todo,Long>{
}
