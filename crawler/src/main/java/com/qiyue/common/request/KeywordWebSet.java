package com.qiyue.common.request;

import lombok.Data;

import java.util.List;

@Data
public class KeywordWebSet {
    private Integer webId;
    private List<Integer> keywords;
    private List<Integer> categories;
}
