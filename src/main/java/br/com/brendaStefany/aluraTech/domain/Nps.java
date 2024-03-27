package br.com.brendaStefany.aluraTech.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nps {

    private String code;
    private int detractor;
    private int neutrals;
    private int promoters;
    private int total_feedbacks;

}
