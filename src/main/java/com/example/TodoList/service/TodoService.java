package com.example.TodoList.service;

import com.example.TodoList.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> display ();
    List<Todo> addNewList(Todo newTodo);
    Todo edit(Todo todo);

    boolean deleteTodoById(int id);

    List<Todo> searchTodos(String task, Boolean isDone);
}
