package com.erenerdogan.entities;

import com.erenerdogan.entities.Comments;
import com.erenerdogan.entities.FileStatus;
import com.erenerdogan.entities.GroupShared;
import com.erenerdogan.entities.Tags;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-11-22T04:39:39")
@StaticMetamodel(Files.class)
public class Files_ { 

    public static volatile SingularAttribute<Files, Integer> fid;
    public static volatile CollectionAttribute<Files, Comments> commentsCollection;
    public static volatile SingularAttribute<Files, Integer> fstatus;
    public static volatile SingularAttribute<Files, String> fdescription;
    public static volatile SingularAttribute<Files, Date> frdate;
    public static volatile SingularAttribute<Files, FileStatus> ffsid;
    public static volatile CollectionAttribute<Files, Tags> tagsCollection;
    public static volatile CollectionAttribute<Files, GroupShared> groupSharedCollection;
    public static volatile SingularAttribute<Files, String> fname;
    public static volatile SingularAttribute<Files, String> fpath;

}