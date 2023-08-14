package org.soni.util;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * @author XY49709
 */
@Data
@Getter
@Setter
public class PaginationResponseUtil {
    private PaginationResponseUtil() {

    }

    public static long totalNoOfRecords;
    public static long totalNoOfPages;
    public static String sort;
    public static String orderBy;
    public static Sort sorting;

    public static void addPaging(Page<?> page) {
        totalNoOfPages = page.getTotalPages();
        totalNoOfRecords = page.getTotalElements();
    }

    public static void addSortAndOrder(String sort, String orderBy) {
        sort = sort;
        orderBy = orderBy;
        sorting = Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, orderBy);
    }
}
