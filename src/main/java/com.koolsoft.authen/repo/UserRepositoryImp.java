package com.koolsoft.authen.repo;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.koolsoft.authen.model.User;


public class UserRepositoryImp implements UserRepository{


    @Override
    public long save(User user){
        Entity userEntity = new Entity("user");
        userEntity.setProperty("id", user.getId() );
        userEntity.setProperty("username", user.getUsername());
        userEntity.setProperty("password", user.getPassword());
//        userEntity.setProperty("role", user.getRole());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = datastore.put(userEntity);

        return key.getId();
    }
}
