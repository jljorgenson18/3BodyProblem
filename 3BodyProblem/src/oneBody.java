 

/**
 * Creating the conditions for a one body problem,
 * creating a one body object
 *
 * @author Jesse Jorgenson
 * @version 1
 */


public class oneBody
{
	private double alpha;			//This gives the constant associated with the power series
	private double init_position;	//This gives an initial amount (or y(0)) to work with
	private double init_velocity; //This gives an initial velocity (or y'(0)) to work with 
	private int degree;				//This gives the degree of the power series
	
	
	/**
     * Creates a one body object based off of initial conditions
	  * and the force of the constant (gravity and special masses will 
	  * be stored in the main method). We will have default values
	  * in the case we don't specify and for values inputted that do
	  * not work.
     *
     * @param The initial position, initial velocity, and the constant
	  * 		  alpha
     */ 
	public oneBody() {
		
		this.alpha = 1;
		this.init_position = 10;
		this.init_velocity = 0;
		this.degree = 12;
	}
	
	
	public oneBody(double alpha) {
	
		if (alpha != 0) this.alpha = alpha;
		else alpha = 1;
		
		this.init_position = 10;
		
		this.init_velocity = 0;
		
		this.degree = 12;
	}
	
	public oneBody(double alpha, double init_position) {

		if (alpha != 0) this.alpha = alpha;
		else alpha = 1;

		if (init_position != 0) this.init_position = init_position;
		else init_position = 10;
		
		this.init_velocity = 0;
		
		this.degree = 12;
	}

	public oneBody(double alpha, double init_position, double init_velocity) {
	
		if (alpha != 0) this.alpha = alpha;
		else alpha = 1;

		if (init_position != 0) this.init_position = init_position;
		else init_position = 10;

		this.init_velocity = init_velocity;
		
		this.degree = 10;
	}

	public oneBody(double alpha, double init_position, double init_velocity, int degree) {
	
		if (alpha != 0) this.alpha = alpha;
		else alpha = 1;

		if (init_position != 0) this.init_position = init_position;
		else init_position = 10;
		
		this.init_velocity = init_velocity;
		
		if (degree >= 1) this.degree = degree;
		else degree = 10;
	}
	
	/**
     * This method updates the initial position, this is mainly
	  * used to continually evaluate data and plot a graph.
     *
     * @param The new position
     */ 
	public void update_position(double position) {
	
		init_position = position;
		
	}
	
	/**
     * This method updates the initial velocity, this is mainly
	  * used to continually evaluate data and plot a graph.
     *
     * @param The new velocity
     */ 
	public void update_velocity(double velocity) {
	
		init_velocity = velocity;
		
	}
	
	/**
     * This method uses the Parker Sochacki Method
	  * to evaluate the power series coefficients for a 
	  * related functions (mainly the position and velocity 
	  * functions).
     *
     * @return A 2d array having the power series 
	  *			coefficients of y, y_prime, y^(-1),
	  *			and y^(-2)
     */ 		
	public double[][] calculate_coefficients()
	{
		double[] y = new double[degree+1];
		double[] v = new double[degree+1];
		double[] u = new double[degree+1];
		double[] w = new double[degree+1];
		
		y[0] = init_position;
		v[0] = init_velocity;
		u[0] = 1/y[0];
		w[0] = u[0] * u[0];
		
		for (int n = 1; n <= degree; n++) {
			y[n] = v[n - 1] / n;
			v[n] = (-alpha / n) * w[n - 1];
			u[n] = -utilities.cauchy_product(u, w, n) / n;
			w[n] = utilities.cauchy_product(u, u, n + 1);
		}
		
		// The following is for testing the method
		
		/**
		
		System.out.println("The values for y are");
		for (int i = 0; i <= degree; i++) System.out.println(y[i]);
		System.out.println();
		
		System.out.println("The values for v are");
		for (int i = 0; i <= degree; i++) System.out.println(v[i]);
		System.out.println();
		
		System.out.println("The values for u are");
		for (int i = 0; i <= degree; i++) System.out.println(u[i]);
		System.out.println();
		
		System.out.println("The values for w are");
		for (int i = 0; i <= degree; i++) System.out.println(w[i]);
		System.out.println();
		
		*/
		
		double[][] return_array = {y, v, u, w};
		
		return return_array;		
	}
	
	
	/**
     * This method continually evaluates our functions
	  * on a time interval with a certain level of precision.
	  * We update after each evaluation to gain greater accuracy
	  * at each point.
     *
     * @param The amount of time evaluated and the number
	  *		  calculations on that interval
	  * @return A 2d array giving the outputs of each function
	  *			at each step
     */ 		
	public double[][] get_data(double time, int steps) {
	
		double interval = time / steps;
		
		double[] t   = new double[steps];
		double[] y_t = new double[steps];
		double[] v_t = new double[steps];
		double[] u_t = new double[steps];
		double[][] coefficients = calculate_coefficients();
		
		t[0]   = 0;
		y_t[0] = coefficients[0][0];
		v_t[0] = coefficients[1][0];
		u_t[0] = coefficients[2][0];
		
		for (int i = 1; i < steps; i++) {
			t[i]   = i * interval;
			y_t[i] = utilities.calculate_function(coefficients[0], interval) ;
			v_t[i] = utilities.calculate_function(coefficients[1], interval) ;
			u_t[i] = utilities.calculate_function(coefficients[2], interval) ;
			
			update_position(y_t[i]);
			update_velocity(v_t[i]);
			coefficients = calculate_coefficients();
		}
		
		double[][] return_data = {t, y_t, v_t, u_t};
		
		return return_data;

	}

}


