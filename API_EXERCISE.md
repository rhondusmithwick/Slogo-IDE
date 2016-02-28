# slogo API_Exercise
> This is the link to the Exercise Description: [API_Exercise](http://www.cs.duke.edu/courses/compsci308/spring16/classwork/06_slogo_api/)

# People
Rhondu Smithwick- rss44
Stephen Kwok - smk44
Cali Nelson - can18
Jonathan Ma - dm269

# API Critique
We chose to critique Cali's Cell Society Team 13.
* Almost everything abstract is internal because that's something you're going to extend/ implement in future classes.
* Getters/ setters  
  * Public are usually used externally
  * Protected/ Package Specific it's internal/ for the subclasses
* Jonathan brings up a good point that external is also internal usually
* One of the challenges was thinking of the different changes Duvall threw at us. The major thing for this was the GridShapes.
* API Description
  * Configuration had external in its Rules object and different parameters to pass to different objects. Additionally, anything needed to save was external IE passing to and from the XML parser. Internal it had between the XML parser and the class to initialize the grid.
  * Simulation was external in that it passed the grid to visualization. Internal was working with the cells themselves.
  * Visualization had external access to the grid and internal access to the graph. Setting parameters was also some external access.

### Slogo architecture
1. Parsing needs to take place when the user clicks run. It then grabs all the text and then throws it at the backend/backend mediator. Parser is a part of the frontend because it involves communication between the frontend and the backend.

2. The result of the parsing is a series of commands. The backend receives the result.

3. Backend catches the errors, frontend displays it.

4. Commands know their parameters. They know it when they are called. They get it threw the parser.

5. Backend gives back the turtle to the frontend.

### Create Your APIs
* Model
  * Command interface
    * void execute (Turtle turtle)
      * will execute the given command on this turtle
    *
* View
  * Turtle internal
