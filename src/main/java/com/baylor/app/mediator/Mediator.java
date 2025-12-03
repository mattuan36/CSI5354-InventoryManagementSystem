package com.baylor.app.mediator;

public interface Mediator {

    public Long getAvailableSpace(String locationId);

    public Boolean isReserved(String vendorId, String locationId);

    public String reserveLocation(String vendorId, String locationId);
}

