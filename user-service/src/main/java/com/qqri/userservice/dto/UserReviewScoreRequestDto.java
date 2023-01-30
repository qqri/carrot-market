package com.qqri.userservice.dto;

import lombok.Data;

@Data
public class UserReviewScoreRequestDto {
    String name; // 리뷰 대상
    int score; // 1 : bad , 2 : soso , 3 : good
}
