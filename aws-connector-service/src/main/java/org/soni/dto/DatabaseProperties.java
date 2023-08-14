package org.soni.dto;


import lombok.Data;

@Data
public class DatabaseProperties{
    private String username;
    private String password;
    private String engine;
    private String host;
    private int port;
    private String dbname;
    private String dbInstanceIdentifier;
}
