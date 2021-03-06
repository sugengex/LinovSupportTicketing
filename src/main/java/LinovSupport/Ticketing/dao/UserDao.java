/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import LinovSupport.Ticketing.model.User;

/**
 * @author Yosep Teki
 *
 */
@Repository("UserDao")
public class UserDao extends CommonDao{

	@SuppressWarnings("unchecked")
	@Transactional
	public User findById(String id) {
		List<User> list = super.entityManager
				.createQuery("FROM User WHERE idUser =:id")
				.setParameter("id",id)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		}else {
			return new User();
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public User findByBk(String bk) {
		List<User> list = super.entityManager
				.createQuery("FROM User WHERE username=:username")
				.setParameter("username", bk)
				.getResultList();
		if (list.size()>0) {
			return list.get(0);
		}else {
			return new User();
		}
	}
	
	@Transactional
	public boolean isIdExist(String id) {
		if (findById(id).getIdUser().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	@Transactional
	public boolean isBkExist(String bk) {
		if (findByBk(bk).getIdUser().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	@Transactional
	public void insertUser(User user) {
		super.entityManager.merge(user);
	}
	@Transactional
	public void updateUser(User user) {
		super.entityManager.merge(user);
	}
	@Transactional
	public void deleteUser(String id) {
		User user = findById(id);
		super.entityManager.remove(user);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User findAll() {
		List<User> list = super.entityManager
				.createQuery("FROM User")
				.getResultList();
		return (User) list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> findByFilter(String username) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM username WHERE 1=1");
		
		if (!username.trim().isEmpty()) {
			hql.append(" AND username =:username");
		}
		
		Query query = (Query) super.entityManager
				.createQuery(hql.toString());
		
		if (!username.trim().isEmpty()) {
			query.setParameter("username",username);
		}
		
		List<User> list = query.getResultList();
		
		return list;
	}
	
}

