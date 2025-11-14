package de.mint.consoleline.event.output;

import de.mint.consoleline.service.JlineUtils;
import de.mint.consoleline.write.LogWriter;
import org.jline.reader.LineReader;

import java.io.PrintStream;
import java.nio.charset.Charset;

public record OutputPrintStream(Charset charset) {

    public void stream(final boolean log, final LineReader lineReader, final LogWriter logWriter) {

    final PrintStream jlineOut =
        new PrintStream(System.out, false, charset) {

          private void jlinePrint(String msg) {
            lineReader.printAbove(msg);
            lineReader.getTerminal().writer().flush();

            if (log && logWriter != null) {
              logWriter.insert(JlineUtils.cleanAnsiString(msg));
            }
          }

          @Override
          public void println(String x) {
            jlinePrint(x);
          }

          @Override
          public void println() {
            jlinePrint("");
          }

          @Override
          public void print(String s) {
            jlinePrint(s);
          }

          @Override
          public PrintStream printf(String format, Object... args) {
            jlinePrint(String.format(format, args));
            return this;
          }

          @Override
          public PrintStream format(String format, Object... args) {
            jlinePrint(String.format(format, args));
            return this;
          }

          @Override
          public void write(byte[] buf, int off, int len) {
            String s = new String(buf, off, len, charset);
            jlinePrint(s);
          }
        };

    System.setOut(jlineOut);
    System.setErr(jlineOut);
  }
}
