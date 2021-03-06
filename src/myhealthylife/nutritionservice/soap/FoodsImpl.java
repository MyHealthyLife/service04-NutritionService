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
    	
		// Checks the type of the food
    	if(foodToSave.getFoodType()!=null) {
    		
    		FoodType typeToSave = foodToSave.getFoodType();
    		typeToSave.setCategory(typeToSave.getCategory().toLowerCase());
    		
    		long checkType = this.searchForFoodType(typeToSave.getCategory());
    		
    		// Creates a new food with a brand new type
    		if(checkType==0) {
    			
    	    	// Saves the new food
    	    	Food.saveFood(foodToSave);

    	    	// Gets the food just inserted
    	    	foodToSave = Food.getFoodById(foodToSave.getIdFood());
    	    	
    			
    		}
    		else {
    			
    			foodToSave.setFoodType(null);
    			
    			// Saves the new food
    	    	Food.saveFood(foodToSave);
    	    	
    	    	this.setFoodType(foodToSave.getIdFood(), checkType);

    	    	// Gets the food just inserted
    	    	foodToSave = Food.getFoodById(foodToSave.getIdFood());
    	    	
    		}
    		
    	}
    	
    	return foodToSave;

	}
	
	
	/**
	 * Deletes a food already present in the database
	 */
	@Override
	public long deleteFood(long id) {
		
		// Gets the food from the database specified by the identifier
		Food food = Food.getFoodById(id);
        
        // If the food exists then it removes it
        if (food!=null) {
            Food.removeFood(food);
            return food.getIdFood();
        }
        
        // Otherwise it returns an error code
        else {
            return -1;
        }
		
	}
	
	
	/**
	 * Updates an existing food in the database
	 */
	@Override
	public Food updateFood(Food foodToUpdate) {
		
		// Gets the id of the food to update
    	Long foodId = foodToUpdate.getIdFood();
    	
    	// Updates the data of the food
    	Food currentFood = Food.getFoodById(foodToUpdate.getIdFood());
    	
    	if(foodToUpdate.getName()!=null) {
    		currentFood.setName(foodToUpdate.getName());
    	}
    	if(foodToUpdate.getCalories()!=null) {
    		currentFood.setCalories(foodToUpdate.getCalories());
    	}
    	
    	// Update query
        Food.updateFood(currentFood);
        return Food.getFoodById(foodId);
		
	}
	
	
	/**
	 * Creates a new type for foods
	 */
	@Override
	public FoodType createFoodType(String typeName) {
		
		// Creates a new type
		FoodType foodTypeToSave = new FoodType();
		foodTypeToSave.setCategory(typeName);

		// Saves the new type
    	foodTypeToSave = FoodType.saveFoodType(foodTypeToSave);

    	// Gets the type just inserted and returns it
    	FoodType foodTypeNew = FoodType.getFoodTypeById(foodTypeToSave.getIdFoodType());
    	
    	return foodTypeNew;
    	
	}
	
	
	/**
	 * Deletes a type already present in the database
	 */
	@Override
	public long deleteFoodType(long id) {

		// Gets the food type from the database specified by the identifier
		FoodType foodType = FoodType.getFoodTypeById(id);
        
        // If the type exists then it removes it
        if (foodType!=null) {
            FoodType.removeFoodType(foodType);
            return foodType.getIdFoodType();
        }
        
        // Otherwise it returns an error code
        else {
            return -1;
        }
		
	}
	
	

	/**
	 * Updates an existing type of food
	 */
	@Override
	public FoodType updateFoodType(FoodType foodTypeToUpdate) {
		
		// Gets the id of the type to update
    	Long foodTypeId = foodTypeToUpdate.getIdFoodType();
    	
    	// Updates the data of the type
    	FoodType currentFoodType = FoodType.getFoodTypeById(foodTypeToUpdate.getIdFoodType());
    	
    	if(foodTypeToUpdate.getCategory()!=null) {
    		currentFoodType.setCategory(foodTypeToUpdate.getCategory());
    	}
    	
    	// Update query
        FoodType.updateFoodType(currentFoodType);
        return FoodType.getFoodTypeById(foodTypeId);
	}
	
	
	/**
	 * Gets the entire set of types for the foods
	 */
	@Override
	public FoodTypeList readFoodTypeList() {
		
		// Gets all the types and adds them to the list
		FoodTypeList foodTypes = new FoodTypeList();
    	foodTypes.setFoodType(FoodType.getAll());
    	
        return foodTypes;
	}
	
	
	/**
	 * Sets an existing type to an existing food
	 */
	@Override
	public Food setFoodType(long foodId, long typeId) {
		
		// Gets the food to update from the database
		Food foodToUpdate = Food.getFoodById(foodId);
		
		// Gets the requested food type from the database
		FoodType sType = FoodType.getFoodTypeById(typeId);
		
		if(foodToUpdate!=null && sType!=null) {
			
			// Updates the food type and then returns it to the client
			foodToUpdate.setFoodType(sType);
			Food.updateFood(foodToUpdate);

			return Food.getFoodById(foodId);
		}
		
		return null;
	}


	@Override
	public FoodList findFoodByType(String foodType) {
		
		// Lists used to filter results
		List<Food> foodList = this.readFoodList().getFood();
		List<Food> foodListFiltered = new ArrayList<>();
		
		for(int i=0;i<foodList.size();i++) {
			
			// Gets the current food and its type
			Food currentFood = foodList.get(i);
			FoodType currentType = currentFood.getFoodType();
			
			if(currentType!=null && currentType.getCategory().equals(foodType)) {
				
				// Adds it to the filtered list
				foodListFiltered.add(currentFood);
				
			}
			
		}
		
		// Sets the filtered list inside the return object
		FoodList foodListObj = new FoodList();
		foodListObj.setFood(foodListFiltered);
		
		return foodListObj;
	}


	@Override
	public FoodList findFoodByTypeFiltered(String foodType, Integer maxCal) {
		
		// Lists used to filter results
		List<Food> foodList = this.readFoodList().getFood();
		List<Food> foodListFiltered = new ArrayList<>();
		
		for(int i=0;i<foodList.size();i++) {
			
			// Gets the current food and its type
			Food currentFood = foodList.get(i);
			FoodType currentType = currentFood.getFoodType();
			
			// Checks if the type of food corresponds to the one searched by the user
			if(currentType!=null && currentType.getCategory().equals(foodType)) {
				
				if(maxCal!=null) {
				
					// Checks if the calories are within the maximum range
					if(currentFood.getCalories() < maxCal) {
						
						// Adds it to the filtered list
						foodListFiltered.add(currentFood);
						
					}
				}
				else {
					foodListFiltered.add(currentFood);
				}
			}
			
		}
		
		// Sets the filtered list inside the return object
		FoodList foodListObj = new FoodList();
		foodListObj.setFood(foodListFiltered);
		
		return foodListObj;
		
	}
	
	
	
	
	@Override
	public FoodList findFoodByTypeFilteredByCalories(Integer maxCal) {
		
		// Lists used to filter results
		List<Food> foodList = this.readFoodList().getFood();
		List<Food> foodListFiltered = new ArrayList<>();
		
		for(int i=0;i<foodList.size();i++) {
			
			// Gets the current food and its type
			Food currentFood = foodList.get(i);
			System.out.println("" + maxCal);
			// Checks if the calories of food corresponds to the one searched by the user
			if(maxCal!=null) {
				
				// Checks if the calories are within the maximum range
				if(currentFood.getCalories() < maxCal) {
					
					// Adds it to the filtered list
					foodListFiltered.add(currentFood);
					
				}
			}
			
		}
		
		// Sets the filtered list inside the return object
		FoodList foodListObj = new FoodList();
		foodListObj.setFood(foodListFiltered);
		
		return foodListObj;
		
	}
	
	
	
	private long searchForFoodType(String foodTypeToSearch) {
    	
		// Gets all the food types
		List<FoodType> sTypeList = FoodType.getAll();
		
		for(int i=0;i<sTypeList.size();i++) {
			
			FoodType singleType = sTypeList.get(i);
			
			if(singleType.getCategory()!=null && singleType.getCategory().equals(foodTypeToSearch)) {
				
				return singleType.getIdFoodType();
				
			}
			
		}
		
    	
    	return 0;

	}


}