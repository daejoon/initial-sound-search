package com.ddoong2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterTest {

    @Mock
    private Console console;

    private StatementPrinter statementPrinter;

    @Before
    public void setUp() throws Exception {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void 검색조건이_없는_리스트를_출력한다() {
        statementPrinter.print(new String[0]);

        verify(console).printLine("Result");
        verify(console).printLine("-------------------------------------------------------");
    }

    @Test
    public void 검색조건이_있는_리스트를_출력한다() {
        statementPrinter.print(new String[] {
                "가나다라"
        });

        verify(console).printLine("Result");
        verify(console).printLine("-------------------------------------------------------");
        verify(console).printLine("가나다라");
    }
}