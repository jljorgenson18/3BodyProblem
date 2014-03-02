import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;



/**
 * Creating the conditions for a one body problem,
 * creating a one body object
 *
 * @author Jesse Jorgenson
 * @version 1
 */


public class threeBody3d
{		
	public final double G = 6.67398E-11;
 
	private int degree;
	private int degree_plus;
		
	private double x_1;	
	private double y_1;
	private double z_1;
	private double vx_1;	
	private double vy_1; 
	private double vz_1;
		
	private double x_2;	
	private double y_2;
	private double z_2;
	private double vx_2;	
	private double vy_2; 
	private double vz_2; 
	
	private double x_3;	
	private double z_3;
	private double y_3;
	private double vx_3;	
	private double vy_3; 
	private double vz_3;
	
	private double alpha_1;
	private double alpha_2;
	private double alpha_3;
	
	private BufferedWriter bw;
	
	String dir = "Data/";
	
	/**
     * Creates a three body object based off of initial conditions
	  * and the masses involved. We will have default values
	  * in the case we don't specify and for values inputed that do
	  * not work.
     *
     */ 
	public threeBody3d(int degree, double mass1, double x_1, double y_1, double z_1,
							       double vx_1, double vy_1, double vz_1,
							       double mass2, double x_2, double y_2, double z_2,
							       double vx_2, double vy_2, double vz_2,
							       double mass3, double x_3, double y_3, double z_3,
							       double vx_3, double vy_3, double vz_3) {
		
		this.degree = degree;
		this.degree_plus = degree + 1;
		
		this.x_1   = x_1;
		this.y_1   = y_1;
		this.z_1   = z_1;
		this.vx_1  = vx_1;
		this.vy_1  = vy_1; 
		this.vz_1  = vz_1;
	
		this.x_2   = x_2;
		this.y_2   = y_2;
		this.z_2   = z_2;
		this.vx_2  = vx_2;
		this.vy_2  = vy_2; 
		this.vz_2  = vz_2;
		
		this.x_3   = x_3;
		this.y_3   = y_3;
		this.z_3   = z_3;
		this.vx_3  = vx_3;
		this.vy_3  = vy_3; 
		this.vz_3  = vz_3;
		
		
		alpha_1 = mass1 * G;
		alpha_2 = mass2 * G;
		alpha_3 = mass3 * G;
	}
	
	public threeBody3d(int degree, double x_1, double y_1, double z_1,
		       					   double vx_1, double vy_1, double vz_1,
		       					   double x_2, double y_2, double z_2,
		       					   double vx_2, double vy_2, double vz_2,
		       					   double x_3, double y_3, double z_3,
		       					   double vx_3, double vy_3, double vz_3) {
		
		this.degree = degree;
		this.degree_plus = degree + 1;
		
		this.x_1   = x_1;
		this.y_1   = y_1;
		this.z_1   = z_1;
		this.vx_1  = vx_1;
		this.vy_1  = vy_1; 
		this.vz_1  = vz_1;
	
		this.x_2   = x_2;
		this.y_2   = y_2;
		this.z_2   = z_2;
		this.vx_2  = vx_2;
		this.vy_2  = vy_2; 
		this.vz_2  = vz_2;
		
		this.x_3   = x_3;
		this.y_3   = y_3;
		this.z_3   = z_3;
		this.vx_3  = vx_3;
		this.vy_3  = vy_3; 
		this.vz_3  = vz_3;
		
		alpha_1 = 1;
		alpha_2 = 1;
		alpha_3 = 1;

	}
	
	public void update(double new_x_1, double new_y_1, double new_z_1, 
					   double new_vx_1, double new_vy_1, double new_vz_1,
					   double new_x_2, double new_y_2, double new_z_2, 
					   double new_vx_2, double new_vy_2, double new_vz_2,
					   double new_x_3, double new_y_3, double new_z_3, 
					   double new_vx_3, double new_vy_3, double new_vz_3) {
		x_1 = new_x_1;
		y_1 = new_y_1;
		z_1 = new_z_1;
		vx_1 = new_vx_1;
		vy_1 = new_vy_1;
		vz_1 = new_vz_1;
		
		x_2 = new_x_2;
		y_2 = new_y_2;
		z_2 = new_z_2;
		vx_2 = new_vx_2;
		vy_2 = new_vy_2;
		vz_2 = new_vz_2;
		
		x_3 = new_x_3;
		y_3 = new_y_3;
		z_3 = new_z_3;
		vx_3 = new_vx_3;
		vy_3 = new_vy_3;
		vz_3 = new_vz_3;
	}
	
	public double[][] calculate_coefficients() {
		
		double[] ax_1 = new double[degree_plus];
		double[] ay_1 = new double[degree_plus];
		double[] az_1 = new double[degree_plus];
		double[] avx_1 = new double[degree_plus];
		double[] avy_1 = new double[degree_plus];
		double[] avz_1 = new double[degree_plus];
		
		double[] ax_2 = new double[degree_plus];
		double[] ay_2 = new double[degree_plus];
		double[] az_2 = new double[degree_plus];
		double[] avx_2 = new double[degree_plus];
		double[] avy_2 = new double[degree_plus];
		double[] avz_2 = new double[degree_plus];
		
		double[] ax_3 = new double[degree_plus];
		double[] ay_3 = new double[degree_plus];
		double[] az_3 = new double[degree_plus];
		double[] avx_3 = new double[degree_plus];
		double[] avy_3 = new double[degree_plus];
		double[] avz_3 = new double[degree_plus];
		
		double[] w12 = new double[degree_plus];
		double[] w13 = new double[degree_plus];
		double[] w23 = new double[degree_plus];
		
		double[] u12 = new double[degree_plus];
		double[] u13 = new double[degree_plus];
		double[] u23 = new double[degree_plus];
		
		double[] x2_m_x1 = new double[degree];
		double[] y2_m_y1 = new double[degree];
		double[] z2_m_z1 = new double[degree];
		double[] vx2_m_vx1 = new double[degree];
		double[] vy2_m_vy1 = new double[degree];
		double[] vz2_m_vz1 = new double[degree];
		
		double[] x3_m_x1 = new double[degree];
		double[] y3_m_y1 = new double[degree];
		double[] z3_m_z1 = new double[degree];
		double[] vx3_m_vx1 = new double[degree];
		double[] vy3_m_vy1 = new double[degree];
		double[] vz3_m_vz1 = new double[degree];
		
		double[] x3_m_x2 = new double[degree];
		double[] y3_m_y2 = new double[degree];
		double[] z3_m_z2 = new double[degree];
		double[] vx3_m_vx2 = new double[degree];
		double[] vy3_m_vy2 = new double[degree];
		double[] vz3_m_vz2 = new double[degree];
		
		double[] t12 = new double[degree];
		double[] t13 = new double[degree];
		double[] t23 = new double[degree];
		
		double[] u12_sqr = new double[degree];
		double[] u13_sqr = new double[degree];
		double[] u23_sqr = new double[degree];
		
		double[] m12 = new double[degree];
		double[] m13 = new double[degree];
		double[] m23 = new double[degree];
												
		ax_1[0] = x_1;
		ay_1[0] = y_1;
		az_1[0] = z_1;
		avx_1[0] = vx_1;
		avy_1[0] = vy_1;
		avz_1[0] = vz_1;
		
		ax_2[0] = x_2;
		ay_2[0] = y_2;
		az_2[0] = z_2;
		avx_2[0] = vx_2;
		avy_2[0] = vy_2;
		avz_2[0] = vz_2;
		
		ax_3[0] = x_3;
		ay_3[0] = y_3;
		az_3[0] = z_3;
		avx_3[0] = vx_3;
		avy_3[0] = vy_3;
		avz_3[0] = vz_3;
		
		w12[0] = Math.sqrt((x_2 - x_1)*(x_2 - x_1) + (y_2 - y_1)*(y_2 - y_1) + (z_2 - z_1)*(z_2 - z_1));
		w13[0] = Math.sqrt((x_3 - x_1)*(x_3 - x_1) + (y_3 - y_1)*(y_3 - y_1) + (z_3 - z_1)*(z_3 - z_1));
		w23[0] = Math.sqrt((x_3 - x_2)*(x_3 - x_2) + (y_3 - y_2)*(y_3 - y_2) + (z_3 - z_2)*(z_3 - z_2));
		
		u12[0] = 1 / w12[0];
		u13[0] = 1 / w13[0];
		u23[0] = 1 / w23[0];
		
		for (int n = 1; n <= degree; n++) {
			int n_minus = n -1;
			x2_m_x1[n_minus] = ax_2[n_minus] - ax_1[n_minus];
			y2_m_y1[n_minus] = ay_2[n_minus] - ay_1[n_minus];
			z2_m_z1[n_minus] = az_2[n_minus] - az_1[n_minus];
			vx2_m_vx1[n_minus] = avx_2[n_minus] - avx_1[n_minus];
			vy2_m_vy1[n_minus] = avy_2[n_minus] - avy_1[n_minus];
			vz2_m_vz1[n_minus] = avz_2[n_minus] - avz_1[n_minus];
			
			x3_m_x1[n_minus] = ax_3[n_minus] - ax_1[n_minus];
			y3_m_y1[n_minus] = ay_3[n_minus] - ay_1[n_minus];
			z3_m_z1[n_minus] = az_3[n_minus] - az_1[n_minus];
			vx3_m_vx1[n_minus] = avx_3[n_minus] - avx_1[n_minus];
			vy3_m_vy1[n_minus] = avy_3[n_minus] - avy_1[n_minus];
			vz3_m_vz1[n_minus] = avz_3[n_minus] - avz_1[n_minus];
			
			x3_m_x2[n_minus] = ax_3[n_minus] - ax_2[n_minus];
			y3_m_y2[n_minus] = ay_3[n_minus] - ay_2[n_minus];
			z3_m_z2[n_minus] = az_3[n_minus] - az_2[n_minus];
			vx3_m_vx2[n_minus] = avx_3[n_minus] - avx_2[n_minus];
			vy3_m_vy2[n_minus] = avy_3[n_minus] - avy_2[n_minus];
			vz3_m_vz2[n_minus] = avz_3[n_minus] - avz_2[n_minus];
			
			t12[n_minus] = utilities.cauchy_product(x2_m_x1, vx2_m_vx1, n) +
					   utilities.cauchy_product(y2_m_y1, vy2_m_vy1, n) +
					   utilities.cauchy_product(z2_m_z1, vz2_m_vz1, n);
			
			t13[n_minus] = utilities.cauchy_product(x3_m_x1, vx3_m_vx1, n) +
					   utilities.cauchy_product(y3_m_y1, vy3_m_vy1, n) +
					   utilities.cauchy_product(z3_m_z1, vz3_m_vz1, n);
			
			t23[n_minus] = utilities.cauchy_product(x3_m_x2, vx3_m_vx2, n) +
					   utilities.cauchy_product(y3_m_y2, vy3_m_vy2, n) +
					   utilities.cauchy_product(z3_m_z2, vz3_m_vz2, n);
			
			
			u12_sqr[n_minus] = utilities.cauchy_product(u12,u12,n);
			u13_sqr[n_minus] = utilities.cauchy_product(u13,u13,n);
			u23_sqr[n_minus] = utilities.cauchy_product(u23,u23,n);
			
			m12[n_minus] = utilities.cauchy_product(u12_sqr,u12,n);
			m13[n_minus] = utilities.cauchy_product(u13_sqr,u13,n);
			m23[n_minus] = utilities.cauchy_product(u23_sqr,u23,n);
			
			w12[n] = utilities.cauchy_product(u12,t12,n) / n;
			w13[n] = utilities.cauchy_product(u13,t13,n) / n;
			w23[n] = utilities.cauchy_product(u23,t23,n) / n;
			
			u12[n] = -utilities.cauchy_product(m12,t12,n) / n;
			u13[n] = -utilities.cauchy_product(m13,t13,n) / n;
			u23[n] = -utilities.cauchy_product(m23,t23,n) / n;
			
			ax_1[n] = avx_1[n_minus] / n;
			ay_1[n] = avy_1[n_minus] / n;
			az_1[n] = avz_1[n_minus] / n;
			
			ax_2[n] = avx_2[n_minus] / n;
			ay_2[n] = avy_2[n_minus] / n;
			az_2[n] = avz_2[n_minus] / n;
			
			ax_3[n] = avx_3[n_minus] / n;
			ay_3[n] = avy_3[n_minus] / n;
			az_3[n] = avz_3[n_minus] / n;
			
			avx_1[n] =  (alpha_2 / n) * utilities.cauchy_product(x2_m_x1, m12, n) +
						(alpha_3 / n) * utilities.cauchy_product(x3_m_x1, m13, n);
			avy_1[n] =  (alpha_2 / n) * utilities.cauchy_product(y2_m_y1, m12, n) +
						(alpha_3 / n) * utilities.cauchy_product(y3_m_y1, m13, n);
			avz_1[n] =  (alpha_2 / n) * utilities.cauchy_product(z2_m_z1, m12, n) +
						(alpha_3 / n) * utilities.cauchy_product(z3_m_z1, m13, n);
			
			avx_2[n] = -(alpha_1 / n) * utilities.cauchy_product(x2_m_x1, m12, n) +
						(alpha_3 / n) * utilities.cauchy_product(x3_m_x2, m23, n);
			avy_2[n] = -(alpha_1 / n) * utilities.cauchy_product(y2_m_y1, m12, n) +
						(alpha_3 / n) * utilities.cauchy_product(y3_m_y2, m23, n);
			avz_2[n] = -(alpha_1 / n) * utilities.cauchy_product(z2_m_z1, m12, n) +
						(alpha_3 / n) * utilities.cauchy_product(z3_m_z2, m23, n);
		
			avx_3[n] = -(alpha_1 / n) * utilities.cauchy_product(x3_m_x1, m13, n) +
					   -(alpha_2 / n) * utilities.cauchy_product(x3_m_x2, m23, n);
			avy_3[n] = -(alpha_1 / n) * utilities.cauchy_product(y3_m_y1, m13, n) +
					   -(alpha_2 / n) * utilities.cauchy_product(y3_m_y2, m23, n);
			avz_3[n] = -(alpha_1 / n) * utilities.cauchy_product(z3_m_z1, m13, n) +
					   -(alpha_2 / n) * utilities.cauchy_product(z3_m_z2, m23, n);
			
		}
		
		double[][] coefficients = {ax_1, ay_1, az_1, avx_1, avy_1, avz_1,
				                   ax_2, ay_2, az_2, avx_2, avy_2, avz_2,
				                   ax_3, ay_3, az_3, avx_3, avy_3, avz_3,
								   w12, w13, w23};
											
		return coefficients;
		
	}
	
	public void getData(double time, String Name, double delta) throws IOException {
		
		double[] initial = {x_1, y_1, z_1, vx_1, vy_1, vz_1, x_2, y_2, z_2, vx_2, vy_2, vz_2,
				x_3, y_3, z_3, vx_3, vy_3, vz_3};
		
		double[][] coefficients = new double[21][];
		double[] evaluated = new double[21];
		double current2;
		double delta2;
		double delta3;
		double M;
		double minimum_distance;
		double current;
		DecimalFormat df2 = new DecimalFormat("0.00000##");
		String mag_string = "";
	
		bw = new BufferedWriter(new FileWriter(dir + Name + ".txt"));
			
		evaluated[18] = Math.sqrt((x_2 - x_1)*(x_2 - x_1) + 
				   		   			  (y_2 - y_1)*(y_2 - y_1) + 
				   		              (z_2 - z_1)*(z_2 - z_1));
				
		evaluated[19] = Math.sqrt((x_3 - x_1)*(x_3 - x_1) + 
 		   				  			  (y_3 - y_1)*(y_3 - y_1) + 
 		   				  			  (z_3 - z_1)*(z_3 - z_1));
				
		evaluated[20] = Math.sqrt((x_3 - x_2)*(x_3 - x_2) + 
 		   				  			  (y_3 - y_2)*(y_3 - y_2) + 
 		   				  			  (z_3 - z_2)*(z_3 - z_2));
				
		bw.write("0" + " " + x_1 + " " + y_1 + " " + z_1 + " " + 
					             vx_1 + " " + vy_1 + " " + vz_1 + " " +
					             x_2 + " " + y_2 + " " + z_2 + " " + 
					             vx_2 + " " + vy_2 + " " + vz_2 + " " +
					             x_3 + " " + y_3 + " " + z_3 + " " + 
					             vx_3 + " " + vy_3 + " " + vz_3 + " ");
		bw.write(evaluated[18] + " ");
		bw.write(evaluated[19] + " ");
		bw.write(evaluated[20] + " \n");
		 	
		current = 0;
			
		while (current < time) {
				
		    bw.write(current + " ");
							
			current2 = current;
			minimum_distance = Math.abs(Math.min(Math.min(evaluated[18],evaluated[19]), evaluated[20]));
					
			// System.out.println("minimum_distance = " + minimum_distance);
					
			M = 6 / minimum_distance;
					
			// System.out.println("M = " + M);
					
			delta2 = delta / M;
						
			// System.out.println("Delta2 = " + delta2);
			while (current2 < (current + delta - delta2)) {
						
				coefficients = calculate_coefficients();
					
				for (int k = 0; k <= 17; k++) evaluated[k] = utilities.calculate_function(coefficients[k], delta2);
						
				update(evaluated[0], evaluated[1], evaluated[2], evaluated[3],  evaluated[4], evaluated[5], 
					   evaluated[6], evaluated[7], evaluated[8], evaluated[9],  evaluated[10], evaluated[11],
					   evaluated[12], evaluated[13], evaluated[14], evaluated[15],  evaluated[16], evaluated[17]);
						
				current2 += delta2;
						
				// System.out.println("Current2 = " + current2);
				
			}
					
			current += delta;
					
			System.out.printf("Current Time = " + df2.format(current2) + "\t\t" + mag_string + "\n");
			delta3 = current - current2;
			coefficients = calculate_coefficients();
					
			for (int k = 0; k <= 20; k++) evaluated[k] = utilities.calculate_function(coefficients[k], delta3);
					
			update(evaluated[0], evaluated[1], evaluated[2], evaluated[3],  evaluated[4], evaluated[5], 
				   evaluated[6], evaluated[7], evaluated[8], evaluated[9],  evaluated[10], evaluated[11],
				   evaluated[12], evaluated[13], evaluated[14], evaluated[15],  evaluated[16], evaluated[17]);
					
					
			for (int k = 0; k <= 20; k ++) bw.write(evaluated[k] + " ");
					
			bw.write("\n");
				
			}
			bw.close();
			
			update(initial[0], initial[1], initial[2], initial[3],  initial[4], initial[5], 
				   initial[6], initial[7], initial[8], initial[9],  initial[10], initial[11],
			       initial[12], initial[13], initial[14], initial[15],  initial[16], initial[17]);
		}
	
	
	public void getDataDyn(double time, String Name, double Breakpoint, double delta) throws IOException {
		int flagger = 0;
		int flag;
		double[] initial = {x_1, y_1, z_1, vx_1, vy_1, vz_1, x_2, y_2, z_2, vx_2, vy_2, vz_2,
				x_3, y_3, z_3, vx_3, vy_3, vz_3};
		
		double[][] coefficients = new double[21][];
		double[] evaluated = new double[21];
		double current2;
		double delta2;
		double delta3;
		double M;
		double minimum_distance;
		double current;
		double m = 1;
		DecimalFormat df = new DecimalFormat("0.000##");
		DecimalFormat df2 = new DecimalFormat("0.00000##");
		String mag_string = "";
	
		while (flagger == 0) {	
			
			flag = 0;
			
			bw = new BufferedWriter(new FileWriter(dir + Name + ".txt"));
			
			evaluated[18] = Math.sqrt((x_2 - x_1)*(x_2 - x_1) + 
				   		   			  (y_2 - y_1)*(y_2 - y_1) + 
				   		              (z_2 - z_1)*(z_2 - z_1));
				
			evaluated[19] = Math.sqrt((x_3 - x_1)*(x_3 - x_1) + 
 		   				  			  (y_3 - y_1)*(y_3 - y_1) + 
 		   				  			  (z_3 - z_1)*(z_3 - z_1));
				
			evaluated[20] = Math.sqrt((x_3 - x_2)*(x_3 - x_2) + 
 		   				  			  (y_3 - y_2)*(y_3 - y_2) + 
 		   				  			  (z_3 - z_2)*(z_3 - z_2));
				
			bw.write("0" + " " + x_1 + " " + y_1 + " " + z_1 + " " + 
					             vx_1 + " " + vy_1 + " " + vz_1 + " " +
					             x_2 + " " + y_2 + " " + z_2 + " " + 
					             vx_2 + " " + vy_2 + " " + vz_2 + " " +
					             x_3 + " " + y_3 + " " + z_3 + " " + 
					             vx_3 + " " + vy_3 + " " + vz_3 + " ");
		    bw.write(evaluated[18] + " ");
		    bw.write(evaluated[19] + " ");
		    bw.write(evaluated[20] + " \n");
		 	
			current = 0;
			
			while (current < time) {
				
				bw.write(current + " ");
				
				if ( (Math.abs(evaluated[18]) > Breakpoint) || (Math.abs(evaluated[19]) > Breakpoint) ) {
					m += 0.5;
					mag_string = "Last Break at = " + df.format(current) + " (" + df.format((current / time) * 100) +
							" Percent)  ||  Magnitude = " + Double.toString(m);
					flag = -1;
					break;
				}
				
				
				else {
					
					current2 = current;
					minimum_distance = Math.abs(Math.min(Math.min(evaluated[18],evaluated[19]), evaluated[20]));
					
				    // System.out.println("minimum_distance = " + minimum_distance);
					
					M = Math.pow((Breakpoint / minimum_distance), m);
					
				    // System.out.println("M = " + M);
					
					delta2 = delta / M;
						
					// System.out.println("Delta2 = " + delta2);
					while (current2 < (current + delta - delta2)) {
						
						coefficients = calculate_coefficients();
					
						for (int k = 0; k <= 17; k++) evaluated[k] = utilities.calculate_function(coefficients[k], delta2);
						
						update(evaluated[0], evaluated[1], evaluated[2], evaluated[3],  evaluated[4], evaluated[5], 
								evaluated[6], evaluated[7], evaluated[8], evaluated[9],  evaluated[10], evaluated[11],
							    evaluated[12], evaluated[13], evaluated[14], evaluated[15],  evaluated[16], evaluated[17]);
						
						current2 += delta2;
						
						// System.out.println("Current2 = " + current2);
				
					}
					
					current += delta;
					
					System.out.printf("Current Time = " + df2.format(current2) + "\t\t" + mag_string + "\n");
					delta3 = current - current2;
					coefficients = calculate_coefficients();
					
					for (int k = 0; k <= 20; k++) evaluated[k] = utilities.calculate_function(coefficients[k], delta3);
					
					update(evaluated[0], evaluated[1], evaluated[2], evaluated[3],  evaluated[4], evaluated[5], 
							evaluated[6], evaluated[7], evaluated[8], evaluated[9],  evaluated[10], evaluated[11],
						    evaluated[12], evaluated[13], evaluated[14], evaluated[15],  evaluated[16], evaluated[17]);
					
					
					for (int k = 0; k <= 20; k ++) bw.write(evaluated[k] + " ");
					
					bw.write("\n");
				
				}
			}
			bw.close();
			
			flagger = flag + 1;
			
			update(initial[0], initial[1], initial[2], initial[3],  initial[4], initial[5], 
				   initial[6], initial[7], initial[8], initial[9],  initial[10], initial[11],
			       initial[12], initial[13], initial[14], initial[15],  initial[16], initial[17]);
			
			
			}
			
	
		}
}	


