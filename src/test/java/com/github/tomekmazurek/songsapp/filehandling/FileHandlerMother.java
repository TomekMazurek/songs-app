package com.github.tomekmazurek.songsapp.filehandling;

import org.springframework.mock.web.MockMultipartFile;

public class FileHandlerMother {

  public static MockMultipartFile getXmlTestFile() {
    return new MockMultipartFile(
        "test",
        "test.xml",
        "text/plain",
        ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<songs>\n"
                + "  <song>\n"
                + "    <title>Living in a Ghost Town</title>\n"
                + "    <author>The Rolling Stones</author>\n"
                + "    <album>Honk</album>\n"
                + "    <category>Rock</category>\n"
                + "    <votes>10</votes>\n"
                + "  </song>\n"
                + "  <song>\n"
                + "    <title>You Should Be Sad</title>\n"
                + "    <author>Halsey</author>\n"
                + "    <album>Manic</album>\n"
                + "    <category>Alternative</category>\n"
                + "    <votes>2</votes>\n"
                + "  </song>\n"
                + "  <song>\n"
                + "    <title>Imported</title>\n"
                + "    <author>Jessie Reyez</author>\n"
                + "    <album>Before Love Came to Kill Us</album>\n"
                + "    <category>R&B</category>\n"
                + "    <votes>6</votes>\n"
                + "  </song>\n"
                + "</songs>")
            .getBytes());
  }

  public static MockMultipartFile getCsvTestFile() {
    return new MockMultipartFile(
        "test",
        "test.csv",
        "text/plain",
        ("Title,Author,Album,Category,Votes\n"
                + "Living in a Ghost Town,The Rolling Stones,Honk,Rock,10\n"
                + "You Should Be Sad,Halsey,Manic,Alternative,2\n"
                + "Imported,Jessie Reyez,Before Love Came to Kill Us,R&B,6")
            .getBytes());
  }
}
