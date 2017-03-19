package com.adscoop.userservice.services.impls;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.adscoop.userservice.entites.UserNode;

import ratpack.exec.Promise;
import rx.Observable;

/**
 * Created by thokle on 01/09/2016.
 */
public interface IUser {

	Iterable<Map<String, Object>> getUsersByDomainName(String s) throws IOException;

	Optional<UserNode> findByUserNameAndPassword(String username, String password) throws IOException;

	Observable<UserNode> getAllUsersNodes() throws IOException;

	Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException;

	Collection<UserNode> findAll() throws IOException;

	UserNode findbyId(Long id) throws IOException;

	void delete(UserNode entity) throws IOException;

	UserNode saveOrUpdate(UserNode entity) throws IOException;

	boolean doesUserExist(String email) throws Exception;

	Promise<UserNode> findByUserToken(String token);


	boolean userNotExistByEmailAndType(String email, String type) throws Exception;


}
