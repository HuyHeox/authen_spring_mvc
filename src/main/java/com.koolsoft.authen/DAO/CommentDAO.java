package com.koolsoft.authen.DAO;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentDAO { long save(Comment comment ); List getAll(int count ); }
