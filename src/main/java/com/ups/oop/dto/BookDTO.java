package com.ups.oop.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
 public class BookDTO {
    private Long id;
    private String title;
    private String editorial;
    private String authorFullName;
}