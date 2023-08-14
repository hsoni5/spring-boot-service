package org.soni.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author XY49709
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 7114597314408235329L;
    private String status;
    private Object data;
    private long totalNoOfRecords;
    private long totalNoOfPages;
}
