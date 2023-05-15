package facades;

import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import errorhandling.NotFoundException;
import security.errorhandling.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }


    public User getUserByName(String username) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null) {
                throw new NotFoundException("No user with this name exists");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public boolean usernameExists(String username) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
        } finally {
            em.close();
        }
        return user != null;
    }

    public List<String> getAllUsernames() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> tq = em.createQuery("SELECT u FROM User u", User.class);
        List<String> usernames = new ArrayList<>();
        for (User u : tq.getResultList()) {
            usernames.add(u.getUserName());
        }
        return usernames;
    }



    public User update(User user) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        User found = em.find(User.class, user.getUserName());
        if (found == null) {
            throw new NotFoundException("Entity with ID: " + user.getUserName() + " not found");
        }
        try {
            em.getTransaction().begin();
            User updated = em.merge(user);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }

    public User delete(String username){
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, username);
        try{
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return user;
    }

    public User create(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }


}
