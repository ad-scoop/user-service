package com.adscoop.userservice.services.impls;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.MapUtils;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adscoop.userservice.entites.UserNode;
import com.google.inject.Inject;

import ratpack.exec.Promise;
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
	public Promise<Iterable<Map<String, Object>>> getUsersByDomainName(String s) {
		return null;
	}

	@Override
	public Promise<UserNode> findByUserNameAndPassword(String username, String password) throws IOException {
		try {
			return Promise.value(session.queryForObject(UserNode.class, "match (a:UserNode) where a.username='" + username
					+ "' and  a.password='" + password + "' return a", Collections.emptyMap()));
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
	public Promise<Collection<UserNode>> getAllUsersNodes() throws IOException {
		try {
			return Promise.value(session.loadAll(UserNode.class, DEPTH_LIST));
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
			if (session.detachNodeEntity(entity.getId())) {
				this.session.delete(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserNode saveOrUpdate(UserNode entity) throws IOException {
		try {
			session.save(entity, DEPTH_ENTITY);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return findbyId(entity.getId());
	}

	@Override
	public boolean doesUserExist(String email) throws Exception {
		UserNode u = session.queryForObject(UserNode.class,
				"match (u:UserNode)  where u.email='" + email + "' return u limit 1 ", Collections.emptyMap());
		if (u == null) {
			return false;
		}
		return true;
	}

	@Override
	public Promise<UserNode> findByUserToken(String token) {
		return Promise.value(session.queryForObject(UserNode.class,
				"match (u:UserNode, {token:'{token}'}) return u", Collections.singletonMap("token",token)));
	}

	@Override
	public boolean userNotExistByEmail(String email) {
		UserNode userNode = session.queryForObject(UserNode.class,
				"match (u:UserNode {email:'{email}'}) return u limit 1 ", Collections.singletonMap("email",email));
		if (userNode != null) {
			return true;
		}
		return false;
	}

}
