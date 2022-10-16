package de.mint.consoleline.event.error;

import de.mint.consoleline.service.JlineUtils;
import de.mint.consoleline.write.LogWriter;
import org.jetbrains.annotations.Nullable;
import org.jline.reader.LineReader;

import java.io.PrintStream;
import java.nio.charset.Charset;

public record ErrorPrintStream(Charset charset) {

  public void stream(final boolean log, final LineReader lineReader, final LogWriter logWriter) {

    System.setErr(
        new PrintStream(System.err, false, this.charset) {
          @Override
          public void println(@Nullable final String message) {
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
