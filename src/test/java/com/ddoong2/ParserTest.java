package com.ddoong2;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ParserTest {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser();
    }

    @Test
    public void 빈문자열은_검색결과_TRUE_이다() {
        assertThat(parser.find("", "가나다라 마바사"), is(true));
    }

    @Test
    public void 검색_문자가_NULL_이라면_IllegalArgumentException_이_발생한다() {
        Exception thrown = null;
        try {
            parser.find(null, "가나다라 마바사");
        } catch (Exception e) {
            thrown = e;
        }
        assertThat(thrown, instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void 검색결과가_일치한다면_TRUE_일치하지_않으면_FALSE_를_리턴한다() {
        assertThat(parser.find("ㅎ", "가나다라 마바사"), is(false));
        assertThat(parser.find("a", "가나다라 마바사"), is(false));
        assertThat(parser.find("ㄱ", "가"), is(true));
        assertThat(parser.find("ㄱㄴ", "가나"), is(true));
        assertThat(parser.find("ㄹㅇ", "가나리오"), is(true));
        assertThat(parser.find("ㄴㄷ", "가나 다라"), is(false));
        assertThat(parser.find("ㄱㄴㄷㄹ ㅁ", "가나다라 마바사"), is(true));
        assertThat(parser.find("ㄱㄴㄷㄹ ㅁ", "가나다라 마바사abcd"), is(true));
        assertThat(parser.find("ㄱㄴㄷㄹ", "가나다"), is(false));
    }
}