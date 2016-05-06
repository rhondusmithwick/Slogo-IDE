# Estimation

I believe implementing the front end extension should take at most 2 hours. I will probably change at least 2 files  and add 1. I will need to add a new class that displays all the images for the turtles, and this class will need to be instantiated by clicking on a button in the toolbar. Adding this button means I'll need to change one of the SubBar classes in the toolbar, and since the class I will be creating will likely require information not already contained in the toolbar, I will need to modify the View class to pass in the needed classes when instantiating the SubBar class. 

#Review 

Completing this new feature took me between 1-2 hours. I ended up adding a couple more classes and changing several more classes than anticipated. 

One class I added was the PreviewUnitWithEditable class, which displays the image and name for a single turtle. While I could have just created the displays for all turtles within my TurtleImageSelector class, I decided to add the PreviewUnitWithEditable class for two reasons. First, I wanted the code to be as modular as possible, and so having a separate class that's solely responsible for displaying the image and name of a single turtle helped me achieve this goal. Second, I had already written the PreviewUnitWithEditable class for VOOGAsalad, and it seemed like a good idea to reuse this class since it already did exactly what I needed it to do. 

Another class I ended up adding was the IEditableElement interface, which was needed because each PreviewUnitWithEditable has an instance of an IEditableElement. Again, since this interface was designed to be reusable in contexts outside of VOOGAsalad, I was able to easily incorporate it into my extension by simply making the Turtle class implement IEditableElement. 

As expected, I ended up modifying the TopBar class to add a new button that instantiates the TurtleImageSelector class, a pop up that displays the images and names of all created turtles. I also had to modify the View class to pass in a map containing all the turtles to the TopBar so that this map could eventually be passed to the TurtleImageSelector, where it will be used to retrieve the image views of each created turtle.

  