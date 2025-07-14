package practice;

import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    private Date createdDate;

    private Date lastModifiedDate;
}
