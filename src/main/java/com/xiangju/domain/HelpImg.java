package com.xiangju.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelpImg {
    private int imgid;
    private int helpid;
    private String imgUrl;
}
