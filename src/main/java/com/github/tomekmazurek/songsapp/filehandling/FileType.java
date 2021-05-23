package com.github.tomekmazurek.songsapp.filehandling;

public enum FileType {
  CSV("csv"),
  XML("xml");
  private String value;

  FileType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
