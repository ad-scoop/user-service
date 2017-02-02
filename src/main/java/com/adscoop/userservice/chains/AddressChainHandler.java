package com.adscoop.userservice.chains;

import com.adscoop.userservice.handlers.address.CreateAddressHandler;
import com.adscoop.userservice.handlers.address.DeleteAddressHandler;
import com.adscoop.userservice.handlers.address.GetAdderessHandler;
import com.adscoop.userservice.handlers.address.UpdateAddressHandler;
import com.adscoop.userservice.handlers.auth.AuthHandler;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 15/01/2017.
 */
public class AddressChainHandler  implements Action<Chain>
         {
             @Override
             public void execute(Chain chain) throws Exception {
                  chain.all(AuthHandler.class).post(":companyname",CreateAddressHandler.class).delete(DeleteAddressHandler.class).put(UpdateAddressHandler.class).get(GetAdderessHandler.class);
             }
         }
