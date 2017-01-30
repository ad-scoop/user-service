package com.adscoop.userservice.chains;

import com.adscoop.userservice.handlers.auth.AuthHandler;
import com.adscoop.userservice.handlers.users.DeleteUserHandler;
import com.adscoop.userservice.handlers.users.GetUserHandler;
import com.adscoop.userservice.handlers.users.UpdateUserHandler;

import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 15/01/2017.
 */
public class UserChainHandler implements Action<Chain> {


    @Override
    public void execute(Chain chain) throws Exception {
       chain.all(AuthHandler.class).delete("delete",DeleteUserHandler.class).put("update",UpdateUserHandler.class).get("get",GetUserHandler.class);
    }
}
