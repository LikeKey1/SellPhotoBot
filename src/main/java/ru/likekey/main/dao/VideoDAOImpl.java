package ru.likekey.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.likekey.main.entity.Payment;
import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    private static SessionFactory sessionFactory;

    public VideoDAOImpl() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Video.class)
                    .addAnnotatedClass(Photo.class)
                    .buildSessionFactory();
        }
    }

    @Override
    public List<Video> getUserVideos(int vkId) {
        User user = new UserDAOImpl().getUser(vkId);
        List<Video> videos = user.getVideos();
        return videos;
    }

    @Override
    public void addVideoToUser() {

    }

    @Override
    public Video getVideo(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Video> videos;
        try {
            session.beginTransaction();
            videos = session.createQuery("from Video where id = " + id).getResultList();
        } finally {
            session.getTransaction().commit();
        }
        return videos.get(0);
    }
}
