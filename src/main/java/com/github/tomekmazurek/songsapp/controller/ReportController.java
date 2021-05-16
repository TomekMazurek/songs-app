package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/reports")
public class ReportController {

  private ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/category")
  public List<SongDto> getCategoryReport(@RequestParam(required = true) String category) {
    return reportService.generateCategoryReport(category);
  }

  @GetMapping("/top")
  public List<SongDto> getTopXReport(@RequestParam(value = "value", required = true) Integer x) {
    return reportService.generateTopXReport(x);
  }

  @GetMapping()
  public List<SongDto> getSongsSortedByVotes() {
    return reportService.generateTopReport();
  }
}
