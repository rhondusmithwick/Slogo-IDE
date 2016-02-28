//package Model;
//
///**
// * Created by rhondusmithwick on 2/23/16.
// *
// * @author Rhondu Smithwick
// */
//public class SetPenColor extends TurtleCommandNode {
//
//    private final String penColor;
//
//    public SetPenColor(turtle myTurtle, String penColor) {
//        super(myTurtle);
//        this.penColor = penColor;
//    }
//
//    @Override
//    public double execute() {
//        try {
//            getTurtle().getTurtleProperties().setPenColor(penColor);
//            return 1;
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//}
