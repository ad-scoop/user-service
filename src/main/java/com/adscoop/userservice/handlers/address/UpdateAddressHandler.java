package com.adscoop.userservice.handlers.address;

import com.adscoop.entiites.AddressNode;
import com.google.inject.Inject;

import com.adscoop.userservice.services.impls.AddressUserServiceImpl;
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


                   ctx.parse(Form.class).then(rs-> {

                      AddressNode resultat = addressUserService.findByUser(path);
                       resultat.setCity(rs.get("city"));
                       resultat.setCountry(rs.get("country"));


                       addressUserService.saveOrUpdate(resultat);


                       });





    }
}
