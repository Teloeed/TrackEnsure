package Lesson_5;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException{

        List<String> list = new ArrayList<>();
//а какая разница?
        try(BufferedReader reader = new BufferedReader(new FileReader("Test.txt"))){
            while (reader.ready()){
                list.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(PrintWriter writer = new PrintWriter(new FileWriter("Test.txt"))){
            for(int i = list.size()-1; i > -1; i--){
                writer.println(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
