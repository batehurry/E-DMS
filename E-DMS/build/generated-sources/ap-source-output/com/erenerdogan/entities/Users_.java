package com.erenerdogan.entities;

import com.erenerdogan.entities.Comments;
import com.erenerdogan.entities.Groups;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-11-22T04:39:39")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Integer> uid;
    public static volatile CollectionAttribute<Users, Comments> commentsCollection;
    public static volatile SingularAttribute<Users, String> uname;
    public static volatile CollectionAttribute<Users, Groups> groupsCollection;
    public static volatile SingularAttribute<Users, String> upassword;
    public static volatile SingularAttribute<Users, Date> urdate;
    public static volatile SingularAttribute<Users, String> uemail;
    public static volatile SingularAttribute<Users, String> usurname;
    public static volatile SingularAttribute<Users, Integer> ustatus;

}