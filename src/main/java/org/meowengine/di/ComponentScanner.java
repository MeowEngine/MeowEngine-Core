package org.meowengine.di;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashSet;
import java.util.stream.Collectors;

public class ComponentScanner {

    public static void performScan(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        var set = reflections.getSubTypesOf(Object.class)
                .stream().filter(aClass -> aClass.isAnnotationPresent(Component.class));

        set.map(aClass -> {
            Object obj = aClass.getDeclaredConstructor()
        })

    }
}
