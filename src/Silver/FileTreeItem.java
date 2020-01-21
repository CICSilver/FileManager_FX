package Silver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.*;

public class FileTreeItem extends TreeItem<String> {
    private boolean isLeaf;
    private boolean isFirstTimeChildren = true;
    private  boolean  isFirstTimeLeaf = true;
    /**
     * Creates an empty TreeItem.
     */
    public FileTreeItem(@NotNull String fileName) {
        super(fileName);
    }

    /**
     * The children of this TreeItem. This method is called frequently, and
     * it is therefore recommended that the returned list be cached by
     * any TreeItem implementations.
     *
     * @return a list that contains the child TreeItems belonging to the TreeItem.
     */
    @Override
    public ObservableList<TreeItem<String>> getChildren() {
        if(isFirstTimeChildren) {
            isFirstTimeChildren = false;
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    /**
     * A TreeItem is a leaf if it has no children. The isLeaf method may of
     * course be overridden by subclasses to support alternate means of defining
     * how a TreeItem may be a leaf, but the general premise is the same: a
     * leaf can not be expanded by the user, and as such will not show a
     * disclosure node or respond to expansion requests.
     */
    @Override
    public boolean isLeaf() {
        if(isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            File f = new File(getAbsolutePath(this));
            isLeaf = f.isFile();
        }
        return isLeaf;
    }

    public ObservableList<TreeItem<String>> buildChildren(TreeItem<String> item) {
        File curFile = new File(getAbsolutePath(item));
        //System.out.println(item.getValue());
        if(curFile.isDirectory()) {
            File[] files = curFile.listFiles();
            if (files != null) {
                ObservableList<TreeItem<String>> children = FXCollections.observableArrayList();
                for (File file : files) {
                    //System.out.println(file.getName());
                    children.add(new FileTreeItem(file.getName()));
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }


    public static String getAbsolutePath(TreeItem<String> item) {
        String sys = "DESKTOP-CICSILV";
        StringBuilder pathString = new StringBuilder(item.getValue());
        while (item.getParent().getValue()!=null && !Objects.equals(item.getParent().getValue(), sys)) {
            pathString.insert(0, item.getParent().getValue() + "\\");
            item = item.getParent();
        }
        return pathString.toString();
    }

    public static void main(String[] args) {
        File f = new File("Intel");
        System.out.println(f.getParent());
    }

}
