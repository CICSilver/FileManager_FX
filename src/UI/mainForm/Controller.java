package UI.mainForm;

import Silver.FileTreeItem;
import Silver.getFilePropties;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.*;

public class Controller {
    public TextField currentPath;
    public TreeView<String> tree;
    public void setTree() {
        File sys = new File(System.getenv("COMPUTERNAME"));
        TreeItem<String> rootItem = new TreeItem<>(sys.getName());
        for (File file : File.listRoots()) {
            FileTreeItem rootsitem = new FileTreeItem(file.getPath());
            rootItem.getChildren().add(rootsitem);
        }
        tree.setRoot(rootItem);
    }

    public void setCurrentPath() {
        TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
        if(selectedItem.getValue().equals(System.getenv("COMPUTERNAME"))) {
            return;
        }
        currentPath.setText(FileTreeItem.getAbsolutePath(selectedItem));
    }

    public void openURL() {
        File file = new File(currentPath.getText());
        if(file.exists()) {
            TreeItem<String> selectedItem = new TreeItem<>(file.getName());
        }
    }

    public static void main(String[] args) {
        File[] dircts = File.listRoots();
        System.out.println(dircts[0].getPath());
    }

}
