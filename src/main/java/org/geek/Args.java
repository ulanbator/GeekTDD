package org.geek;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Args {

    private static Map<Class<?>, OptionParse> map = Map.of(
            boolean.class, new BooleanOptionParse(),
            int.class, new IntegerOptionParse(),
            String.class, new StringOptionParse()
    );

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
        OptionParse optionParse = map.get(parameter.getType());
        return optionParse.parse(arguments, option);
    }

}
