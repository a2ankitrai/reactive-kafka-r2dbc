package com.ank.rkr.util;

import com.ank.rkr.message.AddressMessage;
import com.ank.rkr.message.PersonMessage;
import com.ank.rkr.model.Address;
import com.ank.rkr.model.Person;
import com.google.gson.Gson;
import reactor.kafka.receiver.ReceiverRecord;

public class Mapper {

    public static PersonMessage mapRecordToPersonMessage(ReceiverRecord<String, String> record) {
        return mapStringToObject(record.value());
    }

    public static Person mapPersonMessageToPersonModel(PersonMessage personMessage) {
        Person p = new Person();
        p.setFirstName(personMessage.getFirstName());
        p.setLastName(personMessage.getLastName());
        p.setContactNumber(personMessage.getContactNumber());
        return p;
    }

    public static Address mapPersonMessageToAddressModel(AddressMessage addressMessage, final Long personId) {
        Address address = new Address();
        address.setPersonId(personId);
        address.setHouseNumber(addressMessage.getHouseNumber());
        address.setStreet(addressMessage.getStreet());
        address.setCity(addressMessage.getCity());
        address.setZipCode(addressMessage.getZipCode());
        return address;
    }


    private static PersonMessage mapStringToObject(String text) {
        Gson g = new Gson();
        return g.fromJson(text, PersonMessage.class);
    }
}
