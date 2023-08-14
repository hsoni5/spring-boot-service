package org.soni.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
    protected String createdBy;

    private Date createdDate;

    protected String modifiedBy;

    protected Date modifiedDate;

}