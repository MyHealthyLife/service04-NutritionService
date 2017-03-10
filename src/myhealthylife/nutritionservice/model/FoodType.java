package myhealthylife.nutritionservice.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import myhealthylife.nutritionservice.dao.NutritionServiceDao;

/**
 * The persistent class for the "FoodType" database table.
 * 
 */
@Entity
@Table(name="FoodType")
@NamedQuery(name="FoodType.findAll", query="SELECT f FROM FoodType f")
@XmlRootElement(name="foodType")
@XmlType(propOrder={"idFoodType", "category"})
public class FoodType implements Serializable {

	@Id
	@GeneratedValue
	private Long idFoodType;
	
	@Column(name="category")
	private String category;

	public Long getIdFoodType() {
		return idFoodType;
	}

	public void setIdFoodType(Long idFoodType) {
		this.idFoodType = idFoodType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
	/* DATABASE OPERATIONS */
	public static FoodType getFoodTypeById(long foodId) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		FoodType p = em.find(FoodType.class, foodId);
		NutritionServiceDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<FoodType> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<FoodType> list = em.createNamedQuery("FoodType.findAll", FoodType.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    NutritionServiceDao.instance.closeConnections(em);
	    return list;
	}
	
	public static FoodType saveFoodType(FoodType p) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    NutritionServiceDao.instance.closeConnections(em);
	    return p;
	}
	
	public static FoodType updateFoodType(FoodType p) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    NutritionServiceDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeFoodType(FoodType p) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    NutritionServiceDao.instance.closeConnections(em);
	}
	
	
	
}
