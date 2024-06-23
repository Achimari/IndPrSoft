package lv.restaurant.core.database.user;

import lv.restaurant.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmUserRepository  implements UserRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean changeUserPassword(String password, String email) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE User AS u SET u.password = :password WHERE u.email = :email");
        query.setParameter("password", password);
        query.setParameter("email", email);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean isUserRegistered(String email, String password) {
        List<User> checkedUsers = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
        return checkedUsers.size() == 1;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE email = :email and password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        List<User> checkedUsers = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return checkedUsers.size() == 1;
    }

}
