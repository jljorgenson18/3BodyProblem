The Three Body Problem
-----------------------
Author: Jesse Jorgenson


------------------------------------------------------------------------------------------

TO DO:
To avoid exporting the entire workspace, I exported the user libraries needed to run JOGL. 
The User library files are included in the project under the UserLibraries folder, but you 
still need to import them and them to your workspace. This is actually a fairly simple process.

To add the user libraries, do the following

1. Go to Eclipse ("Window" if on Windows) -> Preferences -> Java -> Build Path -> User Libraries 
2. Click on "Import" and then click "Browser."
3. Navigate to your current Eclipse WorkSpace and go to the UserLibraries Folder
4. Click the Library that matches your Operating System (e.g. jogl-Mac.userlibraries for MAC)
5. Go to your package explorer and right click on your main project directory
6. Click on properties and then go to "Java Build Path"
7. Click "libraries" on the top menu and then click "add library" on the right
8. In the resulting pop-up, choose "User Library" and then hit "next."
9. Check the checkbox next to the user library you just imported and then click finish
10. Run mainGraphics.java to see if JOGL is working

NOTE:
I currently do not have each of the libraries setup for various operating systems. If your 
O/S system is not supported but the jar files are available under the local "Jogl" directory,
feel free to add the correct userlibrary for your O/S by going to 

https://sites.google.com/site/justinscsstuff/jogl-tutorial-1 

and following the directions to get the user library setup. Please try to keep everything local
in order to easily deploy the project and feel free to help out and add it to Github. Simply
export the user library and then add it to the UserLibraries folder inside of the project.

If you have any questions or need any assistance, feel free to email me at jljorgenson18@gmail.com

------------------------------------------------------------------------------------------

This is the Three Body Problem project designed to display 3D graphics of Newtonian Gravity using
the Parker-Sochacki Method of Differential Equations. The way this program works is by 
- Inserting initial conditions into a threeBody3d object (inside of MainFrame.java)
- Having an algorithm generate all of the coordinates over time into a text file (via threeBody3d.java)
- Reading in the text file and then parsing the coordinates to display over JOGL (via threeBodyGraphics.java)
- Then outputting the frame for the 3D graphics (from a MainFrame object referenced in mainGraphics.java)

This was my first big program so I am still working to update and clean up the code. In general, this is 
how the program works.

Initial Conditions ----(threeBody3d.java)---> Data/YourData.txt ---(threeBodyGraphics.java)---
																							  |
																							  |
																							  |
							  Output/mainGraphics.java<---(MainFrame.java)----GLCanvas/JFrame<-
							  
