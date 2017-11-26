package com.adscoop.userservice.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.adscoop.userservice.entites.UserNode;

import ratpack.exec.Promise;

/**
 * Created by thokle on 01/09/2016.
 */
public interface IUser {

	Promise<Iterable<Map<String, Object>>> getUsersByDomainName(String s) throws IOException;

	Promise<UserNode> findByUserNameAndPassword(String username, String password) throws IOException;

	Promise<Collection<UserNode>>  	getAllUsersNodes() throws IOException;

	Collection<UserNode> findAll() throws IOException;

	UserNode findbyId(Long id) throws IOException;

	void delete(UserNode entity) throws IOException;

	UserNode saveOrUpdate(UserNode entity) throws IOException;

	boolean doesUserExist(String email) throws Exception;

	Promise<UserNode> findByUserToken(String token);

	boolean userNotExistByEmail(String email);

}
