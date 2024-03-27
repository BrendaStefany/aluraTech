package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.Nps;
import br.com.brendaStefany.aluraTech.dto.nps.NpsReportData;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NpsService {

    @Autowired
    FeedbacksService feedbacksService;

    public void downloadNpsReport(HttpServletResponse response) throws IOException {
        try {
            List<NpsReportData> reportData = generateNpsReportDataList();
            if (reportData == null || reportData.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("No data found for generating the report.");
                return;
            }

            byte[] reportBytes = outputNpsReport(reportData);
            if (reportBytes == null || reportBytes.length == 0) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to generate report bytes.");
                return;
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=aluraTech_nps_report.pdf");

            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(reportBytes);
            outputStream.close();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred while downloading the report.");
        }
    }

    public static byte[] outputNpsReport(List<NpsReportData> reportData) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("src\\main\\resources\\jasperreports\\report_NPS.jrxml");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<NpsReportData> generateNpsReportDataList() {
        List<Object[]> data = feedbacksService.findFeedbacksForNPS();
        List<Nps> npsList = convertToNpsList(data);
        List<NpsReportData> npsReportData = new ArrayList<>();

        for (Nps nps : npsList) {
            npsReportData.add(generateNpsReportData(nps));
        }
        return npsReportData;
    }

    public NpsReportData generateNpsReportData(Nps nps) {
        NpsReportData npsReportData = new NpsReportData();
        npsReportData.setCODE(nps.getCode());
        npsReportData.setQTD_FEEDBACK(nps.getTotal_feedbacks());
        npsReportData.setPROMOTERS(nps.getPromoters());
        npsReportData.setPROMOTERS_PERCENTAGE(calculatePercentage(nps.getPromoters(), nps.getTotal_feedbacks()));
        npsReportData.setNEUTRALS(nps.getNeutrals());
        npsReportData.setNEUTRALS_PERCENTAGE(calculatePercentage(nps.getNeutrals(), nps.getTotal_feedbacks()));
        npsReportData.setDETRACTORS(nps.getDetractor());
        npsReportData.setDETRACTORS_PERCENTAGE(calculatePercentage(nps.getDetractor(), nps.getTotal_feedbacks()));
        npsReportData.setNPS(calculateNetPromoterScore(nps.getPromoters(), nps.getNeutrals(), nps.getDetractor()));
        return npsReportData;
    }

    private String calculatePercentage(int value, int total) {
        double percentage = (double) value / total * 100;
        return String.format("%.2f%%", percentage);
    }

    private String calculateNetPromoterScore(int promoters, int neutrals, int detractors) {
        double nps = ((double) promoters - detractors) / (promoters + neutrals + detractors) * 100;
        return String.format("%.2f%%", nps);
    }

    public List<Nps> convertToNpsList(List<Object[]> data) {
        return data.stream().map(row -> {
            String courseCode = (String) row[0];
            int detractors = ((Number) row[1]).intValue();
            int neutrals = ((Number) row[2]).intValue();
            int promoters = ((Number) row[3]).intValue();
            int totalFeedbacks = ((Number) row[4]).intValue();
            return new Nps(courseCode, detractors, neutrals, promoters, totalFeedbacks);
        }).collect(Collectors.toList());
    }

}
