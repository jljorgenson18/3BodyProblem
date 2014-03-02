import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;



public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame(String name) throws IOException {
		super(name + " demo");
		double p1;
		double p2;
		threeBody3d test_body = new threeBody3d(30,  -1, 0, 0, 
													0, 0.7, 0, 
													1, 0, 0, 
													0, -0.7, 0, 
													0, 0, 0, 
													0, 0, 0);
		
		

		threeBody3d brouke = new threeBody3d(30,  -1, 0, 0, 
												0,-0.939325, 0, 
												0.5,-0.647584, 0, 
												-0.505328,0.4696663, 0, 
												0.5,0.647584, 0, 
												0.505328,0.4696663, 0);
												
		
	
		p1 = 0.347111;
		p2 = 0.532728;
		
		threeBody3d figure_8 = new threeBody3d(30,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.306893;
		p2 = 0.125507;
		
		threeBody3d BUTTERFLY_I = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.392955;
		p2 = 0.097579;
		
		threeBody3d BUTTERFLY_II = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.184279;
		p2 = 0.587188;
		
		threeBody3d BUMBLEBEE  = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.080584;
		p2 = 0.588836;
		
		threeBody3d DRAGONFLY = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		p1 = 0.083300;
		p2 = 0.127889;
		
		threeBody3d GOGGLES = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		p1 = 0.464445;
		p2 = 0.396060;
		
		threeBody3d MOTH_I  = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		p1 = 0.439166;
		p2 = 0.452968;
		
		threeBody3d MOTH_II  = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		p1 = 0.383444;
		p2 = 0.377364;
		
		threeBody3d MOTH_III = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.405916;
		p2 = 0.230163;
		
		threeBody3d BUTTERFLY_III = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.350112;
		p2 = 0.079339;
		
		threeBody3d BUTTERFLY_IV = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.559064;
		p2 = 0.349192;
		
		threeBody3d YARN = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.513938;
		p2 = 0.304736;
		
		threeBody3d YIN_YANG_1a = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.282699;
		p2 = 0.327209;
		
		threeBody3d YIN_YANG_1b  = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.416822;
		p2 = 0.330333;
		
		threeBody3d YIN_YANG_2a = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		p1 = 0.417343;
		p2 = 0.313100;
		
		threeBody3d YIN_YANG_2b = new threeBody3d(20,  -1, 0, 0, 
													 p1, p2, 0, 
													 1, 0, 0, 
													 p1, p2, 0,
													 0, 0, 0, 
												 -2*p1, -2*p2, 0);
		
		
		
		// brouke.getDataDyn(6.52, "BROUCKE-HENON", 5, 0.005);
		brouke.getData(6.52, "BROUCKE-HENON", 0.005);
		// figure_8.getDataDyn(6.324449, "FIGURE_8", 5, 0.005);
		// BUTTERFLY_I.getDataDyn(6.235641, "BUTTERFLY_I", 5, 0.003);
		// BUTTERFLY_II.getDataDyn(7.003907, "BUTTERFLY_II", 5, 0.004);
		// BUMBLEBEE.getDataDyn(63.534541, "BUMBLEBEE", 5, 0.005);
		// DRAGONFLY.getDataDyn(21.270975, "DRAGONFLY", 5, 0.005);
		// GOGGLES.getDataDyn(10.466818, "GOGGLES", 5, 0.0035);
		// MOTH_I.getDataDyn(14.893911, "MOTH_I", 5, 0.005);
		// MOTH_II.getDataDyn(28.670278, "MOTH_II", 5, 0.005);
		// MOTH_III.getDataDyn(25.840618, "MOTH_III", 5, 0.005);
		// BUTTERFLY_III.getDataDyn(13.865763, "BUTTERFLY_III", 5, 0.005);
		// BUTTERFLY_IV.getDataDyn(79.475875, "BUTTERFLY_IV", 5, 0.004);
		// YARN.getDataDyn(55.501762, "YARN", 5, 0.005);
		// YIN_YANG_1a.getDataDyn(17.328370, "YIN_YANG_1a", 5, 0.005);
		// YIN_YANG_1b.getDataDyn(10.962563, "YIN_YANG_1b", 5, 0.004);
		// YIN_YANG_2a.getDataDyn(55.789829, "YIN_YANG_2a", 5, 0.005);
		// YIN_YANG_2b.getDataDyn(54.207599, "YIN_YANG_2b", 5, 0.005);
		
	
		GLCapabilities capabilities = createGLCapabilities();
    	threeBodyGraphics canvas = null;
    
    
		try {
			canvas = new threeBodyGraphics (capabilities, 1440, 900, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    	Container content = getContentPane();
    	//content.setLayout(new FlowLayout());
    	
    	content.add(canvas, BorderLayout.CENTER);
    	
    	setVisible(true);
    	
	}

	private static GLCapabilities createGLCapabilities() {
		GLCapabilities capabilities = new GLCapabilities(null);
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		return capabilities;
	}

}
