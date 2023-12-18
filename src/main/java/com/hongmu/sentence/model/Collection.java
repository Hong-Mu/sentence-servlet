package com.hongmu.sentence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Collection {
    private int id;
    private String title;
    private int userId;
}
