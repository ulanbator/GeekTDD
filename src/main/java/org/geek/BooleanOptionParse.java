package org.geek;

import java.util.List;

public class BooleanOptionParse implements OptionParse{
    @Override
    public Object parse(List<String> arguments, Option option) {
        Object value;
        value = arguments.contains("-" + option.value());
        return value;
    }
}
