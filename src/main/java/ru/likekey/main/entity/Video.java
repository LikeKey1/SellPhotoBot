package ru.likekey.main.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "path")
    private String path;

    @Column(name = "path2")
    private String path2;

    @Column(name = "path3")
    private String path3;

    @Column(name = "path_ready")
    private String pathReady;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_video",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Video() {
    }

    public Video(String path, String path2, String path3, String pathReady, int price, String description, String duration) {
        this.path = path;
        this.path2 = path2;
        this.path3 = path3;
        this.pathReady = pathReady;
        this.price = price;
        this.description = description;
        this.duration = duration;
    }

    public void addUserToVideo(User user) {
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

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
