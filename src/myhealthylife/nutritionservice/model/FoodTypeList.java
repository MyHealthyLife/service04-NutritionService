package myhealthylife.nutritionservice.model;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "foodTypes")
public class FoodTypeList {
	
	private List<FoodType> foodType;

	public List<FoodType> getFoodType() {
		return foodType;
	}

	public void setFoodType(List<FoodType> foodType) {
		this.foodType = foodType;
	}

}
