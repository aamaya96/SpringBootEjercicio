package com.agilesolutions.exersice2.service;

import com.agilesolutions.exersice2.entity.ToDo;
import com.agilesolutions.exersice2.entity.User;
import com.agilesolutions.exersice2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService<User,String>{
    @Autowired
    public UserService(UserRepository repository){
        super(repository);
    }
    public List<ToDo> getToDoListByUser(String userName) {
        return this.findById(userName)
                    .getToDo()
                    .stream()
                    .sorted((t1,t2) -> Integer.compare(t1.getPriority(),t2.getPriority()))
                    .collect(Collectors.toList());
    }

    public List<ToDo> getToDoListByUserCategory(String userName, String categoryName) {
        return this.getToDoListByUser(userName)
                    .stream()
                    .filter(t -> t.getCategory().getCategoryName().equals(categoryName))
                    .collect(Collectors.toList());
    }
}
