package com.ups.oop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
 public class BookDTO {
    private String bookId;
    private String name;
    private String editorial;
    private String title;
}
