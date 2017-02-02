package com.adscoop.userservice.services.impls;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adscoop.userservice.entites.UserNode;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by thokle on 03/09/2016.
 */

public class UserNodeServiceImpl implements IUser {

	private static final int DEPTH_LIST = 0;
	private static final int DEPTH_ENTITY = 1;
	private static Logger logger = LoggerFactory.getLogger(UserNodeServiceImpl.class);
	private Session session;

	@Inject
	public UserNodeServiceImpl(Session connectionSource) {

		this.session = connectionSource;

	}

	@Override
	public Iterable<Map<String, Object>> getUsersByDomainName(String s) {
		return null;
	}

	@Override
	public Optional<UserNode> findByUserNameAndPassword(String username, String password) throws IOException {
		try {
			return Optional.of(session.queryForObject(UserNode.class, "match (a:UserNode) where a.username='"
					+ username + "' and  a.password='" + password + "' return a", Collections.EMPTY_MAP));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException {
		try {
			return Observable.from(session.query(cypherQuery, Collections.EMPTY_MAP)).create(as -> {

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public Collection<UserNode> findAll() throws IOException {
		try {
			return session.loadAll(UserNode.class, DEPTH_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Observable<UserNode> getAllUsersNodes() throws IOException {
		try {
			return Observable.from(session.loadAll(UserNode.class, DEPTH_LIST));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserNode findbyId(Long id) throws IOException {
		try {
			return session.load(UserNode.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(UserNode entity) throws IOException {
		try {
			this.session.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserNode saveOrUpdate(UserNode entity) throws IOException {
		// logger.debug(session.session().getTransaction().status().name());
		try {
			session.save(entity, DEPTH_ENTITY);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.print(e);

		}
		return entity;
	}




	@Override
	public boolean doesUserExist(String email) {

		Optional<UserNode> node = Optional.ofNullable(session.queryForObject(UserNode.class, "match (u {email: '"+email+"' } )return u  ", Collections.EMPTY_MAP));
		if(node.isPresent()){
			return true;
		}
		return false;

	}

	@Override
	public Optional<UserNode> findByUserToken(String token) {
		return Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where u.token='"+token+ "' return u",Collections.EMPTY_MAP));
	}
}
