# View API Changes
 * Both methods in the original View API ended up not being in our final API. This is because once be learned about observables and simple string properties we realized we could just pass input and and errors using these objects rather than having specific methods in the view to pass them.
 * the bindSize method was added so that the view could be dynamically resized as the application window was resized, rather than having the views dimensions hard coded
 * the getTitle method was added for using multiple workspaces, so that each table could be differentiated by a title string determined by the view
 * the getInnerGroup method was added so that we could add the turtle's graphical group from the controller into the group that defined the turtle area so that the turtle could be displayed visually in the view
 * the getGroup method was added as we needed to be able to add each view to a tab that was defined in the Slogo class. To do this we needed the slogo class to be able to access the group that contained all the visual elements of the view, and add it to a tab
 * The getProperties method was added so that we could use simplestring properties. this method is also needed by slogo so that the properties defined in the view can be bound with properties defined by the controller.
 
