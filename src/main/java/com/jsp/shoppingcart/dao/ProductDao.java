package com.jsp.shoppingcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Product;

@Repository
public class ProductDao {
	@Autowired
	EntityManagerFactory emf;
	
	public void saveProduct(Product p) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(p);
		et.commit();
	}
	public void updateProduct(Product p) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.merge(p);
		et.commit();
	}
	public void deleteProductById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Product p=em.find(Product.class, id);
		et.begin();
		em.remove(p);
		et.commit();
	}
	public Product findProductById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Product p=em.find(Product.class, id);
		if(p!=null)	return p;
		else return null;
	}
	public List<Product> fetchAllProducts(){
		EntityManager em=emf.createEntityManager();
		Query q=em.createQuery("select p from Product p");
		return q.getResultList();         //fetch the data from database in the form of list
	}
	public List<Product> findProductByBrand(String brand){
		EntityManager em=emf.createEntityManager();
		Query q=em.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
	public List<Product> findProductByCategory(String category){
		EntityManager em=emf.createEntityManager();
		Query q=em.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}
}