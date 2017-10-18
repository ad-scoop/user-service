package com.adscoop.userservice.handlers.company;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.userservice.entites.Company;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.exec.Promise;
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
                Promise<UserNode> userNode = userNodeService.findByUserToken(token);
                userNode.then( userNode1 -> {
                Company companyNode1 = new Company();
                companyNode1.setCompanyname(companyNode.getCompanyname());
                companyNode1.setCompanytype(companyNode.getCompanytype());
                companyNode1.setCvr(companyNode.getCvr());

                companyNode.getLabeles().stream().forEach(la -> {
                    companyNode1.setLabel(la);

                });

                userNode1.getCompanyNodes().add(companyNode1);
                userNodeService.saveOrUpdate(userNode1);
                ctx.render(json(companyNode1));
            });


        });

    }
}
}
