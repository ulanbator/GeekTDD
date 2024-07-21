package org.geek;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgTest {

    // -l -p 8080 -d /usr/logs

    // happy path
    @Test
    public void testSingleBooleanExists() {
        BooleanOptions options = Args.parse(BooleanOptions.class, "-l");
        assertTrue(options.logging());
    }

    @Test
    public void testSingleBooleanNotExists() {
        BooleanOptions options = Args.parse(BooleanOptions.class);
        assertFalse(options.logging());
    }

    @Test
    public void testSingleIntegerExists() {
        IntegerOptions options = Args.parse(IntegerOptions.class, "-p", "8080");
        assertEquals(8080, options.port());
    }

    @Test
    public void testSingleStringExists() {
        StringOptions options = Args.parse(StringOptions.class, "-d", "/user/logs");
        assertEquals("/user/logs", options.dir());
    }


    @Test
    public void testSuccess1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.dir());
    }
    // sad path
    // TODO 布尔类型 后面加参数 后面加非布尔参数
    // TODO int类型 后面加非int参数
    // TODO 字符串类型 后面加非字符串参数

    // 默认值
    // TODO 布尔
    // TODO int
    // TODO 字符串




    // -g this is a list -d 1 2 -3 5
    @Disabled
    public void testSuccess2() {
        OptionsList options = Args.parse(OptionsList.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[]{1, 2}, options.decimals());
    }

    static record StringOptions(@Option("d") String dir) {

    }

    static record IntegerOptions(@Option("p") int port) {

    }

    static record BooleanOptions(@Option("l") boolean logging) {

    }

    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {

    }

    static record OptionsList(@Option("g") String[] group, @Option("d") int[] decimals) {

    }


}
