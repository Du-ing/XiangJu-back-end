package com.xiangju.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求互助的类型参数数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelpType {
    private String timeLimit;
    private String site;
    private String type;
    private double min;
    private double max;
}
