package org.geek;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class Args {
    public static <T> T parse(Class<T> optionClass, String... args) {
        try {
            Constructor<?> constructor = optionClass.getDeclaredConstructors()[0];
            Parameter parameter = constructor.getParameters()[0];
            Option option = parameter.getAnnotation(Option.class);
            List<String> arguments = Arrays.asList(args);
            Object obj = constructor.newInstance(arguments.contains("-" + option.value()));
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
