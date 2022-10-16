package de.mint.consoleline.test;

import de.mint.consoleline.service.JlineBuilder;
import de.mint.consoleline.service.JlineExecutor;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class Test {

  public static void main(final String[] args) {
    final JlineExecutor jlineExecutor =
        JlineBuilder.getJlineBuilder()
            .setThreadSize(3)
            .setPrompt(Test.prompt())
            .setLog(true)
            .build();
    jlineExecutor.buildTerminal();
  }

  private static String prompt() {
    return new AttributedStringBuilder()
        .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN))
        .append("@test")
        .style(AttributedStyle.DEFAULT)
        .append("> ")
        .toAnsi();
  }
}
