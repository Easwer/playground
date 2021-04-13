package com.easwer.collection.streams;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private int id;

    private String name;

    private Date birthday;

    private String location;
    
}
