package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Report API", tags = "report api", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation(value = "get category report", response = SongDto[].class)
    @GetMapping("/category")
    public List<SongDto> getCategoryReport(@RequestParam String category) {
        return reportService.generateCategoryReport(category);
    }

    @ApiOperation(value = "get first x songs sorted by votes", response = SongDto[].class)
    @GetMapping("/top")
    public List<SongDto> getTopXReport(@RequestParam(value = "value") Integer x) {
        return reportService.generateTopXReport(x);
    }

    @ApiOperation(value = "get all songs sorted", response = SongDto[].class)
    @GetMapping()
    public List<SongDto> getSongsSortedByVotes() {
        return reportService.generateTopReport();
    }
}
