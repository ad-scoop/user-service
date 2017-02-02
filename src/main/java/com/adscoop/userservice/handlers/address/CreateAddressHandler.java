package com.adscoop.userservice.handlers.address;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import java.util.Optional;

import com.adscoop.userservice.entites.AddressNode;
import com.adscoop.userservice.entites.Company;
import com.adscoop.userservice.entites.UserNode;
import com.adscoop.userservice.services.impls.CompanyServiceImpl;
import com.adscoop.userservice.services.impls.UserNodeServiceImpl;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 10/09/2016.
 */
public class CreateAddressHandler implements Handler {


    private CompanyServiceImpl companyService;
    private  UserNodeServiceImpl userNodeService;
    @Inject
    public CreateAddressHandler(CompanyServiceImpl companyService, UserNodeServiceImpl userNodeService) {

        this.companyService = companyService;
        this.userNodeService = userNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

        if(ctx.getRequest().getMethod().isPost()){
        ctx.parse(fromJson(AddressNode.class)).then(adr -> {

            String companyName = ctx.getPathTokens().get("companyname");

            if (companyName != null) {

                Company companyNode = companyService.findByUser(ctx.getRequest().getHeaders().get("token"), companyName);

                AddressNode addressNode = new AddressNode();
                addressNode.setCountry(adr.getCountry());
                addressNode.setCity(adr.getCity());
                adr.getLabels().stream().filter(a -> a != null).forEach(la -> {
                    addressNode.setLabel(la);

                });
                addressNode.setFloor(adr.getFloor());
                addressNode.setRegion(adr.getRegion());
                addressNode.setSite(adr.getSite());
                addressNode.setStreetname(addressNode.getStreetname());
                addressNode.setStreetNumber(addressNode.getStreetNumber());
                addressNode.setZipcode(adr.getZipcode());
                addressNode.setFloor(adr.getFloor());


                companyNode.addAddress(addressNode);

                companyService.save(companyNode);
                ctx.render(json(addressNode));

            } else
            {
                Optional<UserNode> userNode = userNodeService.findByUserToken(ctx.getRequest().getHeaders().get("token"));
                if(userNode.isPresent()){
                    AddressNode addressNode = new AddressNode();
                    addressNode.setCountry(adr.getCountry());
                    addressNode.setCity(adr.getCity());
                    adr.getLabels().stream().filter(a -> a != null).forEach(la -> {
                        addressNode.setLabel(la);

                    });
                    addressNode.setFloor(adr.getFloor());
                    addressNode.setRegion(adr.getRegion());
                    addressNode.setSite(adr.getSite());
                    addressNode.setStreetname(addressNode.getStreetname());
                    addressNode.setStreetNumber(addressNode.getStreetNumber());
                    addressNode.setZipcode(adr.getZipcode());
                    addressNode.setFloor(adr.getFloor());




                   userNode.get().addAddress(addressNode);
                    ctx.render(json(addressNode));


                }

            }


        });


    }else  {
            ctx.next();
        }

    }

}
