//package com.koolsoft.authen.DAO;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.google.appengine.api.datastore.DatastoreService;
//import com.google.appengine.api.datastore.DatastoreServiceFactory;
//import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.api.datastore.FetchOptions;
//import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.api.datastore.Query;
//import org.springframework.stereotype.Component;
//
//import javax.xml.stream.events.Comment;
//
//@Component
//public class GoogleDSCommentDAO implements CommentDAO {
//
//    public static final String EntityKind = "TestComments";
//
//    @Override
//    public long save(Comment comment) {
//        Key commentKey = KeyFactory.createKey( EntityKind, comment.getEmail());
//        Entity commentEntity = new Entity("Comment", commentKey);
//        commentEntity.setProperty("email", comment.getEmail());
//        commentEntity.setProperty("date", comment.getDate());
//        commentEntity.setProperty("text", comment.getText());
//
//        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//        Key key = datastore.put(commentEntity);
//
//        return key.getId();
//    }
//
//
//    public List getAll(int count ) {
//        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//        Query query = new Query("Comment").addSort("date", Query.SortDirection.DESCENDING);
//        List entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit( count ));
//        List comments = new ArrayList();
//        if( entities != null &amp;&amp; entities.size() > 0 ) {
//            for( Entity entity: entities ) {
//                comments.add( createCommentFromEntity( entity ) );
//            }
//        }
//        return comments;
//    }
//
//    private Comment createCommentFromEntity( Entity entity ) {
//
//        Comment comment = new Comment();
//        comment.setEmail( (String)entity.getProperty("email") );
//        comment.setText( (String)entity.getProperty("text") );
//        comment.setDate( (Date)entity.getProperty("date") );
//
//        return comment;
//    }
//
//}
