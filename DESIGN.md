# slogo Design
> This is the link to the Design Assignment
 [DESIGN](http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/part1.php)

# People
* Rhondu Smithwick- rss44
* Stephen Kwok - smk44
* Cali Nelson - can18
* Jonathan Ma - dm269

# Introduction
The goal of this project is to closely integrate frontend and backend APIs, allowing
different teams to easily communicate to reach a common goal. There must be
flexibility in terms of how the turtle executes its commands, and it must be easy for
the frontend and backend to communicate changes to the turtle. The frontend will be
closed to the backend, only passing a turtle object to the backend. The backend will
open up only a facilitator class to the frontend. All of its other classes will be closed.

# Design Overview
* The project is divided up between the View (frontend) and the Model (backend).
* The View (see User Interface section) will be focused on a Turtle class and a
GUI class.
  * The Turtle class will be our class for holding the information specifically relating to a turtle
  IE setPenColor, setFill, etc... It is noted that the turtle is passed to the backend, which will run the user input
command on the turtle.
  * The GUI will contain the actual user interface and will allow the user to input text.
* The Model (see Design Consideration Section for more discussion on this) will be focused around the
Command interface. For example, the interface contains a single method that is common throughout all commands.
```java
public interface Command {

  double execute();

}
```
  * When the View (or perhaps the class that facilitates contact between the View and Model) is
  ready to do a command, it calls the execute method on the turtle. One example command is:
```java
public class Forward extends TurtleCommand {

  private final double distance;

  public Forward(Turtle myTurtle, double distance) {
    super(myTurtle);
    this.distance = distance;
  }

  public double execute() {
    Point2D directionVector = getDirectionVector();
    double newX = getTurtle().getX() + (distance * unitDirVector.getX());
    double newY = getTurtle().getY() + (distance * unitDirVector.getY());
    Command goto = new GoTo(getTurtle(), newX, newY);
    goto.execute();
    return distance;
  }

  private Point2D getDirectionVector() {
    // getHeading returns the Point on the edge of the screen the turtle is currently facing
    Point2D heading = getTurtle().getHeading();
    double dirVectorX = heading.getX() - getTurtle().getX();
    double dirVectorY = heading.getY() - getTurtle().getY();
    double dirVectorDistance = Math.sqrt(dirVectorX * dirVectorX + dirVectorY * dirVectorY);
    return new Point2D (dirVectorX / dirVectorDistance, dirVectorY / dirVectorDistance);
  }
}
```
  * Class hierarchy: The abstract TurtleCommand class implements Command and includes additional methods
for getting and setting a turtle. The Forward class then extends this TurtleCommand class and contains code for an
actual Command.
* These are the steps to the the View and Model's interaction.
1. The user inputs text, which the View saves and passes to the backend.
2. The Model parses the text, creates a List of commands based on it, and gives this
list of commands back to the View.
3. Finally, the View executes this list of commands. It will also display their return value on the GUI as required.


# User Interface
* The user will enter commands into a text box in the userinterface.  They can enter multiple commands at once, until they press the execute button which will execute the entered commands. The user can continue to enter commands and press execute until they are finished.  The results of the turtle commands will be shown in the middle of the screen. At the top will be a menu bar with various buttons and combo boxes that allow the user to change different stylistic things about the simulation, including choosing a turtle image, chosing colors, and accessing a help document. There will be another area to display the result of any errors or exceptions; how exactly these errors will be displayed is not yet set.  Finally there will be an area below the turtle display that will show any currently defined user commands, any currently defined variables, and the past execution history for the simulation.


![This is cool, too bad you can't see it](DesignImages/UserInt.jpg "User Interface Design")
