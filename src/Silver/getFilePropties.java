package Silver;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class getFilePropties {
    public static String[] getChildrenName(String path) {
        File file = new File(path);
        return file.list();
    }

    public static Icon getSingleIcon(String path) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getSystemIcon(new File(path));
    }

    public static List<Icon> getAllIcon(String path) {//获取目录下的所有小图标
        List<Icon> icons = new ArrayList<>();
        int counter = 0;
        if (Objects.equals(path, "HOME")) {
            List<File> Disks = new ArrayList<>(Arrays.asList(File.listRoots()));
            for (File disk : Disks) {
                FileSystemView fsv = FileSystemView.getFileSystemView();
                File file = new File(disk.getName() + "\\");
                icons.add(fsv.getSystemIcon(file));
            }
        } else {
            File file = new File(path);
            File[] files = file.listFiles();
            if (files != null) {
                for (File a : files) {
                    if (a != null && a.exists()) {
                        FileSystemView fsv = FileSystemView.getFileSystemView();
                        icons.add(fsv.getSystemIcon(a));
                    }
                }
            }
        }
        return icons;
    }

    public static void main(String[] args) {
       File dic =  new File("C:/");
        for (File file : Objects.requireNonNull(dic.listFiles())) {
            System.out.println(file.getName());
        }
    }
}
