package com.bkbk.qsdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class StaDto {
    List<Integer> qnchoiceId;
    private Long staTotal;
    List<Integer> staNum;
    List<Integer> staA;
    List<Integer> staB;
    List<Integer> staC;
}
