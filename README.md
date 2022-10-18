<div align="center">
<br>
<a href="#"> <img src="terminal.svg" /></a>
<h2>A simple console input/ output library based on Jline</h2>
<hr>
 <a href="https://github.com/mintUI9976/ConsoleLine"><img src="https://img.shields.io/badge/release-1.0.4-ff69b4" /></a>
  <a href="https://github.com/mintUI9976/ConsoleLine"><img src="https://img.shields.io/github/languages/code-size/mintUI9976/ConsoleLine?color=orange" /></a>
  <a href="https://github.com/mintUI9976/ConsoleLine"><img src="https://img.shields.io/tokei/lines/github/mintUI9976/ConsoleLine?color=red" /></a>
  <a href="https://github.com/mintUI9976/ConsoleLine/blob/master/LICENSE"><img src="https://img.shields.io/github/license/mintUI9976/ConsoleLine" /></a>
  <a href="https://github.com/mintUI9976/ConsoleLine/stargazers"><img src="https://img.shields.io/github/stars/mintUI9976/ConsoleLine?color=blue" /></a>
  <img src="https://img.shields.io/badge/opensource-❤-blueviolet">  
<br>
<br>
<a href="https://github.com/jline/jline3">Jline3</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
<a href="https://github.com/vlsi/compactmap">CompactMap</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
<a href="https://github.com/ronmamo/reflections">Reflections</a>
<br>
<hr>
</div>

## About ConsoleLine

### Usage:

- usable with jdk 17 and above
- usable via Jvm hotspot and java9

### Features:

- command system
    - references to CommandArguments
    - no command register, it will be registered by [Reflection](https://github.com/ronmamo/reflections)

- custom exception
    - references to RuntimeException

- log system
    - references to FileWriter
    - can be activated or deactivated
    - log path
    - log filename

- event system
    - input
        - thread able - by set thread size
        - period - set as long
        - messages will be written into log file
    - output
        - output stream will be written into log file
    - error
        - error stream will be written into log file

- builder system
    - terminal can be created by builder principle

<hr>

### Utilization:

- CreateTerminal

````java

public class Test {

    public static void main(final String[] args) {
        final JlineExecutor jlineExecutor =
                JlineBuilder.getJlineBuilder()
                        .setThreadSize(3)
                        .setPrompt(Test.prompt())
                        .setCommandArgumentNotAvailable("The arguments are not available")
                        .setCommandNotAvailable("The command is not available")
                        .setPrefix("de.mint.consoleline")
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

````

- CreateCommand

````java

public class TestCommand implements CommandArguments {

    public TestCommand() {
    }

    @Override
    public @NotNull String commandName() {
        return "test";
    }

    @Override
    public void onCommand(final String[] arguments) {
        switch (arguments[0]) {
            case "print":
                if (arguments.length > 1) {
                    final String[] message = Arrays.copyOfRange(arguments, 1, arguments.length);
                    System.out.println("TestCommand: " + String.join(" ", message));
                } else {
                    System.out.println("Test output");
                }
        }
    }
}

````

