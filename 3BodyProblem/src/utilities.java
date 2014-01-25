
/**
 * The class gives a few utility methods for 
 * mathematical modeling using power series.
 *
 * @author Jesse Jorgenson
 * @version 1
 */

public class utilities {

	/**
     * This method finds the cauchy product of two
	  * sets of coefficients
     *
     * @param Two arrays of coefficients and the number of 
	  *		  coefficients used in each array
     * @return A double made from the cauchy product 
     */ 
	public static double cauchy_product(double[] array1, double[] array2, int size) {
	
		double sum;
		sum = 0;
		for (int i = 0; i < size; i++) sum += (array1[i] * array2[size - 1 - i]);
		return sum;
	}
	
	/**
     * This method uses horner's algorithm to evaluate
	  * a power series function based off of its coefficients
     *
     * @param An array of coefficients and the function input
     * @return A double, the output of the function 
     */ 
	public static double calculate_function(double[] array, double t) {
		
		int m;
		double return_val;
		
		m = array.length;
		
		return_val = array[m - 2] + array[m - 1] * t;
		for (int i = (m - 3); i >= 0; i--) {
			return_val = array[i] + return_val * t;
		}
		
		return return_val;		
	}
}
