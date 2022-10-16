package de.mint.consoleline.event.output;

import de.mint.consoleline.service.JlineUtils;
import de.mint.consoleline.write.LogWriter;
import org.jline.reader.LineReader;

import java.io.PrintStream;
import java.nio.charset.Charset;

public record OutputPrintStream(Charset charset) {

    // A method that is used to override the standard output stream.
    public void stream(final boolean log, final LineReader lineReader, final LogWriter logWriter) {
        System.setOut(
                new PrintStream(System.out, false, this.charset) {
                    @Override
                    public void println(final String message) {
                        assert message != null;
                        final String temp = message + "\n";
                        lineReader.printAbove(temp);
                        lineReader.getTerminal().writer().flush();
                        if (log) {
                            if (logWriter != null) {
                                logWriter.insert(JlineUtils.cleanAnsiString(message));
                            }
                        }
                    }
                });
    }
}
