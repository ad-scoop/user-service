package com.adscoop.userservice.handlers.credit;

import static ratpack.jackson.Jackson.fromJson;

import com.adscoop.userservice.entites.CreditInfo;
import com.adscoop.userservice.services.impls.CreditInfoServiceImpl;
import com.google.inject.Inject;

import ratpack.exec.ExecutionException;
import ratpack.handling.Context;
import ratpack.handling.Handler;


/**
 * Created by thokle on 10/09/2016.
 */
public class UpdateCreditHandler implements Handler {

    private CreditInfoServiceImpl creditInfoService;


    @Inject
    public UpdateCreditHandler(CreditInfoServiceImpl creditInfoService) {
        this.creditInfoService = creditInfoService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

        Long userid = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));

        ctx.parse(fromJson(CreditInfo.class)).then(creditInfo -> {
            try {
                CreditInfo creditInfo1 = creditInfoService.findByUserId(userid);

                creditInfo1.setCardnumber(creditInfo.getCardnumber());
                creditInfo.getLabels().stream().forEach(ls -> {
                    creditInfo1.setLabel(ls);

                });
                creditInfo1.setStartDate(creditInfo.getStartDate());
                creditInfo1.setStartEndYear(creditInfo1.getStartEndYear());
                creditInfo1.setCardHolderName(creditInfo.getCardHolderName());

            } catch (ExecutionException e){
                ctx.getResponse().getStatus();
            }

        });
    }
}
