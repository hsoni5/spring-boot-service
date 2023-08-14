package org.soni.service;



import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class GFPaymentEnquiryServiceImpl  {

//
//
//    /**
//     * @param size
//     * @param page
//     * @param department
//     * @return
//     */
//    @Override
//    public List<GFDepartmentDto> getDepartment(int size, int page, String department) {
//        Pageable paging = PageRequest.of(page, size, PaginationResponseUtil.sorting);
//        Page<GFDepartment> pagedResult = null;
//        List<GFDepartmentDto> gfDptDtoList = new ArrayList<>();
//        pagedResult = gfDepartmentRepository.findAll(GFPaymentEnquirySpecification.likeDept(GFProcessConstants.GFPROCESS_DEPARTMENT
//                        , department),
//                paging);
//        PaginationResponseUtil.addPaging(pagedResult);
//        if (pagedResult.hasContent()) {
//            pagedResult.forEach(gf -> {
//                GFDepartmentDto dto = new GFDepartmentDto();
//                dto.setDepartment(gf.getDepartment());
//                dto.setDescription(gf.getDescription());
//                gfDptDtoList.add(dto);
//            });
//        } else {
//            throw new NoRecordsFoundException(GFProcessConstants.GFPROCESS_NO_RECORDS_FOUND);
//        }
//        return gfDptDtoList;
//
//    }
//
//    /**
//     * @param size
//     * @param page
//     * @param gfPaymentsDto
//     * @return
//     */
//    @Override
//    public List<GFPaymentsDto> searchPaymentEnquiry(int size, int page, GFPaymentsDto gfPaymentsDto) {
//        Pageable paging = PageRequest.of(page, size, PaginationResponseUtil.sorting);
//        Page<GFPayments> pagedResult = null;
//        if (Objects.nonNull(gfPaymentsDto)) {
//            pagedResult = gfPaymentsRepository.findAll(GFPaymentEnquirySpecification
//                            .idLike(GFProcessConstants.GFPROCESS_COMPANY, gfPaymentsDto.getCompany())
//                            .and(GFPaymentEnquirySpecification.idLike(GFProcessConstants.GFPROCESS_TERRITORY,
//                                    gfPaymentsDto.getTerritory()))
//                            .and(GFPaymentEnquirySpecification.likeId(GFProcessConstants.GFPROCESS_DEPARTMENT,
//                                    gfPaymentsDto.getDepartment()))
//                            .and(GFPaymentEnquirySpecification.like(GFProcessConstants.GFPROCESS_STATUS,
//                                    gfPaymentsDto.getStatus()))
//                            .and(GFPaymentEnquirySpecification.likePayee(GFProcessConstants.GFPROCESS_DISBURSED_TO,
//                                    gfPaymentsDto.getDisburseTo()))
//                            .and(GFPaymentEnquirySpecification.between(GFProcessConstants.GFPROCESS_PAYDATE,
//                                    DateTimeUtils.yyyyMMDDFormat(gfPaymentsDto.getDatefrom()), DateTimeUtils.yyyyMMDDFormat(gfPaymentsDto.getDateTo()))),
//                    paging);
//            PaginationResponseUtil.addPaging(pagedResult);
//        }
//        if (Objects.nonNull(pagedResult) && pagedResult.hasContent()) {
//            return transformEntitiesToDtoList(pagedResult.getContent());
//        } else {
//            throw new NoRecordsFoundException(GFProcessConstants.GFPROCESS_NO_RECORDS_FOUND);
//        }
//
//    }
//
//    /**
//     * @param gfPayments
//     * @return
//     */
//    public List<GFPaymentsDto> transformEntitiesToDtoList(List<GFPayments> gfPayments) {
//        return gfPayments.stream().map(this::buildEntityToDto).collect(Collectors.toList());
//    }
//
//    /**
//     * @param sobj
//     * @return
//     */
//    public GFPaymentsDto buildEntityToDto(GFPayments sobj) {
//        String practice = getPractice(sobj.getPayee());
//        return GFPaymentsDto.builder()
//                .company(sobj.getGfPaymentsId().getCompany())
//                .amount(sobj.getAmount())
//                .department(sobj.getGfPaymentsId().getDepartment())
//                .disburseRef(sobj.getSrcSysrefNo())
//                .disburseTo(sobj.getGfpayees() != null ? sobj.getGfpayees().getAccountName() : "")
//                .payDate(DateTimeUtils.ddMMYyyyFormat(sobj.getGfPaymentsId().getPayDate()))
//                .status(sobj.getStatus())
//                .territory(sobj.getGfPaymentsId().getTerritory()).payee(sobj.getPayee())
//                .paymentInstance(sobj.getGfPaymentsId().getPaymentInstance()).practice(practice).build();
//    }
//
//    /**
//     * @param payee
//     * @return
//     */
//    public String getPractice(Long payee) {
//        GFPractices gfPractices = null;
//        GFAdmin gfAdmin = null;
//        GFInstitute gfInstitute = null;
//        System.out.println("payee number " + payee);
//        gfPractices = gfPracticesRepository.findByPayee(payee);
//        if(ObjectUtils.isNotEmpty(gfPractices)){
//            return gfPractices.getGfPracticesId().getPractice();
//        }else if(ObjectUtils.isEmpty(gfPractices)){
//            gfAdmin = gfAdminRepository.findByPayee(payee);
//            if(ObjectUtils.isNotEmpty(gfAdmin)){
//                return gfAdmin.getGfAdminId().getAdmin();
//            }
//        } else {
//            if(ObjectUtils.isEmpty(gfPractices)) {
//                ObjectUtils.isEmpty(gfAdmin);
//                gfInstitute = gfInstituteRepository.findByPayee(payee);
//                if (ObjectUtils.isNotEmpty(gfInstitute)) {
//                    return gfInstitute.getGfInstituteId().getInstitute();
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * @param gfPaymentsDto
//     * @return
//     */
//    @Override
//    public List<GFPaymentsReqRefDto> getPaymentEnquiryDetails(GFPaymentsDto gfPaymentsDto) {
//        List<GFPaymentsMade> gfPayments = gfPaymentsMadeRepository.findByPaymentsMade(gfPaymentsDto.getCompany(),
//                gfPaymentsDto.getTerritory(), gfPaymentsDto.getDepartment(), gfPaymentsDto.getPayee(),
//                DateTimeUtils.yyyyMMDDFormat(gfPaymentsDto.getPayDate()), gfPaymentsDto.getPaymentInstance());
//        List<GFPaymentsReqRefDto> gfPaymentsReqRefDtoList = new ArrayList<>();
//        if (!ObjectUtils.isEmpty(gfPayments)) {
//            gfPayments.stream().forEach(gfPayment -> {
//                List<GFIfaceReqRef> gfIfaceReqRefList = gfIfaceReqRefRepository.findByReference(gfPayment.getGfPaymentsMadeId().getReferenceValue());
//                String reference= gfIfaceReqRefList.stream().map(gfIfaceReqRef -> gfIfaceReqRef.getGfIfaceReqRefId().getType() + " " + gfIfaceReqRef.getValue()).collect(Collectors.joining(", "));
//                gfPaymentsReqRefDtoList.add(GFPaymentsReqRefDto.builder().amount(gfPayment.getAmount()).dueDate(DateTimeUtils.ddMMYyyyFormat(gfPayment.getDateDue()))
//                        .practice(gfPayment.getPractice())
//                        .reference(reference)
//                        .testCode(gfPayment.getTariffCode()).testDate(DateTimeUtils.ddMMYyyyFormat(gfPayment.getDateOfTest())).vat(gfPayment.getVat())
//                        .build());
//            });
//        }
//        return gfPaymentsReqRefDtoList;
//    }
}