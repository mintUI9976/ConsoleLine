package de.mint.consoleline.event.error;

import de.mint.consoleline.service.JlineUtils;
import de.mint.consoleline.write.LogWriter;
import org.jline.reader.LineReader;

import java.io.PrintStream;
import java.nio.charset.Charset;

public record ErrorPrintStream(Charset charset) {

  public void stream(final boolean log, final LineReader lineReader, final LogWriter logWriter) {

    PrintStream jlineErr =
        new PrintStream(System.err, false, charset) {

          private void jlinePrint(String msg) {

            synchronized (lineReader.getTerminal()) {
              lineReader.getTerminal().writer().println(msg);
              lineReader.getTerminal().writer().flush();
              lineReader.callWidget(LineReader.REDRAW_LINE);
              lineReader.callWidget(LineReader.REDISPLAY);
            }

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

    System.setErr(jlineErr);
  }
}
