package com.erenerdogan.entities;

import com.erenerdogan.entities.Files;
import com.erenerdogan.entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-11-22T04:39:39")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, String> cdescription;
    public static volatile SingularAttribute<Comments, Integer> cstatus;
    public static volatile SingularAttribute<Comments, Users> cuid;
    public static volatile SingularAttribute<Comments, String> ctitle;
    public static volatile SingularAttribute<Comments, Date> cdate;
    public static volatile SingularAttribute<Comments, Integer> cid;
    public static volatile SingularAttribute<Comments, Files> cfid;

}