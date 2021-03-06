package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (MyString), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args)
    {
        Human child1 = new Human("Bob", true, 18);
        Human child3 = new Human("Tom", true, 10);
        Human child2 = new Human("Anna", false, 15);
        List<Human> children = new ArrayList<Human>();
        children.add(child1);
        children.add(child2);
        children.add(child3);

        Human father = new Human("Rob", true, 48, children);
        Human mother = new Human("Diana", false, 45, children);
        List<Human> childF = new ArrayList<Human>();
        List<Human> childM = new ArrayList<Human>();
        childF.add(father);
        childM.add(mother);

        Human dedF = new Human("Ron", true, 88, childF);
        Human dedM = new Human("Billy", true, 89, childM);
        Human grannyF = new Human("Rosa", false, 85, childF);
        Human grannyM = new Human("Linda", false, 86, childM);

        System.out.println(dedF);
        System.out.println(dedM);
        System.out.println(grannyF);
        System.out.println(grannyM);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
    }

    public static class Human
    {
        private  String name;
        private boolean sex;
        private int age;
        List<Human> children = new ArrayList<Human>();

        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, List<Human> children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
