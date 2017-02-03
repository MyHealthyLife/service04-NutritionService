package myhealthylife.nutritionservice.soap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.jws.WebParam;
import javax.jws.WebService;

import myhealthylife.nutritionservice.model.Food;
import myhealthylife.nutritionservice.model.FoodList;
import myhealthylife.nutritionservice.model.FoodType;
import myhealthylife.nutritionservice.model.FoodTypeList;

//Service Implementation

@WebService(endpointInterface = "myhealthylife.nutritionservice.soap.Foods",
    serviceName="FoodService")
public class FoodsImpl implements Foods {

	
	/**
	 * Gets the entire list of foods present in the database
	 */
	@Override
	public FoodList readFoodList() {
		
		// Gets all the foods from the database and returns it to the client
		FoodList foods = new FoodList();
    	foods.setFood(Food.getAll());
    	
        return foods;
	}

	
	/**
	 * Gets a specific food based on its identifier
	 */
	@Override
	public Food readFood(long id) {
		
		// Gets the specific food the client requested
        Food food = Food.getFoodById(id);
        if (food!=null) {
            System.out.println("---> Found Food by id = "+id+" => "+food.getName());
        } else {
            System.out.println("---> Didn't find any Food with  id = "+id);
        }
        return food;
		
	}
	

	
	/**
	 * Creates a new food object and saves it into the database
	 */
	@Override
	public Food createFood(Food foodToSave) {
    	
    	// Saves the new food
    	Food.saveFood(foodToSave);

    	// Gets the food just inserted
    	foodToSave = Food.getFoodById(foodToSave.getIdFood());
    	
    	return foodToSave;

	}

}