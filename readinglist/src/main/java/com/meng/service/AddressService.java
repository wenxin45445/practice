package com.meng.service;

import org.springframework.stereotype.Service;

import com.meng.entity.Address;

@Service
public class AddressService {
    public Address findByLastName(String schema) {
        return new Address("1","2","3","4","5","6");
    }
}
