package com.hongmu.sentence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Sentence {
    private int id;
    private String title;
    private String subtitle;
    private int userId;
}
