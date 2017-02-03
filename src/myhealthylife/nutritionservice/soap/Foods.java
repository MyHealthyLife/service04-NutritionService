package myhealthylife.nutritionservice.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import myhealthylife.nutritionservice.model.Food;
import myhealthylife.nutritionservice.model.FoodList;
import myhealthylife.nutritionservice.model.FoodType;
import myhealthylife.nutritionservice.model.FoodTypeList;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Foods {

	
	/**
	 * Gets the entire list of foods present in the database
	 * @return The FoodList object, containing all the foods in the database
	 */
	@WebMethod(operationName="readFoodList")
    @WebResult(name="foods") 
    public FoodList readFoodList();
	
	/**
	 * Gets a specific food based on its identifier
	 * @param id The identifier of the food
	 * @return The Food object identified by id
	 */
    @WebMethod(operationName="readFood")
    @WebResult(name="food") 
    public Food readFood(@WebParam(name="foodId") long id);
    
    
    /**
     * Creates a new food object and saves it into the database
     * @param foodToSave The food the client wants to save into the database
     * @return The Food object just inserted in the database
     */
    @WebMethod(operationName="createFood")
    @WebResult(name="food") 
    public Food createFood(@WebParam(name="food") Food foodToSave);
    
    
    /**
     * Deletes a food already present in the database
     * @param id The identifier of the food to delete
     * @return The identifier of the food just deleted
     */
    @WebMethod(operationName="deleteFood")
    @WebResult(name="idFood") 
    public long deleteFood(@WebParam(name="foodId") long id);
    
    
    /**
     * Updates an existing food in the database
     * @param foodToUpdate The food object the user wants to update
     * @return The food object just updated
     */
    @WebMethod(operationName="updateFood")
    @WebResult(name="food") 
    public Food updateFood(@WebParam(name="food") Food foodToUpdate);
    
    
    /**
     * Creates a new type for foods
     * @param typeName The name of the type the user wants to create
     * @return The type object just created
     */
    @WebMethod(operationName="createFoodType")
    @WebResult(name="foodType") 
    public FoodType createFoodType(@WebParam(name="typeName") String typeName);
    
    
    /**
     * Deletes a type already present in the database
     * @param id The identifier of the type the user wants to delete
     * @return The identifier of the type the user just deleted
     */
    @WebMethod(operationName="deleteFoodType")
    @WebResult(name="idFoodType") 
    public long deleteFoodType(@WebParam(name="typeId") long id);
    
    
    /**
     * Gets the entire set of types for the foods
     * @return A list containing the types available in the database
     */
    @WebMethod(operationName="readFoodTypeList")
    @WebResult(name="foodTypes") 
    public FoodTypeList readFoodTypeList();

}