package ru.likekey.main.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vkId")
    private int vkId;

    @Column(name = "balance")
    private int balance;

    @Column(name = "place")
    private String place;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payments_id")
    private Payment userPayment;

    @ManyToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "user_photo",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    private List<Photo> photos;

    @ManyToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "user_video",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> videos;

    public User() {
    }

    public User(int vkId, int balance) {
        this.vkId = vkId;
        this.balance = balance;
        this.place = "MAIN";
    }

    public void addPhotoToUser(Photo photo) {
        if (photos == null) photos = new ArrayList<>();
        photos.add(photo);
    }

    public void addVideoToUser(Video video) {
        if (videos == null) videos = new ArrayList<>();
        videos.add(video);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVkId() {
        return vkId;
    }

    public void setVkId(int vkId) {
        this.vkId = vkId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Payment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(Payment userPayment) {
        this.userPayment = userPayment;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
