Design Considerations
----------------------

###Interface vs Abstract Class

> Our primary discussion for backend design was centered around how our code could most efficiently reflect the commands that the SLogo IDE takes in.
  We had two main ideas as two how we could do this:

> - Have an abstract class called **Command** that contains global variables like **double degrees** and **double distance**, along with an abstract
    method called **execute** that would execute the command on the Turtle. We would then make each unique command as a subclass of the **Command**
    class. For instance, **Command** would look something like this:

    ```java
      public abstract Class Command {
        private double degrees;
        private double distance;
        private Turtle turtle;

        public Command(Turtle turtle, double distance, double degrees) {
          super(turtle, distance, degrees);
        }

        public void execute(Turtle turtle) {
          //insert some code
        }
      }
    ```

> - Have an interface called **Command** that would currently only have execute as the sole method in the interface. Then, each class that implements
    the interface would need to have an execute method. Additionally, each class would have to have its own "special" global variables. For example,
    the **Forward** class would have the global variable of **double distance**, but not **double degrees**, while the **Right** class would have
    **double degrees**, but not **double distance**.

> Pros and Cons

> - One of the pros of using a single abstract class would be code consolidation. There would be less code to worry about for each subclass, since most
    of the work could be handled in the super class. This could potentially mean easier code management and cleaner looking code.
> - However, one the major cons of using a single abstract class is the fact that a lot of the subclasses wouldn't utilize the variables in the Abstract
    class at all. For instance, the **Forward** class doesn't care about the **degrees** variable at all and the **Right** class wouldn't care about the
    **distance** variable at all either. This seems like a big waste of code.
> - One of the pros of using the interface is that it would help with API design, since all of our primary methods would be held within that interface.
    Additionally, it is extremely useful for code management and we can easily create new classes from an interface when necessary. It also takes care of
    the need to specialize the different command classes, since each command class will have it's own unique global variables. This way, we won't have
    the **Forward** class with a **degrees** variable when it doesn't really need one.
> - The main con against using an interface is that currently, the only method in the interface is **execute()**, so it seems like the interface doesn't
    really do much. But, our team does believe that the interface gives us more options for future extension and makes the overall code more flexible.
    More than likely, there will be more methods added to the interface by the end of the project.

###Parsing of User Input

> One of the other important design considerations that the team really discussed was where the parsing of user input would take place. There were two
  options, each with their own reasons:

> - We could place the parsing in the backend, which is where parsing would normally seem to take place.
> - We could place the parsing in the frontend, which would make more sense, as the backend could just process parsed data to make the next move.

> Backend Parsing

> - There doesn't really seem to be a major pro for placing the parsing specifically in the backend. One interesting point that was brought up was that
    backend parsing would require the frontend to just pass the user inputted string to the backend and then have the backend handle all of the data.
    This would keep the user inputted data all in one place and it would be easy to keep track of it.
> - The main con against backend parsing was that it would add another section of functionality to an already heavy workload for the backend coders. It
    also seemed to potentially make the frontend significantly easier.

> Frontend Parsing

> - The pro for frontend parsing was that it would make the backend code less dense and seemingly help split up the work more evenly among the team. It
    also seemed to make more sense for the frontend to grab the user input and immediately parse the input and pass that data to the backend.
> - The con against this is that it seems conventionally, an action like parsing should be handled in backend. It would also split up the data handling
    between two groups, which may complicate things. 
