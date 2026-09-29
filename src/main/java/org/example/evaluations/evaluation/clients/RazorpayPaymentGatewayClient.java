package org.example.evaluations.evaluation.clients;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

@Component
public class RazorpayPaymentGatewayClient {

    @Autowired
    private RazorpayClient razorpayClient;

    private final String paymentId = "pay_39QqoUAi66xm2f"; // use this paymentId, wherever needed

    public String issueInstantRefund(Double amount, String receipt) {
        JSONObject refundRequest = new JSONObject();

        if (amount != null)
            refundRequest.put("amount", amount);
        
        refundRequest.put("speed", "optimum");
        
        if (receipt != null)
            refundRequest.put("receipt", receipt);

        JSONObject notes = new JSONObject();
        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
        notes.put("notes_key_2", "Tea, Earl Grey… decaf.");
        refundRequest.put("notes", notes);

        try {
            Refund refund = razorpayClient.payments.refund(paymentId, refundRequest);
            return refund.get("id");
        } catch (RazorpayException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getMessage());
        }
    }

    public String updateRefund(String refundId, JSONObject jsonObject) {
        try {
            Refund refund = razorpayClient.refunds.edit(refundId, jsonObject);
            return refund.get("id");
        } catch (RazorpayException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getMessage());
        }

    }
}
