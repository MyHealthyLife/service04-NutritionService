package myhealthylife.nutritionservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import myhealthylife.nutritionservice.model.Food;

public enum NutritionServiceDao {
	instance;
	private EntityManagerFactory emf;
	
	private NutritionServiceDao() {
		if (emf!=null) {
			emf.close();
		}
		emf = Persistence.createEntityManagerFactory("service04-NutritionService");
	}
	
	public EntityManager createEntityManager() {
		try {
			return emf.createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    
	}

	public void closeConnections(EntityManager em) {
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	// Sentence related operations could also directly go into the "Sentence" Model
	// Check Methods in LifeStaus as example
	public static Food getSentenceById(Long sentenceId) {
		EntityManager em = instance.createEntityManager();
		Food p = em.find(Food.class, sentenceId);
		instance.closeConnections(em);
		return p;
	}
	
	public static List<Food> getAll() {
		EntityManager em = instance.createEntityManager();
	    List<Food> list = em.createNamedQuery("Food.findAll", Food.class).getResultList();
	    instance.closeConnections(em);
	    return list;
	}
	
	// add other database global access operations

}