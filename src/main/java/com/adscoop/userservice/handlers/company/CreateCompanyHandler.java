package com.adscoop.userservice.handlers.company;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import java.util.Optional;

import com.adscoop.userservice.entites.Company;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

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
        String token = String.valueOf(ctx.getRequest().getHeaders().get("token"));
        if(token !=null) {
            ctx.parse(fromJson(Company.class)).then(companyNode -> {
                Optional<UserNode> userNode = userNodeService.findByUserToken(token);
                Company companyNode1 = new Company();
                companyNode1.setCompanyname(companyNode.getCompanyname());
                companyNode1.setCompanytype(companyNode.getCompanytype());
                companyNode1.setCvr(companyNode.getCvr());

                companyNode.getLabeles().stream().forEach(la -> {
                    companyNode1.setLabel(la);

                });

                userNode.get().setCompanyNode(companyNode1);
                userNodeService.saveOrUpdate(userNode.get());
                ctx.render(json(companyNode1));
            });

        }

    }
}
