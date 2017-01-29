package com.adscoop.userservice.handlers.address;

import java.util.Optional;

import com.adscoop.entiites.AddressNode;
import com.adscoop.userservice.services.impls.AddressUserServiceImpl;
import com.google.inject.Inject;

import ratpack.form.Form;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 10/09/2016.
 */
public class UpdateAddressHandler implements Handler {


    private AddressUserServiceImpl addressUserService;

    @Inject
    public UpdateAddressHandler(AddressUserServiceImpl addressUserService) {
        this.addressUserService = addressUserService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String path = ctx.getPathTokens().get("cypher");


        ctx.parse(Form.class).then(rs -> {
            try {
                Optional<AddressNode> resultat = addressUserService.findByUser(path);
                resultat.get().setCity(rs.get("city"));
                resultat.get().setCountry(rs.get("country"));
                resultat.get().setFloor(Integer.valueOf(rs.get("floor")));
                rs.getAll("labels").stream().filter(f -> f != null).forEach(la -> {

                    resultat.get().setLabel(la);
                });
                resultat.get().setRegion(rs.get("region"));
                resultat.get().setSite(rs.get("site"));
                resultat.get().setStreetname(rs.get("streetname"));
                resultat.get().setStreetNumber(Integer.valueOf(rs.get("streetnumber")));
                resultat.get().setZipcode(Integer.valueOf(rs.get("zipcode")));


                addressUserService.saveOrUpdate(resultat.get());

            } catch (Exception e) {

            }
        });


    }
}
