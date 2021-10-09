package Lesson_4.Home;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindFiles {

    public static void main(String[] args) {
        try {
            for(String i : getFileTree("C:\\")){
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static List<String> getFileTree(String root) throws IOException {
        File folder = new File(root);
        return new ArrayList<>(search(folder));
    }
    private static List<String> search(File folder){
        List<String> result = new ArrayList<>();
        for (File temp : Objects.requireNonNull(folder.listFiles())){
            if (temp.isFile()) result.add(temp.getAbsolutePath());
            else if (temp.isDirectory()) result.addAll(search(temp));
        }
        return result;
    }


}