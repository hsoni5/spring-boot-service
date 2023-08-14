package org.soni.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageQueue<T> implements Serializable{
    private static final long serialVersionUID = -9064810662454642210L;
    private String correlationId;
    private List<T> data;
}