package com.adscoop.userservice.handlers.credit;


import com.adscoop.userservice.entites.CreditInfo;
import com.adscoop.userservice.services.impls.CreditInfoServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 26/12/2016.
 */
public class DeleteCreditCardHandler implements Handler {

    private CreditInfoServiceImpl creditInfoService;

@Inject
    public DeleteCreditCardHandler(CreditInfoServiceImpl creditInfoService) {
        this.creditInfoService = creditInfoService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        CreditInfo creditInfo = creditInfoService.findByUserToken(token);
        if (creditInfo != null) {
            try {
                creditInfoService.delete(creditInfo);
            }catch (Exception e){

            }
        }
    }
}
