package model.deprecated;
//package Model.Deprecated;
//
//import Model.TreeNode.TurtleCommandNode;
//import Model.Turtle.Turtle;
//import javafx.scene.image.Image;
//
///**
// * Created by rhondusmithwick on 2/23/16.
// *
// * @author Rhondu Smithwick
// */
//public class SetImage extends TurtleCommandNode {
//
//    private final Image image;
//
//    public SetImage(Turtle myTurtle, Image image) {
//        super(myTurtle);
//        this.image = image;
//    }
//
//    @Override
//    public double execute() {
//        try {
//            getTurtle().getTurtleProperties().setImage(image);
//            return 1;
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//}
