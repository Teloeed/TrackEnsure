package Lesson_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Task2 {
    public static void main(String[] args){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            //стек
            Deque<Integer> temp = new ArrayDeque<>() {};
            int line = Integer.parseInt(br.readLine());
            while(line != 0){
                temp.add(line % 10);
                line = line / 10;
            }
            temp.forEach(System.out::print);

    } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
