package de.mint.consoleline.event.output;

import de.mint.consoleline.service.JlineUtils;
import de.mint.consoleline.write.LogWriter;
import org.jline.reader.LineReader;

import java.io.PrintStream;
import java.nio.charset.Charset;

public record OutputPrintStream(Charset charset) {

    public void stream(final LineReader lineReader, final boolean log, final LogWriter logWriter) {
        System.setOut(
                new PrintStream(System.out, true, this.charset) {
                    @Override
                    public void println(String message) {
                        assert message != null;
                        message = message + "\n";
                        lineReader.printAbove(message);
                        if (log) {
                            if (logWriter != null) {
                                logWriter.insert(JlineUtils.cleanAnsiString(message));
                            }
                        }
                        /*lineReader.getTerminal().writer().flush();*/
                    }
                });
    }
}
