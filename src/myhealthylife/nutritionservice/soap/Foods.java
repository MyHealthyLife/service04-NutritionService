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
    @WebResult(name="food") 
    public FoodList readFoodList();
	
	
    

}