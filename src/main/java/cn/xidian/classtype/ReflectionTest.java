package cn.xidian.classtype;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author chenmiao
 * @version 1.0.0
 * @title: ReflectionTest
 * @description: Practice to reflection of java
 * @date 2019-11-09 14:27
 */
public class ReflectionTest {

    static class Animal {
        protected String name;

        protected String getName() {
            return name;
        }
    }

    static class Bird extends Animal {
        private String kind;
        protected String legs;
        public String eyes;

        private String getKind() {
            return kind;
        }

        public void fly() {

        }
    }

    public static void main(String[] args) {
        Class<Bird> bird = Bird.class;
        System.out.println(Arrays.stream(bird.getDeclaredFields()).map(field -> field.getName()).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(bird.getFields()).map(field -> field.getName()).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(bird.getDeclaredMethods()).map(method -> method.getName()).collect(Collectors.joining(",")));
    }
}
