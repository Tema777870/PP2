package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String SQLCREATE = "CREATE TABLE IF NOT EXISTS users_table (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age TINYINT, PRIMARY KEY (id) );";
    private static final String SQLDELETE = "DROP TABLE IF EXISTS users_table;";

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(SQLCREATE).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Table created!");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(SQLDELETE).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Table dropped!");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        try (Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User saved!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
//            session.delete(user);
            //    session.remove(user);
            session.createQuery("delete User where id = " + id).executeUpdate();
            transaction.commit();
            System.out.println("User deleted!");

        } //catch (IllegalStateException i) {
        //   System.out.println("No such id for deleting!");
        // }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("no such user");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            transaction.commit();
            System.out.println("List of Users:");
            return users;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            System.out.println("Table cleaned!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
