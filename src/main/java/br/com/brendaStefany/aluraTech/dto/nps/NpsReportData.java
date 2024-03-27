package br.com.brendaStefany.aluraTech.dto.nps;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NpsReportData {

    private String CODE;
    private int QTD_FEEDBACK;
    private String PROMOTERS_PERCENTAGE;
    private int PROMOTERS;
    private String NEUTRALS_PERCENTAGE;
    private int NEUTRALS;
    private String DETRACTORS_PERCENTAGE;
    private int DETRACTORS;
    private String NPS;

}
