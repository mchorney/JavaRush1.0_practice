package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров,
 - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("G:\\IdeaProjects\\JavaRushHomeWork\\JavaRushHomeWork\\src\\com\\javarush\\test\\level35\\lesson10\\bonus01\\data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animals = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/")) pathToAnimals += "/";
        final File f = new File(pathToAnimals);
        File[] files = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".class");
            }
        });
        ClassLoader classLoader;
        for (final File file : files) {
            classLoader = new ClassLoader(){
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    try {
                        byte[] buff = Files.readAllBytes(file.toPath());
                        return defineClass(null, buff, 0, buff.length);
                    } catch (IOException e) {
                        return super.findClass(name);
                    }
                }
            };
            String shortName = file.getName().substring(0, file.getName().length() - 6);
            try {
                Class clazz = classLoader.loadClass(shortName);
                Class[] interfaces = clazz.getInterfaces();
                for (Class cl : interfaces) {
                    if (cl.equals(Animal.class)){
                        Constructor[] constructors = clazz.getConstructors();
                        for (Constructor constr : constructors) {
                            if (!Modifier.isAbstract(constr.getModifiers())
                                    && Modifier.isPublic(constr.getModifiers())
                                    && constr.getParameterCount() == 0){
                                animals.add((Animal) clazz.newInstance());
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return animals;
    }
}
