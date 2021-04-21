package com.te.springmvc2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jvnet.staxex.Base64EncoderStream;
import org.springframework.stereotype.Repository;

import com.te.springmvc2.bean.EmployeeBean;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public EmployeeBean authenticate(int id, String password) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("emp");
		EntityManager manager = managerFactory.createEntityManager();

		try {
			EmployeeBean bean = manager.find(EmployeeBean.class, id);
			if (bean != null) {
				if (bean.getPassword().equals(password)) {
					System.out.println("login successfull");
					return bean;
				} else {
					System.out.println("invalid credentials");
					return null;
				}
			} else {
				System.out.println("user not found");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EmployeeBean getEmployee(int id) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("emp");
		EntityManager manager = managerFactory.createEntityManager();
		EmployeeBean bean = manager.find(EmployeeBean.class, id);
		if (bean != null) {
			return bean;
		} else {
			return null;
		}

	}

	@Override
	public boolean deleteEmp(int id) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("emp");
		EntityManager manager = managerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		EmployeeBean bean2 = manager.find(EmployeeBean.class, id);
		if (bean2 != null) {
			manager.remove(bean2);
			transaction.commit();
		} else {
			return false;
		}

		return false;
	}

	@Override
	public List<EmployeeBean> getAllEmployees() {
   EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");

		EntityManager manager = factory.createEntityManager();
		
		String query = "from EmployeeBean";
		
		javax.persistence.Query query2 = manager.createQuery(query);
		
		List<EmployeeBean> list = query2.getResultList();
		if(list!=null) {
			return list;
		}
		else {
			return null;
		}
		
	
		
		
	}

}
