package org.geek;

import java.lang.reflect.Constructor;

public class Args {
    public static <T> T parse(Class<T> optionClass, String... args) {
        Constructor<?> constructor = optionClass.getDeclaredConstructors()[0];
        try {
            Object obj = constructor.newInstance(true);
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
