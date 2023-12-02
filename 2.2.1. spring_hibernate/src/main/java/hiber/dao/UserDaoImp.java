package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

@Autowired
 private SessionFactory sessionFactory;

   @Override
   public void add(User user) {

       sessionFactory.getCurrentSession().save(user);

   }
   @Override
   public User select(String model, int series){
       TypedQuery<Car> selectCarQuery = sessionFactory.getCurrentSession()
               .createQuery("from Car where model = :model and series = :series")
               .setParameter("model", model)
               .setParameter("series", series);
       List<Car> selectCarList = selectCarQuery.getResultList();
       if (!selectCarList.isEmpty()){
           Car selectCar = selectCarList.get(0);
           List<User> ListUser = listUsers();
           User selectUser = ListUser.stream().filter(user -> user.getUserCar().equals(selectCar))
                   .findAny().orElse(null);
           return selectUser;
       }
       return null;

   }



    @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
