package servlets_product_task.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import servlets_product_task.dto.Product;

public class ProductDao {

	public EntityManager getEntityManager()
	{
		EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("vinod");
		return entityManagerFactory.createEntityManager();
	}
	public void saveProduct(Product product) {
		EntityManager entityManager= getEntityManager();
		EntityTransaction entityTransaction= entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
	}

}
