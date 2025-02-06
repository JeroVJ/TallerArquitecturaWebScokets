package org.example.websockets;

public class LocationData {
    private double lat;
    private double lon;
    private double speed;
    private double heading;

    // Getters y setters
    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }
    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public double getHeading() { return heading; }
    public void setHeading(double heading) { this.heading = heading; }
}