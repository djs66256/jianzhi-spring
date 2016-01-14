package com.jianzhi.controller;

import com.jianzhi.core.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json/user/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    private boolean validateLocation(double longitude, double latitude) throws Exception {
        //TODO:.....
        return true;
    }

/*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateLocation(@RequestParam(required = false) String longitudeStr,
                                 @RequestParam(required = false) String latitudeStr,
                                 HttpServletRequest request) {
        try {
            double longitude = new Double(longitudeStr);
            double latitude = new Double(latitudeStr);
            validateLocation(longitude, latitude);
            User user = (User) request.getSession().getAttribute("user");
            Location location = user.getLocation();
            if (location == null) {
                location = new Location();
                location.setUser(user);
            }
            location.setLongitude(longitude);
            location.setLatitude(latitude);
            locationService.save(location);

            return new ReturnMessage(ReturnMessage.SUCCESS);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }
*/
}
