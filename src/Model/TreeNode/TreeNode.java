package Model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by rhondusmithwick on 2/25/16.
 *
 * @author Rhondu Smithwick
 */
public abstract class TreeNode implements Delayed {
    private long expiryTime =TimeUnit.NANOSECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = expiryTime -TimeUnit.NANOSECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return diff;
    }


    public void setDelay(long l) {
        this.expiryTime = l;
    }

    @Override
    public int compareTo(Delayed o) {
        long thisDelay = getDelay(TimeUnit.MILLISECONDS);
        long oDelay = o.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(thisDelay, oDelay);
    }

    private final DelayQueue<TreeNode> children = new DelayQueue<>();

    public double getValue() {
        return 0.0;
    }

    public void addChild(TreeNode n) {
        children.add(n);
    }

    protected DelayQueue<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getChild() {
        TreeNode child = null;
        try {
            child = getChildren().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return child;
    }

    public boolean needsMoreChildren() {
        int numChildren = children.size();
        return (numChildren < getNumChildrenRequired());
    }

    protected int getNumChildrenRequired() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getClass().getSimpleName(), children);
    }
}
