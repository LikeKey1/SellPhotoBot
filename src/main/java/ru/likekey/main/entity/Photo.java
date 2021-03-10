package ru.likekey.main.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "path")
    private String path;

    @Column(name = "path_ready")
    private String pathReady;

    @Column(name = "price")
    private int price;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_photo",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Photo() {
    }

    public Photo(String path, String pathReady, int price) {
        this.path = path;
        this.pathReady = pathReady;
        this.price = price;
    }

    public void addUserToPhoto(User user) {
        if (users == null) users = new ArrayList<>();
        users.add(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathReady() {
        return pathReady;
    }

    public void setPathReady(String pathReady) {
        this.pathReady = pathReady;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
