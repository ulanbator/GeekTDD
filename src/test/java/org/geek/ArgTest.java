package org.geek;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgTest {

    // -l -p 8080 -d /usr/logs

    // happy path
    // TODO 单独布尔类型
    @Test
    public void testSingleBooleanExists() {
        BooleanOptions options = Args.parse(BooleanOptions.class, "-l");
        assertTrue(options.logging());
    }

    // TODO 单独布尔类型 参数不存在
    @Test
    public void testSingleBooleanNotExists() {
        BooleanOptions options = Args.parse(BooleanOptions.class);
        assertFalse(options.logging());
    }

    // TODO 单独int
    @Test

    // TODO 单独字符串
    // TODO 所有类型

    // sad path
    // TODO 布尔类型 后面加参数 后面加非布尔参数
    // TODO int类型 后面加非int参数
    // TODO 字符串类型 后面加非字符串参数

    // 默认值
    // TODO 布尔
    // TODO int
    // TODO 字符串


    @Disabled
    public void testSuccess1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.dir());
    }

    // -g this is a list -d 1 2 -3 5
    @Disabled
    public void testSuccess2() {
        OptionsList options = Args.parse(OptionsList.class, "-g","this","is","a","list","-d","1","2","-3","5");
        assertArrayEquals(new String[] {"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[] {1, 2}, options.decimals());
    }

    static record IntegerOptions(@Option("p") int port) {

    }

    static record BooleanOptions(@Option("l") boolean logging) {

    }

    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {

    }

    static record OptionsList(@Option("g") String[] group, @Option("d") int[] decimals){

    }


}
