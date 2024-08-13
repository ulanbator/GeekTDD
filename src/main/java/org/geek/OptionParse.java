package org.geek;

import java.util.List;

public interface OptionParse {

    /**
     *
     * @param arguments
     * @param option
     * @return
     */
    Object parse(List<String> arguments, Option option);
}
