package Lesson_5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Task4 {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>(List.of(-3, 2, 7, -5, 9, 3, -4, -9, 4));
        list.forEach(System.out::print);
        System.out.println();
        int p = 0;
        int count = 0;
        while(count < list.size()){
            int temp = list.get(p);
            if (temp < 0){
                list.remove(p);
                list.add(temp);
            }
            else p++;
            count++;
        }
        list.forEach(System.out::print);
    }
}
