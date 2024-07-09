package com.ata.joblist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {

    private Map<String, String> sorts;
    private Map<String, List<String>> filters = new HashMap<>();
    private String fields;
}