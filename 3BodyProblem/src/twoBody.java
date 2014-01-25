import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Creating the conditions for a one body problem,
 * creating a one body object
 *
 * @author Jesse Jorgenson
 * @version 1
 */


public class twoBody
{		
	public final double G = 6.67398E-11;
 
	private int degree;
		
	private double x_1;	
	private double y_1; 
	private double vx_1;	
	private double vy_1; 
		
	private double x_2;	
	private double y_2; 
	private double vx_2;	
	private double vy_2; 
	private double alpha_1;
	private double alpha_2;
		 			
	
	
	/**
     * Creates a two body object based off of initial conditions
	  * and the masses involved. We will have default values
	  * in the case we don't specify and for values inputted that do
	  * not work.
     *
     */ 
	public twoBody(int degree,double mass1,double x_1,double y_1,double vx_1,double vy_1,
						      double mass2,double x_2,double y_2,double vx_2,double vy_2) {
		
		this.degree = degree;
		
		this.x_1   = x_1;
		this.y_1   = y_1;
		this.vx_1  = vx_1;
		this.vy_1  = vy_1; 
	
		this.x_2   = x_2;
		this.y_2   = y_2;
		this.vx_2  = vx_2;
		this.vy_2  = vy_2;
		
		alpha_1 = mass1 * G;
		alpha_2 = mass2 * G;
	}
	
	public twoBody(int degree, double x_1,double y_1,double vx_1,double vy_1,
						       double x_2,double y_2,double vx_2,double vy_2) {
		
		this.degree = degree;
		
		this.x_1   = x_1;
		this.y_1   = y_1;
		this.vx_1  = vx_1;
		this.vy_1  = vy_1; 
	
		this.x_2   = x_2;
		this.y_2   = y_2;
		this.vx_2  = vx_2;
		this.vy_2  = vy_2;
		
		alpha_1 = 1;
		alpha_2 = 1;
	}
	
	public void update(double new_x_1, double new_y_1,double new_vx_1, double new_vy_1,
					   double new_x_2, double new_y_2,double new_vx_2, double new_vy_2) {
		x_1 = new_x_1;
		y_1 = new_y_1;
		vx_1 = new_vx_1;
		vy_1 = new_vy_1;
		
		x_2 = new_x_2;
		y_2 = new_y_2;
		vx_2 = new_vx_2;
		vy_2 = new_vy_2;
	}
	
	public double[][] calculate_coefficients() {
		double[] ax_1 = new double[degree+1];
		double[] ay_1 = new double[degree+1];
		double[] avx_1 = new double[degree+1];
		double[] avy_1 = new double[degree+1];
		
		double[] ax_2 = new double[degree+1];
		double[] ay_2 = new double[degree+1];
		double[] avx_2 = new double[degree+1];
		double[] avy_2 = new double[degree+1];
		
		double[] w = new double[degree+1];
		double[] u = new double[degree+1];
		
		double[] x2_m_x1 = new double[degree];
		double[] y2_m_y1 = new double[degree];
		double[] vx2_m_vx1 = new double[degree];
		double[] vy2_m_vy1 = new double[degree];
		
		double[] z = new double[degree];
		double[] u_sqr = new double[degree];
		double[] m = new double[degree];
		
		
		
											
		ax_1[0] = x_1;
		ay_1[0] = y_1;
		avx_1[0] = vx_1;
		avy_1[0] = vy_1;
		
		ax_2[0] = x_2;
		ay_2[0] = y_2;
		avx_2[0] = vx_2;
		avy_2[0] = vy_2;
		
		w[0] = Math.sqrt((x_2 - x_1)*(x_2 - x_1) + (y_2 - y_1)*(y_2 - y_1));
		u[0] = 1 / w[0];
		
		for (int n = 1; n <= degree; n++) {
			x2_m_x1[n-1] = ax_2[n-1] - ax_1[n-1];
			y2_m_y1[n-1] = ay_2[n-1] - ay_1[n-1];
			vx2_m_vx1[n-1] = avx_2[n-1] - avx_1[n-1];
			vy2_m_vy1[n-1] = avy_2[n-1] - avy_1[n-1];
			
			z[n-1] = utilities.cauchy_product(x2_m_x1, vx2_m_vx1, n) -
						utilities.cauchy_product(y2_m_y1, vy2_m_vy1, n);
			
			
			u_sqr[n-1] = utilities.cauchy_product(u,u,n);
			m[n-1] = utilities.cauchy_product(u_sqr,u,n);
			
			w[n] = utilities.cauchy_product(u,z,n) / n;
			u[n] = -utilities.cauchy_product(m,z,n) / n;
			
			ax_1[n] = avx_1[n-1] / n;
			ay_1[n] = avy_1[n-1] / n;
			ax_2[n] = avx_2[n-1] / n;
			ay_2[n] = avy_2[n-1] / n;
			
			avx_1[n] =  (alpha_2 / n) * utilities.cauchy_product(x2_m_x1, m, n);
			avy_1[n] =  (alpha_2 / n) * utilities.cauchy_product(y2_m_y1, m, n);
			avx_2[n] = -(alpha_1 / n) * utilities.cauchy_product(x2_m_x1, m, n);
			avy_2[n] = -(alpha_1 / n) * utilities.cauchy_product(y2_m_y1, m, n);
			
		}
		
		double[][] coefficients = {ax_1, ay_1, avx_1, avy_1, 
								   ax_2, ay_2, avx_2, avy_2,
								   w, u, x2_m_x1, y2_m_y1,
								   vx2_m_vx1, vy2_m_vy1, z,
								   u_sqr, m};
											
		return coefficients;
		
	}
	
	public void getData(double time, int steps, String Name) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/Faculty/Desktop/jesse_kelsey_research/"
																	+ Name + ".txt"));
			bw.write(Name + "\n");
			bw.write("\n");
			
			double interval = time / steps;
			double[][] coefficients = new double[17][degree+1];
			double[] evaluated = new double[9];
			
			bw.write("0" + " " + x_1 + " " + y_1 + " " + vx_1 + " " + vy_1 + " ");
			bw.write(x_2 + " " + y_2 + " " + vx_2 + " " + vy_2 + " ");
			bw.write(Math.sqrt((x_2 - x_1)*(x_2 - x_1) + (y_2 - y_1)*(y_2 - y_1)) + "\n");
			
			for (int i = 1; i <= steps; i++) {
				coefficients = calculate_coefficients();
				bw.write((i * interval) + " ");
				
				for (int k = 0; k < 9; k++) {
					evaluated[k] = utilities.calculate_function(coefficients[k], interval);
					bw.write(evaluated[k] + " ");
				}
				bw.write("\n");
				
				update(evaluated[0], evaluated[1], evaluated[2], evaluated[3], 
					   evaluated[4], evaluated[5], evaluated[6], evaluated[7]);	
			}
			bw.close();
			
		}
		catch (IOException ex) {
			Logger.getLogger(testingMethod.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	
	}	
}