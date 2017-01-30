package com.adscoop.userservice.handlers;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.entiites.CreditInfo;
import com.adscoop.userservice.services.impls.CreditInfoServiceImpl;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;


/**
 * Created by thokle on 01/09/2016.
 */
public class CreditCardHandler implements Handler
{

    private CreditInfoServiceImpl creditInfoService;
    private UserNodeServiceImpl userNodeService;
    @Inject
    public CreditCardHandler(CreditInfoServiceImpl creditInfoService, UserNodeServiceImpl userNodeService) {
        this.creditInfoService = creditInfoService;
        this.userNodeService = userNodeService;

    }

    @Override
    public void handle(Context ctx) throws Exception {
       String method = ctx.getRequest().getMethod().getName();

        switch (method){
            case "POST":
              //  UserNode userNode = userNodeService.findbyId(Long.valueOf(ctx.getRequest().getHeaders().get("userid")));
                ctx.parse(fromJson(CreditInfo.class)).then(c -> {

                   CreditInfo creditInfo =  creditInfoService.saveOrUpdate(c);
              // userNode.getCreditInfos().add(creditInfo);
                //    userNodeService.saveOrUpdate(userNode);
                ctx.render(json(creditInfo, CreditInfo.class));

                });
                break;
            case "GET":
                if(ctx.getPathTokens().get("id")!=null){

                    creditInfoService.findbyId(new Long(ctx.getPathTokens().get("userid")));
                }else
                {


                }
                break;

        }

    }


}
