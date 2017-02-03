package myhealthylife.nutritionservice.model;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "foods")
public class FoodList {
	
	private List<Food> food;

	public List<Food> getFood() {
		return food;
	}

	public void setFood(List<Food> food) {
		this.food = food;
	}

}
