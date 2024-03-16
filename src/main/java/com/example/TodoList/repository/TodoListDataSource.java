package com.example.TodoList.repository;

import com.example.TodoList.model.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class TodoListDataSource {

    LocalDate getdate= LocalDate.now();
   public List<Todo> getList(){
        return new ArrayList<>(){{
                add(new Todo(10," home work"," Homework Spring",true,getdate));
                add(new Todo(11," home work"," Homework Spring",false,getdate));
                add(new Todo(12," home work"," Homework Spring",true,getdate));
                add(new Todo(13," home work"," Homework Spring",false,getdate));

        }};

    }
}
