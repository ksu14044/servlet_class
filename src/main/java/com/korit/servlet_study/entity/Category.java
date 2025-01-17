package com.korit.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Category {
    private int id;
    private String name;
}
