//package Main;
//
//import Controller.TurtleController;
//
//import Controller.Controller;
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
//          findTwin(controllerProperty, viewProperties);
//       }
//   }
//
//    private boolean findTwin(SimpleStringProperty controllerProperty,
//                          SimpleStringProperty[] viewProperties) {
//        String cName = controllerProperty.getName();
//        for (SimpleStringProperty viewProperty: viewProperties) {
//            String vName = viewProperty.getName();
//            if (cName.equals(vName)) {
//                controllerProperty.bindBidirectional(viewProperty);
//                return true;
//            }
//        }
//        return false;
//    }
//}
