package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongsMother;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {

  @Mock ReportService reportService;
  @Mock SongService songService;
  @InjectMocks FileService fileService;

  @Test
  public void shouldReturnGeneratedTopThreeCsvFileReport() throws IOException {
    // given
    String fileType = "csv";
    String reportType = "top_three";
    List<SongDto> topThreeReport = SongsMother.createTopThreeList();
    Mockito.when(reportService.generateTopXReport(3)).thenReturn(topThreeReport);

    // when
    File reportFile = fileService.generateFile(fileType, reportType);

    // then
    Assertions.assertThat(reportFile).exists().isNotEmpty();
    Assertions.assertThat(reportFile.getName()).contains("csv").doesNotContain("xml");
    Files.delete(Path.of("report.csv"));
  }

  @Test
  public void shouldReturnGeneratedTopThreeXmlFileReport() throws IOException {
    // given
    String fileType = "xml";
    String reportType = "top_three";
    List<SongDto> topThreeReport = SongsMother.createTopThreeList();
    Mockito.when(reportService.generateTopXReport(3)).thenReturn(topThreeReport);

    // when
    File reportFile = fileService.generateFile(fileType, reportType);

    // then
    Assertions.assertThat(reportFile).isNotEmpty().exists();
    Assertions.assertThat(reportFile.getName())
        .contains("xml")
        .doesNotContain("csv")
        .contains("report");
    Files.delete(Path.of("report.xml"));
  }

  @Test
  public void shouldGenerateCategoryReportTest() throws IOException {
    // given
    String fileType = "csv";
    String reportType = "category";
    Mockito.when(reportService.generateCategoryReport("Rock"))
        .thenReturn(SongsMother.createTopThreeList());

    // when
    File reportFile = fileService.generateFile(fileType, reportType);

    // then
    Mockito.verify(reportService, times(1)).generateCategoryReport("Rock");
    Assertions.assertThat(reportFile).exists().isNotEmpty();
    Files.delete(Path.of("report.csv"));
  }

  @Test
  public void shouldGenerateTopReportTest() throws IOException {
    // given
    String fileType = "csv";
    String reportType = "top";
    Mockito.when(reportService.generateTopReport()).thenReturn(SongsMother.createTopThreeList());

    // when
    File reportFile = fileService.generateFile(fileType, reportType);

    // then
    Mockito.verify(reportService, times(1)).generateTopReport();
    Assertions.assertThat(reportFile).exists().isNotEmpty();
    Files.delete(Path.of("report.csv"));
  }

  @Test
  public void shouldGenerateTopTenReportTest() throws IOException {
    // given
    String fileType = "csv";
    String reportType = "top_ten";
    Mockito.when(reportService.generateTopXReport(10)).thenReturn(SongsMother.createTopThreeList());

    // when
    File reportFile = fileService.generateFile(fileType, reportType);

    // then
    Mockito.verify(reportService, times(1)).generateTopXReport(10);
    Assertions.assertThat(reportFile).exists().isNotEmpty();
    Files.delete(Path.of("report.csv"));
  }

  @Test
  public void shouldThrowExceptionWhenUnknownReportFileIsPassedToMethodTest() throws IOException {
    // given
    String fileType = "csv";
    String reportType = "abc";

    // when & then
    Assertions.assertThatIllegalArgumentException()
        .isThrownBy(() -> fileService.generateFile(fileType, reportType));
  }

  @Test
  public void shouldReadCsvFileTest() {
    // given
    MockMultipartFile mockFile =
        new MockMultipartFile(
            "test.csv",
            "test.csv",
            "text/plain",
            ("Title,Author,Album,Category,Votes\n"
                    + "Living in a Ghost Town,The Rolling Stones,Honk,Rock,10\n"
                    + "You Should Be Sad,Halsey,Manic,Alternative,2\n"
                    + "Imported,Jessie Reyez,Before Love Came to Kill Us,R&B,6\n")
                .getBytes());

    // when
    fileService.processFile(mockFile);

    // then
    Mockito.verify(songService, times(3)).addSong(any());
  }

  @ParameterizedTest
  @ValueSource(strings = {"doc", "txt", "pdf"})
  public void shouldThrowExceptionWhenUnhandledFileTypeIsHandled(String fileType) {
    // given
    String reportType = "top";
    fileService = new FileService(songService,reportService);

    // when & then
    Assertions.assertThatIllegalArgumentException().isThrownBy(() -> fileService.generateFile(fileType, reportType))
        .isInstanceOf(IllegalArgumentException.class);

  }
}
