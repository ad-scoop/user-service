package com.adscoop.userservice.handlers.company;

import com.adscoop.entiites.Company;
import com.adscoop.entiites.UserNode;
import com.google.inject.Inject;

import com.adscoop.userservice.services.impls.UserNodeServiceImpl;

import ratpack.handling.Context;
import ratpack.handling.Handler;


import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 18/10/2016.
 */
public class CreateCompanyHandler implements Handler {

    UserNodeServiceImpl userNodeService;

    @Inject
    public CreateCompanyHandler(UserNodeServiceImpl userNodeService) {
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Long userid = Long.valueOf(ctx.getRequest().getHeaders().get("userid"));
        if(userid !=null) {
            ctx.parse(fromJson(Company.class)).then(companyNode -> {
                UserNode userNode = userNodeService.findbyId(userid);
                Company companyNode1 = new Company();
                companyNode1.setCompanyname(companyNode.getCompanyname());
                companyNode1.setCompanytype(companyNode.getCompanytype());
                companyNode1.setCvr(companyNode.getCvr());

                companyNode.getLabeles().stream().forEach(la -> {
                    companyNode1.setLabel(la);

                });

                userNode.setCompanyNode(companyNode1);
                userNodeService.saveOrUpdate(userNode);
                ctx.render(json(companyNode1));
            });

        }

    }
}
