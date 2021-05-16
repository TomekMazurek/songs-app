package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongDtoMapper;
import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.service.ReportService;
import com.github.tomekmazurek.songsapp.service.SongService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/reports")
public class ReportController {

  private ReportService reportService;

  public ReportController( ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/category")
  public List<SongDto> getCategoryReport(@RequestParam(required = true) Category category){
    return reportService.generateCategoryReport(category);
  }
  @GetMapping()
  public List<SongDto> getTopXReport(@RequestParam int x){
    return reportService.generateTopXReport(x);
  }
}
