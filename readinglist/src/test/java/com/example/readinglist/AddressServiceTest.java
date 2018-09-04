package com.example.readinglist;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meng.configuration.AddressBookConfiguration;
import com.meng.entity.Address;
import com.meng.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AddressBookConfiguration.class, AddressService.class})
public class AddressServiceTest {
    @Autowired
    private  AddressService addressService;

    @Test
    public void testService(){
        Address address = addressService.findByLastName("Schema");
        assertEquals("1", address.getFirstName());
        assertEquals("2", address.getLastName());
        assertEquals("3", address.getAddressLine());
        assertEquals("4", address.getCity());
        assertEquals("5", address.getState());
        assertEquals("6", address.getPostCode());
    }
}
