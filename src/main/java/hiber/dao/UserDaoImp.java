package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void add(Car car) {
		sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public List<User> getUsersByCar(String model, int series) {
		return sessionFactory.getCurrentSession()
				.createQuery("from User as user inner join user.car as car where car.model = " + "'"
						+ model + "'"
						+ " and car.series = " + series)
				.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
		return query.getResultList();
	}

}
