package com.baylor.app.mediatorAspect;

import com.baylor.app.service.LocationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AspectMediator {

    @Autowired
    private LocationService locationService;

    @Around("execution(* com.baylor.app.service.VendorService.getAvailableSpace(..))")
    public Long availabilityAspect(ProceedingJoinPoint joinPoint) {
        return locationService.getAvailableSpace(joinPoint.getArgs()[0].toString());
    }

    @Around("execution(* com.baylor.app.service.VendorService.reserveLocation(..))")
    public String reserveLocationAspect(ProceedingJoinPoint joinPoint) {
        return locationService.reserveLocation(joinPoint.getArgs()[0].toString(), joinPoint.getArgs()[1].toString());
    }
}
