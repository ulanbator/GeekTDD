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
            List<String> arguments = Arrays.asList(args);
            Object[] array = Arrays.stream(constructor.getParameters()).map(p -> parseObject(p, arguments)).toArray();
            return (T) constructor.newInstance(array);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseObject(Parameter parameter, List<String> arguments) {
        Option option = parameter.getAnnotation(Option.class);
        Object value = null;
        if(parameter.getType().equals(boolean.class)) {
            value = arguments.contains("-" + option.value());
        }
        if(parameter.getType().equals(int.class)){
            int index = arguments.indexOf("-" + option.value());
            value = Integer.parseInt(arguments.get(index + 1));
        }
        if(parameter.getType().equals(String.class)){
            int index = arguments.indexOf("-" + option.value());
            value = arguments.get(index + 1);
        }
        return value;
    }
}
