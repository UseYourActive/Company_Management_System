package com.tinqin.cms.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

//@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime generatedAt;

    // Getters and setters
}

//    public Report generateReport(String title, String content) {
//        Report report = new Report();
//        report.setTitle(title);
//        report.setContent(content);
//        report.setGeneratedAt(LocalDateTime.now());
//        return reportRepository.save(report);
//    }

// think of a PDF/CSV