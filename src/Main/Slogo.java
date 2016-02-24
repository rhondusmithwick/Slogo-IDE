//package Main;
//
//import Controller.TurtleController;
//
//import java.util.Observable;
//import java.util.Observer;
//import Controller.Controller;
//import javafx.beans.property.Property;
//import javafx.beans.property.SimpleStringProperty;
//import view.ViewInt;
//import view.View;
//
///**
// * Created by rhondusmithwick on 2/23/16.
// *
// * @author Rhondu Smithwick
// */
//public class Slogo  {
//    private final Controller controller = new TurtleController();
//
////    private final ViewInt view = new View();
//
//    public Slogo() {
//        bindProperties();
//    }
//
//   private void bindProperties() {
//       SimpleStringProperty[] controllerProperties = controller.getProperties();
//       SimpleStringProperty[] viewProperties = view.getProperties();
//       for (SimpleStringProperty controllerProperty: controllerProperties) {
//           for (SimpleStringProperty viewProperty: viewProperties) {
//               String cName = controllerProperty.getName();
//               String vName = viewProperty.getName();
//               if (cName.equals(vName)) {
//                   controllerProperty.bindBidirectional(viewProperty);
//                   break;
//               }
//           }
//       }
//   }
//}
