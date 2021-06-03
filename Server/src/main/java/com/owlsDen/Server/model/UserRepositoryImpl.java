package com.owlsden.Server.model;

import java.util.List;

public abstract class UserRepositoryImpl implements UserRepository {
    List<DAOUser> users = findAll();
}
