package de.mint.consoleline.Format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomFormat {

  private String stringFormat;

  private String previewColor;

  private SimpleDateFormat simpleDateFormat;

  private String message;

  public CustomFormat(
      String stringFormat, String previewColor, SimpleDateFormat simpleDateFormat, String message) {
    this.stringFormat = stringFormat;
    this.previewColor = previewColor;
    this.simpleDateFormat = simpleDateFormat;
    this.message = message;
  }

  public String format() {
    final String time = simpleDateFormat.format(new Date());
    return String.format(
        this.stringFormat,
        this.previewColor,
        time,
        CustomColors.YELLOW,
        "WARN",
        this.previewColor,
        message,
        CustomColors.RESET);
  }

  public String getStringFormat() {
    return stringFormat;
  }

  public String getPreviewColor() {
    return previewColor;
  }

  public SimpleDateFormat getSimpleDateFormat() {
    return simpleDateFormat;
  }

  public String getMessage() {
    return message;
  }
}
