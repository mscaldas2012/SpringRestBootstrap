package edu.msc.spring.bootstrap.model;

import lombok.Data;
/**
 * Created by marcelo on 11/7/16.
 */
@Data
public class UserAccount {
    public enum ACCOUNT_STATUS { ACTIVE, CLOSED, SUSPENDED}
    private long id;
    private String username;
    private String fullName;
    private String email;
    private ACCOUNT_STATUS accountStatus;
}
