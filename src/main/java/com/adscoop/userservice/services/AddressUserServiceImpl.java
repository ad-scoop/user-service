package com.adscoop.userservice.services;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.neo4j.ogm.session.Session;

import com.adscoop.userservice.entites.AddressNode;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by thokle on 01/09/2016.
 */
public class AddressUserServiceImpl implements AddressUserService {
	
	private static final int DEPTH_ENTITY = 1;
	private Session session;

	@Inject
	public AddressUserServiceImpl(Session connectionSource) {
		this.session = connectionSource;
	}

	@Override
	public Observable<Map<String, Object>> findByCypher(String cypherQuery) throws IOException {

		try {
			return Observable.from(session.query(cypherQuery, Collections.emptyMap()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterable<AddressNode> findAll() throws IOException {
		try {
			return session.loadAll(AddressNode.class, DEPTH_ENTITY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AddressNode findbyId(Long id) throws IOException {
		try {
			session.load(AddressNode.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(AddressNode entity) throws IOException {
		try {
			if(session.detachNodeEntity(entity.getId())){
				session.delete(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<AddressNode> saveOrUpdate(AddressNode entity) throws IOException {
		try {
			session.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.of(findbyId(entity.getId()));
	}

	@Override
	public Optional<AddressNode> findByUser(String cypher) throws IOException {

		try {
			return Optional.of(session.queryForObject(AddressNode.class,
					"match (a)-[:ADDRESS_BELONGS_TO_COMPANY]->(c)-[:COMPANY_BELONGS_TO_USER]->(u) where u.id='" + cypher
							+ "' return a as AddressNode",
					Collections.emptyMap()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
