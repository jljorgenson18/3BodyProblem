
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;

import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;




public class ThreeBodyGraphics extends GLCanvas implements GLEventListener {

	    /** Serial version UID. */
	    private static final long serialVersionUID = 1L;
	    
	    /** The GL unit (helper class). */
	    private GLU glu;

	    /** The frames per second setting. */
	    private int fps = 150;

	    /** The OpenGL animator. */
	    private FPSAnimator animator;
	    
	    String new_line = "";
	    String[] new_string_coord = new String[22];
	    double[] new_double_coord = new double[22];
	    
	    private DecimalFormat df1 = new DecimalFormat("00.00");
	    private DecimalFormat df2 = new DecimalFormat("0.00");
	    
	    private float[] rgba1 = {0.82f, 0.8f, 0.26f};
   	 
   	 	private float[] rgba2 = {0.61f, 0.25f, 0.82f};
   	 
   	 	private float[] rgba3 = {1f, 1f, 1f};
   	 	
   	 	private int stretch = 1;

		private BufferedReader br;
		
		private BufferedReader br2;
		
		private Reader file;
		
		private Reader file2;
		
		private String file_name;
	    
		boolean enableVelocityVector = true;
		
		// Prepare light parameters.
        private float SHINE_ALL_DIRECTIONS = 1;
        private float[] lightPos = {-20, 20, 0, SHINE_ALL_DIRECTIONS};
        private float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 0.5f};
        private float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 0.5f};
		
		private int trail;
		
		final float radius = 0.03f;
        final int slices = 8;
        final int stacks = 8;
        
        //Optional Directory
        String dir = "Data/";
	    
	    /**
	     * A new mini starter.
	     * 
	     * @param capabilities The GL capabilities.
	     * @param width The window width.
	     * @param height The window height.
	     * @throws IOException 
	     */
	    public threeBodyGraphics (GLCapabilities capabilities, int width, int height, String file_name) throws IOException {
	        addGLEventListener(this);
	        
	        this.file_name = file_name;
	        
	        file = new FileReader(dir + file_name + ".txt");
	        
	        file2 = new FileReader(dir + file_name + ".txt");
		    br = new BufferedReader(file);
		    
		    br2 = new BufferedReader(file);
		  
	    }


	    /**
	     * Sets up the screen.
	     * 
	     * @see javax.media.opengl.GLEventListener#init(javax.media.opengl.GLAutoDrawable)
	     */
	    public void init(GLAutoDrawable drawable) {
	    	
	    	drawable.setGL(new DebugGL2(drawable.getGL().getGL2()));
	        final GL2 gl = drawable.getGL().getGL2();
	        
	        // Enable z- (depth) buffer for hidden surface removal. 
	        gl.glEnable(GL2.GL_DEPTH_TEST);
	        gl.glDepthFunc(GL2.GL_LEQUAL);

	        // Enable smooth shading.
	        gl.glShadeModel(GL2.GL_SMOOTH);

	        // Define "clear" color.
	        gl.glClearColor(0f, 0f, 0f, 0f);

	        // We want a nice perspective.
	        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

	        // Create GLU.
	        glu = new GLU();
	        
	        // create one display list
	        trail = gl.glGenLists(1);
	        
	        // compile the display list, store a triangle in it
	        gl.glNewList(trail, GL2.GL_COMPILE);
	        plotTrail(gl, lightPos, lightColorAmbient, lightColorSpecular);
	        gl.glEndList();
	        
	        // Start animator.
	        animator = new FPSAnimator(this, fps);
	        animator.start();
	        
	        
	        
	    }

	    /**
	     * The changes in the canvas
	     * 
	     * @see javax.media.opengl.GLEventListener#display(javax.media.opengl.GLAutoDrawable)
	     */
	    public void display(GLAutoDrawable drawable) {
	    	
	    	
	    	if (!animator.isAnimating()) {
	            return;
	        }
	    	
	    	final GL2 gl = drawable.getGL().getGL2();
	    	
	    	 // Clear screen.
	        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	        

	        // Set camera.
	        setCamera(gl, glu, 3.2f);
	        
	        gl.glCallList(trail);
	    	
			try {
				new_line = br.readLine();
				
				//If it reaches the end, reread from the beginning
				if (new_line == null) {
					try {
						file.close();
						file = new FileReader(dir + file_name + ".txt");
						br = new BufferedReader(file);
						new_line = br.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
				
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Split the string up and convert everything to doubles
			new_string_coord = new_line.split(" ");
			
			for (int k = 0; k < 22; k++) new_double_coord[k] = Double.parseDouble(new_string_coord[k]);
			
	        
	        //_______________________________________________________________________
	        
	        // SPEHERE 1
			
			setLighting(gl, rgba1, lightPos, lightColorAmbient, lightColorSpecular);
	        
	        // Compute sphere position.
	        final float x1 = (float) new_double_coord[1];
	        final float y1 = (float) new_double_coord[2] * stretch;
	        final float z1 = 0;
	        gl.glTranslatef(x1, y1, z1);
	        
	        drawSphere(glu);
	         
	        if (enableVelocityVector) drawVelocityVector(gl, (float) new_double_coord[4], (float) new_double_coord[5], 0f);
	        
	        // Save old state.
	        gl.glPushMatrix();
	        
	        //_______________________________________________________________________
	        
	        // SPEHERE 2
	        
	        gl.glLoadIdentity(); 
	        
	        setLighting(gl, rgba2, lightPos, lightColorAmbient, lightColorSpecular);
	        
	        // Compute sphere position.
	        final float x2 = (float) new_double_coord[7];
	        final float y2 = (float) new_double_coord[8] * stretch;
	        final float z2 = 0;
	        gl.glTranslatef(x2, y2, z2);

	        drawSphere(glu);
	           
	        if (enableVelocityVector) drawVelocityVector(gl, (float) new_double_coord[10], (float) new_double_coord[11], 0f);
	        
	        // Save old state.
	        gl.glPushMatrix();
	        
	        //_______________________________________________________________________
	        
	        // SPHERE 3
	        
	        gl.glLoadIdentity();
	        
	        setLighting(gl, rgba3, lightPos, lightColorAmbient, lightColorSpecular);
	        
	        // Compute sphere position.
	        final float x3 = (float) new_double_coord[13];
	        final float y3 = (float) new_double_coord[14] * stretch;
	        final float z3 = 0;
	        gl.glTranslatef(x3, y3, z3);

	        drawSphere(glu);
	        
	        if (enableVelocityVector) drawVelocityVector(gl, (float) new_double_coord[16], (float) new_double_coord[17], 0f);
	        
	        // Save old state.
	        gl.glPushMatrix();
	        
	        //_______________________________________________________________________
	        
	        // Displaying the coordinates and other info
	        
	        gl.glLoadIdentity();
	        GLUT glut = new GLUT();
	        gl.glRasterPos2f(-2.0f,1.2f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "t: "   + df1.format(new_double_coord[0]));
	        gl.glRasterPos2f(-2.0f,1.1f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "x1: "  + df2.format(new_double_coord[1]));
	        gl.glRasterPos2f(-2.0f,1f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "y1: "  + df2.format(new_double_coord[2]));
	        gl.glRasterPos2f(-2.0f,0.9f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "z1: "  + df2.format(new_double_coord[3]));
	        gl.glRasterPos2f(-2.0f,0.8f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vx1: " + df1.format(new_double_coord[4]));
	        gl.glRasterPos2f(-2.0f,0.7f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vy1: " + df1.format(new_double_coord[5]));
	        gl.glRasterPos2f(-2.0f,0.6f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vz1: " + df1.format(new_double_coord[6]));
	        gl.glRasterPos2f(-2.0f,0.5f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "x2: "  + df2.format(new_double_coord[7]));
	        gl.glRasterPos2f(-2.0f,0.4f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "y2: "  + df2.format(new_double_coord[8]));
	        gl.glRasterPos2f(-2.0f,0.3f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "z2: "  + df2.format(new_double_coord[9]));
	        gl.glRasterPos2f(-2.0f,0.2f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vx2: " + df1.format(new_double_coord[10]));
	        gl.glRasterPos2f(-2.0f,0.1f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vy2: " + df1.format(new_double_coord[11]));
	        gl.glRasterPos2f(-2.0f,0);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vz2: " + df1.format(new_double_coord[12]));
	        gl.glRasterPos2f(-2.0f,-0.1f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "x3: "  + df2.format(new_double_coord[13]));
	        gl.glRasterPos2f(-2.0f,-0.2f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "y3: "  + df2.format(new_double_coord[14]));
	        gl.glRasterPos2f(-2.0f,-0.3f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "z3: "  + df2.format(new_double_coord[15]));
	        gl.glRasterPos2f(-2.0f,-0.4f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vx3: " + df1.format(new_double_coord[16]));
	        gl.glRasterPos2f(-2.0f,-0.5f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vy3: " + df1.format(new_double_coord[17]));
	        gl.glRasterPos2f(-2.0f,-0.6f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "vz3: " + df1.format(new_double_coord[18]));
	        gl.glRasterPos2f(-2.0f,-0.7f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "dyp: " + df2.format(new_double_coord[19]));
	        gl.glRasterPos2f(-2.0f,-0.8f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "dyw: " + df2.format(new_double_coord[20]));
	        gl.glRasterPos2f(-2.0f,-0.9f);
	        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "dpw: " + df2.format(new_double_coord[21]));
	                
	        
	        
	        // Restore old state.
	        gl.glPopMatrix();
	        gl.glPopMatrix();
	        gl.glPopMatrix();
	        
	    }

	    /**
	     * Resizes the screen.
	     * 
	     * @see javax.media.opengl.GLEventListener#reshape(javax.media.opengl.GLAutoDrawable,
	     *      int, int, int, int)
	     */
	    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	        final GL2 gl = drawable.getGL().getGL2();
	        gl.glViewport(0, 0, width, height);
	        
	        
	    }

	    /**
	     * Changing devices is not supported.
	     * 
	     * @see javax.media.opengl.GLEventListener#displayChanged(javax.media.opengl.GLAutoDrawable,
	     *      boolean, boolean)
	     */
	    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	        throw new UnsupportedOperationException("Changing display is not supported.");
	    }
	    
	    
	    public void plotTrail(GL2 gl, float[] lightPos1, float[] lightColorAmbient1, float[] lightColorSpecular1) {
	    	
	    	try {
				new_line = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	gl.glPointSize(1.4f);
	    	
	        while (new_line != null) {
	        	new_string_coord = new_line.split(" ");
	        	new_double_coord[1] = Double.parseDouble(new_string_coord[1]);
	        	new_double_coord[2] = Double.parseDouble(new_string_coord[2]);
	        	new_double_coord[3] = Double.parseDouble(new_string_coord[3]);
	        	new_double_coord[7] = Double.parseDouble(new_string_coord[7]);
	        	new_double_coord[8] = Double.parseDouble(new_string_coord[8]);
	        	new_double_coord[9] = Double.parseDouble(new_string_coord[9]);
	        	new_double_coord[13] = Double.parseDouble(new_string_coord[13]);
	        	new_double_coord[14] = Double.parseDouble(new_string_coord[14]);
	        	new_double_coord[15] = Double.parseDouble(new_string_coord[15]);
	        	
	        	setLighting(gl, rgba1, lightPos1, lightColorAmbient1, lightColorSpecular1);
	        	
		        gl.glBegin(GL2.GL_POINTS);
	        	gl.glVertex3f((float) new_double_coord[1],(float)new_double_coord[2] * stretch, (float) new_double_coord[3]);
	        	gl.glEnd();
	        	
	        	setLighting(gl, rgba2, lightPos1, lightColorAmbient1, lightColorSpecular1);
	        	
	        	gl.glBegin(GL2.GL_POINTS);
	        	gl.glVertex3f((float) new_double_coord[7],(float)new_double_coord[8] * stretch, (float) new_double_coord[9]);
	        	gl.glEnd();
	        	
	        	setLighting(gl, rgba3, lightPos1, lightColorAmbient1, lightColorSpecular1);
	        	
	        	gl.glBegin(GL2.GL_POINTS);
	        	gl.glVertex3f((float) new_double_coord[13],(float)new_double_coord[14] * stretch, (float) new_double_coord[15]);
	        	gl.glEnd();
	        	
	        	try {
					new_line = br2.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        try {
				file2.close();
				file2 = new FileReader(dir + file_name + ".txt");
				br2 = new BufferedReader(file2);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    }
	    
	    private void setCamera(GL2 gl, GLU glu, float distance) {
	        // Change to projection matrix.
	        gl.glMatrixMode(GL2.GL_PROJECTION);
	        gl.glLoadIdentity();

	        // Perspective.
	        float widthHeightRatio = (float) getWidth() / (float) getHeight();
	        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
	        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

	        // Change back to model view matrix.
	        gl.glMatrixMode(GL2.GL_MODELVIEW);
	        gl.glLoadIdentity();
	    }
	    
	    private void setLighting(GL2 gl, float[] rgba, 
	    								 float[] lightPos1, 
	    								 float[] lightColorAmbient1, 
	    								 float[] lightColorSpecular1) {
	    	
	    	// Set light parameters.
	        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos1, 0);
	        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient1, 0);
	        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular1, 0);

	        // Enable lighting in GL.
	        gl.glEnable(GL2.GL_LIGHT1);
	        gl.glEnable(GL2.GL_LIGHTING);

	        // Set material properties.
	        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
	        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
	        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.2f);
	    	
	    }

	    private void drawVelocityVector(GL2 gl, float x, float y, float z) {
	    	
	    	// Velocity Vector (Optional)
	        gl.glBegin(GL2.GL_LINES);
	        gl.glVertex3f(0.0f, 0.0f, 0.0f);
	        gl.glVertex3f( x, y, z);
	        gl.glEnd();
	        
	        gl.glPointSize(6.0f);
	        gl.glBegin (GL2.GL_POINTS);
	        gl.glVertex3f( x, y, z);
	        gl.glEnd ();
	    	
	    }

	    private void drawSphere(GLU glu1) {
	    	
	    	// Draw sphere (possible styles: FILL, LINE, POINT).
	        GLUquadric sphere1 = glu.gluNewQuadric();
	        glu.gluQuadricDrawStyle(sphere1, GLU.GLU_FILL);
	        glu.gluQuadricNormals(sphere1, GLU.GLU_FLAT);
	        glu.gluQuadricOrientation(sphere1, GLU.GLU_OUTSIDE);
	        glu.gluSphere(sphere1, radius, slices, stacks);
	        glu.gluDeleteQuadric(sphere1);	
	    }


		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
	


}

