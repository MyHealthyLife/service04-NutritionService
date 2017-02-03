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

}