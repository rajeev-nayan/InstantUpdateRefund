package org.example.evaluations.evaluation.controllers;

import org.apache.commons.lang3.StringUtils;
import org.example.evaluations.evaluation.dtos.RefundRequestDto;
import org.example.evaluations.evaluation.services.IRefundService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefundController {

    @Autowired
    private IRefundService refundService;

    // Add your APIs here
    @PostMapping("/issueRefund")
    public String issueRefund(@RequestBody RefundRequestDto dto) {
        return refundService.issueRefund(dto.getAmount(), dto.getReceipt());
    }

    @PatchMapping("/updateRefund/{refundId}")
    public String updateRefund(@RequestBody RefundRequestDto dto, @PathVariable String refundId) {
        JSONObject jsonObject = new JSONObject();
        if (dto.getAmount() != null) {
            jsonObject.put("amount", dto.getAmount());
        }
        if (dto.getReceipt() != null) {
            jsonObject.put("receipt", dto.getReceipt());
        }

        if (dto.getRefundSpeed() != null) {
            jsonObject.put("speed", dto.getRefundSpeed().toString());
        }
        return refundService.updateRefund(refundId, jsonObject);
    }

}
