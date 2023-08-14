package org.soni.controller;


import org.soni.dto.GFPaymentsDto;
import org.soni.exception.NoRecordsFoundException;
import org.soni.response.PaginationResponse;
import org.soni.response.ProcessResponse;
import org.soni.util.PaginationResponseUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "inquiry")
public class PaymentsEnquiryController {
//
////    @Autowired
////    GFPaymentEnquiryServiceImpl gfPaymentEnquiryServiceImpl;
//
//    /**
//     * @param page
//     * @param size
//     * @param department
//     * @param sort
//     * @param orderBy
//     * @return
//     * @throws NullPointerException
//     */
//    @GetMapping(path = "AppUrlConstant.GETDEPARTMENT")
//    public PaginationResponse getDepartment(@RequestParam(defaultValue = "0") int page,
//                                            @RequestParam(defaultValue = "3") int size,
//                                            @RequestHeader(value = "department", required = false) String department,
//                                            @RequestHeader(value = "sort", defaultValue = "GFProcessConstants.GFENQUIRY_DEFAULT_SORT", required = false) String sort,
//                                            @RequestHeader(value = "orderBy", defaultValue = "GFProcessConstants.GFENQUIRY_DEFAULT_DEPARTMENT", required = false) String orderBy)
//            throws NoRecordsFoundException, NullPointerException {
//        PaginationResponseUtil.addSortAndOrder(sort, orderBy);
//        return new PaginationResponse("GFProcessConstants.STATUS_SUCCESS",
//                "gfPaymentEnquiryServiceImpl.getDepartment(size, page, department)",
//                PaginationResponseUtil.totalNoOfRecords,
//                PaginationResponseUtil.totalNoOfPages);
//
//    }
//
//    /**
//     * @param page
//     * @param size
//     * @param sort
//     * @param orderBy
//     * @param gfPaymentsDto
//     * @return
//     * @throws NullPointerException
//     */
//    @PostMapping(path = "AppUrlConstant.SEARCH")
//    public PaginationResponse search(@RequestParam(defaultValue = "0") int page,
//                                     @RequestParam(defaultValue = "3") int size,
//                                     @RequestHeader(value = "sort", defaultValue = "GFProcessConstants.GFENQUIRY_DEFAULT_SORT", required = false) String sort,
//                                     @RequestHeader(value = "orderBy", defaultValue = "GFProcessConstants.GFENQUIRY_DEFAULT_ORDER", required = false) String orderBy,
//                                     @RequestBody(required = false) GFPaymentsDto gfPaymentsDto)
//            throws NoRecordsFoundException, NullPointerException {
//        PaginationResponseUtil.addSortAndOrder(sort, orderBy);
//        return new PaginationResponse("GFProcessConstants.STATUS_SUCCESS",
//                "   gfPaymentEnquiryServiceImpl.searchPaymentEnquiry(size, page, gfPaymentsDto)",
//                PaginationResponseUtil.totalNoOfRecords,
//                PaginationResponseUtil.totalNoOfPages);
//
//    }
//
//    /**
//     * @param gfPaymentsDto
//     * @return
//     * @throws NullPointerException
//     */
//    @PostMapping(path = "search")
//    public ProcessResponse paymentDetails(@RequestBody(required = false) GFPaymentsDto gfPaymentsDto)
//            throws NoRecordsFoundException, NullPointerException {
//        return new ProcessResponse("GFProcessConstants.STATUS_SUCCESS",
////                gfPaymentEnquiryServiceImpl.getPaymentEnquiryDetails(gfPaymentsDto));
//
//    }

}