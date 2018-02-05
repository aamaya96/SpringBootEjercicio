package com.agilesolutions.exersice2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "to_do")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int priority = 1;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name="category_name")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_name")
    @JsonBackReference
    private User user;

    public ToDo(){}

    public ToDo(int priority, String title, String description, Category category, User user) {
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
