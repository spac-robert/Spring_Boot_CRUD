package ro.robert.spring_serverV2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.robert.spring_serverV2.entity.User;
import ro.robert.spring_serverV2.service.ServiceUser;

import java.util.List;


@RestController
public class ServiceController {
    @Autowired
    private final ServiceUser service;

    public ServiceController(ServiceUser service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping("/users/post")
    public void add(@RequestBody User user) throws IllegalAccessException {
        service.add(user);
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) throws IllegalAccessException {
        service.delete(id);
    }

    @PutMapping("/users/put/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) throws IllegalAccessException {
        service.updateUser(id, user);
    }
}