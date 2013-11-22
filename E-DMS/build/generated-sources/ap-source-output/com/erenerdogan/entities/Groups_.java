package com.erenerdogan.entities;

import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-11-22T04:39:39")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile SingularAttribute<Groups, Integer> gsubid;
    public static volatile CollectionAttribute<Groups, Users> usersCollection;
    public static volatile SingularAttribute<Groups, Integer> gid;
    public static volatile SingularAttribute<Groups, String> gname;
    public static volatile SingularAttribute<Groups, Integer> gstatus;
    public static volatile CollectionAttribute<Groups, GroupShared> groupSharedCollection;

}