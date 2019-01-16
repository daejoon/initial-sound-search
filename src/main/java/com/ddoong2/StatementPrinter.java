package com.ddoong2;

import java.util.Arrays;

public class StatementPrinter {

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(String[] datas) {
        console.printLine("Result");
        console.printLine("-------------------------------------------------------");
        Arrays.stream(datas).forEach(console::printLine);
    }
}
