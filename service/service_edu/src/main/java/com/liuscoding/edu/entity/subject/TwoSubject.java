package com.liuscoding.edu.entity.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: TwoSubject
 * @description: 二级分类
 * @author: liusCoding
 * @create: 2020-05-10 15:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwoSubject {

    private String id;

    private String title;
}
