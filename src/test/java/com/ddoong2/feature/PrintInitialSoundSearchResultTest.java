package com.ddoong2.feature;

import com.ddoong2.Console;
import com.ddoong2.InitialSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintInitialSoundSearchResultTest {

    @Mock
    private Console console;

    @Test
    public void 초성으로_검색한_결과를_출력한다() {
        InitialSearch initialSearch = new InitialSearch();

        initialSearch.search("ㄱㄴ");

        verify(console).printLine("Result");
        verify(console).printLine("-------------------------------------------------------");
        verify(console).printLine("가나다라 마바사");
        verify(console).printLine("가나리");
        verify(console).printLine("갈날리랑");
    }
}
