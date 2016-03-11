package model.display;

import controller.slogoparser.ExpressionTree;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import maps.IndexMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;

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
        Predicate<Entry<Integer, String>> isImage = (e) -> (Objects.equals(imageFilePath, e.getValue()));
        return imageMap.getIndexMap().getEntrySet().parallelStream()
                .filter(isImage)
                .map(Entry::getKey).findFirst().orElse(0);
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
