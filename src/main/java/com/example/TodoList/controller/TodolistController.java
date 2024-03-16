package com.example.TodoList.controller;

import com.example.TodoList.model.Todo;

import com.example.TodoList.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class TodolistController {
    private  final TodoServiceImpl todoService ;

    @GetMapping("/todo")
    public String display(Model model){
        List<Todo> todoList = todoService.display();
       model.addAttribute("todo",todoList);
       return  "index";

    }
    @GetMapping("/todo/{id}")
    public String getTodoById(@PathVariable("id") int id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "index";
    }
// addnew task
    @GetMapping("/todo/new")
    public String showAddTodoForm() {
        return "formAddNew"; // This corresponds to addTodoForm.html Thymeleaf template
    }

    @PostMapping("/todo/new")
    public String addTodo(@RequestParam("task") String task,
                          @RequestParam("description") String description,
                          RedirectAttributes redirectAttributes) {
        Todo newTodo = new Todo();
        newTodo.setTask(task);
        newTodo.setDescription(description);
        // Assuming you have a method in TodoService to add a new todo
        todoService.addNewList(newTodo);

        // Add a flash attribute to display a success message
        redirectAttributes.addFlashAttribute("newTodo", newTodo);
        return "redirect:/todo";
    }
//end addnew task

    // Edit start

    @GetMapping("/todo/edit/{id}")
    public String showEditTodoForm(@PathVariable int id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "formEdit";
    }

    @PostMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable int id, @ModelAttribute Todo todo) {
        todo.setId(id);
        todoService.edit(todo);
        return "redirect:/todo";
    }

    // search

    @GetMapping("/todo/search")
    public String searchTodo(@RequestParam(value = "task", required = false) String task,
                             @RequestParam(value = "isDone", required = false) Boolean isDone,
                             Model model) {
        // Perform the search based on task and isDone parameters
        List<Todo> searchResults = todoService.searchTodos(task, isDone);

        // Add the search results to the model
        model.addAttribute("todos", searchResults);

        // Return the view to display the search results
        return "searchSIDE";
    }
    /// deleted
    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        // Call the service method to delete the todo by ID
        boolean deleted = todoService.deleteTodoById(id);

        // Check if the todo was successfully deleted
        if (deleted) {
            // Add a flash attribute to display a success message
            redirectAttributes.addFlashAttribute("successMessage", "Todo deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete todo. Todo not found!");
        }
        return "redirect:/todo";
    }
}
