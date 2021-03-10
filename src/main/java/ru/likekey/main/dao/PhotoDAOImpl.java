package ru.likekey.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.likekey.main.entity.Payment;
import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;

import java.util.List;

public class PhotoDAOImpl implements PhotoDAO {

    private static SessionFactory sessionFactory;

    public PhotoDAOImpl() {
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
    public List<Photo> getUserPhotos(int vkId) {
        User user = new UserDAOImpl().getUser(vkId);
        List<Photo> photos = user.getPhotos();
        return photos;
    }

    @Override
    public List<Photo> getAllPhoto() {
        Session session = sessionFactory.getCurrentSession();
        List<Photo> photos;
        try {
            session.beginTransaction();
            String query = "from Photo";
            photos = session.createQuery(query).getResultList();
        } finally {
            session.getTransaction().commit();
        }
        return photos;
    }

    @Override
    public Photo getPhoto(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Photo> photo;
        try {
            session.beginTransaction();
            photo = session.createQuery("from Photo where id = " + id).getResultList();
        } finally {
            session.getTransaction().commit();
        }
        return photo.get(0);
    }
}
