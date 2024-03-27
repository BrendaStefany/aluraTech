package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.dto.nps.NpsReportData;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.service.NpsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/nps")
public class NpsController {

    @Autowired
    NpsService npsService;

    @GetMapping("/download")
    public void downloadNpsReport(HttpServletResponse response) throws IOException {
        npsService.downloadNpsReport(response);
    }

    @GetMapping()
    public ResponseEntity<List<NpsReportData>> NpsReport(){
        List<NpsReportData> npsReportData = npsService.generateNpsReportDataList();
        return new ResponseEntity<>(npsReportData, HttpStatus.OK);
    }

}
