package org.geek;

import java.util.List;

public class StringOptionParse implements OptionParse{
    @Override
    public Object parse(List<String> arguments, Option option) {
        Object value;
        int index = arguments.indexOf("-" + option.value());
        value = arguments.get(index + 1);
        return value;
    }
}
