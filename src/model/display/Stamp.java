package model.display;

import controller.slogoparser.ExpressionTree;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import maps.IndexMap;
import model.treenode.TurtleCommandNode;
import model.turtle.Turtle;
import model.turtle.TurtleDefaults;


public class Stamp extends TurtleCommandNode {

    private IndexMap imageMap;

    @Override
    public double turtleExecute(Turtle turtle) {
        String imageFilePath = turtle.getTurtleProperties().getImage();
        ImageView imageView = makeImageView(turtle);
        Platform.runLater(() -> turtle.addStamp(imageView));
        return imageMap.getIndex(imageFilePath);
    }

    private ImageView makeImageView(Turtle turtle) {
        Image image = turtle.getTurtleProperties().getImageView().getImage();
        ImageView imageView = new ImageView(image);
        Point2D location = turtle.getTurtleProperties().getLocation();
        imageView.setX(location.getX());
        imageView.setY(location.getY());
        double imageDim = TurtleDefaults.IMG_DIM.getDouble();
        imageView.setFitHeight(imageDim);
        imageView.setFitWidth(imageDim);
        return imageView;
    }

    @Override
    public void handleSpecific(ExpressionTree tree) {
        super.handleSpecific(tree);
        imageMap = tree.getImageMap();
    }
}
