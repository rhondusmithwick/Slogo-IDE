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
