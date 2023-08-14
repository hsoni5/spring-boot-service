package org.soni.dto;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GFPaymentsDto implements Serializable {/**
 *
 */
private static final long serialVersionUID = 1L;

    private String company;
    private String territory;
    private String department;
    private String datefrom;
    private String dateTo;
    private String payDate;
    private BigDecimal amount;
    private BigDecimal vat;
    private Long disburseRef;
    private String status;
    private String disburseTo;
    private String practice;
    private Long  payee;
    private Long  paymentInstance;
}
