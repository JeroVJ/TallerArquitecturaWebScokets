package org.example.websockets;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LocationSimulator {

    private final SimpMessagingTemplate messagingTemplate;
    private double currentLat = 40.416775;  // Madrid
    private double currentLon = -3.703790;

    public LocationSimulator(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void simulateLocation() {
        LocationData location = new LocationData();

        // Simular movimiento
        currentLat += (Math.random() - 0.5) * 0.001;
        currentLon += (Math.random() - 0.5) * 0.001;

        location.setLat(currentLat);
        location.setLon(currentLon);
        location.setSpeed(Math.random() * 50);  // Velocidad simulada
        location.setHeading(Math.random() * 360);  // Dirección simulada

        messagingTemplate.convertAndSend("/topic/location", location);
    }
}
