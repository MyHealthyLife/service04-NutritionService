package myhealthylife.nutritionservice.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import myhealthylife.nutritionservice.dao.NutritionServiceDao;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Food" database table.
 * 
 */
@Entity
@Table(name="Food")
@NamedQuery(name="Food.findAll", query="SELECT f FROM Food f")
@XmlRootElement(name="food")
@XmlType(propOrder={"idFood", "name", "calories", "foodType"})
public class Food implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idFood;
	
	@Column(name="name")
	private String name;
	
	@Column(name="calories")
	private Integer calories;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="idFoodType",referencedColumnName="idFoodType",insertable=true,updatable=true)
	private FoodType foodType;
	
	public Food() {
	}
	

	/* GETTERS AND SETTERS */
	public long getIdFood() {
		return idFood;
	}


	public void setIdFood(long idFood) {
		this.idFood = idFood;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getCalories() {
		return calories;
	}


	public void setCalories(Integer calories) {
		this.calories = calories;
	}


	public FoodType getFoodType() {
		return foodType;
	}


	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}


	
	
	/* DATABASE OPERATIONS */
	public static Food getFoodById(long foodId) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		Food p = em.find(Food.class, foodId);
		NutritionServiceDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Food> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Food> list = em.createNamedQuery("Food.findAll", Food.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    NutritionServiceDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Food saveFood(Food p) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    NutritionServiceDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Food updateFood(Food p) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    NutritionServiceDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeFood(Food p) {
		EntityManager em = NutritionServiceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    NutritionServiceDao.instance.closeConnections(em);
	}



}