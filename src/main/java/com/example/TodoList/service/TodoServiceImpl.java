package com.example.TodoList.service;

import com.example.TodoList.model.Todo;
import com.example.TodoList.repository.TodoListDataSource;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Setter
public class TodoServiceImpl implements TodoService{
    LocalDate getDate= LocalDate.now();
    TodoListDataSource dataSource= new TodoListDataSource();
    private  List<Todo> todolist ;

    public TodoServiceImpl(List<Todo> todolist) {
        this.todolist = todolist;
    }

    public Todo getTodoById(Integer id){
     List<Todo> todos=  dataSource.getList();
        for(Todo todo:todos) {
            if(todo.getId()==id){
                return todo;
            }
         }
        return null;
    }
    @Override
    public List<Todo> display() {
       return  dataSource.getList();

    }

    @Override
    public List<Todo> addNewList(Todo newTodo) {
        List<Todo>newList = new ArrayList<>();
        newList.add(newTodo);
        return newList;

    }

    @Override
    public Todo edit( Todo todo) {
        dataSource.getList().stream().filter(e->e.getId().equals(todo.getId())).forEach(
                e->{
                    e.setTask(todo.getTask());
                    e.setDescription(todo.getDescription());
                    e.setIsDone(todo.getIsDone());
                    e.setCreatedAt(todo.getCreatedAt());
                }
                );
        todolist.add(todo);
        return todo;

    }

    @Override
    public boolean deleteTodoById(int id) {
        List<Todo> todos = dataSource.getList();
        Iterator<Todo> iterator = todos.iterator();

        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Todo> searchTodos(String task, Boolean isDone) {

        if (task != null && !task.isEmpty()) {
            dataSource.getList().stream().filter(todo->todo.getTask().contains(task));
        }

        if (isDone != null) {
            dataSource.getList().stream().filter(todo -> todo.getIsDone() == isDone);
        }
        List<Todo> searchResults = dataSource.getList().stream().toList();

        return searchResults;
    }

}
