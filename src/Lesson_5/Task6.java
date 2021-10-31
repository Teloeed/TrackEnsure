package Lesson_5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Task6 {
    public static void main(String[] args){
        String line = "{{}}()[]";
        System.out.println(check(line));

    }
    public static boolean check(String line){
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            switch (c) {
                case '{' -> stack.push('}');
                case '(' -> stack.push(')');
                case '[' -> stack.push(']');
                case '}', ')', ']' -> {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char t = stack.pop();
                    if (t != c) return false;
                }
            }

        }
        return stack.isEmpty();
    }
}
