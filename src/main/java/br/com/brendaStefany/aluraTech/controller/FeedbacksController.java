package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.dto.feedbacks.FeedbacksInboundDTO;
import br.com.brendaStefany.aluraTech.dto.feedbacks.FeedbacksOutboundDTO;
import br.com.brendaStefany.aluraTech.dto.nps.NpsReportData;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsInboundDTO;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsOutboundDTO;
import br.com.brendaStefany.aluraTech.service.FeedbacksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbacksController {

    @Autowired
    FeedbacksService feedbacksService;

    @PostMapping()
    public ResponseEntity<FeedbacksOutboundDTO> addNewRegister(@RequestBody @Valid FeedbacksInboundDTO feedback){
        FeedbacksOutboundDTO feedbackDTO = feedbacksService.addNewFeedback(feedback);
        return new ResponseEntity<>(feedbackDTO, HttpStatus.CREATED);
    }

}
