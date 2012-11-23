package com.racetrack.bean

/**
 * Created with IntelliJ IDEA.
 * User: Muffin
 * Date: 11/23/12
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
class RaceBean {
    String name
    String city
    List registrations

    public List getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List registrations) {
        this.registrations = registrations;
    }
}
