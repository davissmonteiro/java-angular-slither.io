package com.example.slitherio.backend.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Snake {
    private final String playerId;
    private List<Coordinate> body;
    private double direction;
    private int growthPending = 0;

    public Snake(String playerId, List<Coordinate> initialBody, double direction) {
        this.playerId = Objects.requireNonNull(playerId);
        Objects.requireNonNull(body, "body cannot be null");
        if (initialBody == null ||  initialBody.isEmpty()) {
            throw new IllegalArgumentException("The snake's body cannot be null or empty");
        }

        this.body = new ArrayList<>(initialBody);
        this.direction = direction;
    }
    
    public String getPlayerId() { return playerId; }
    public List<Coordinate> getBody() { return Collections.unmodifiableList(body); }
    public double getDirection() { return direction; }
    public Coordinate getHead() { return body.get(0); }
    
    public void setDirection(double direction) { this.direction = direction; }
    public void grow(int amount) {
        this.growthPending += amount;
    }
    
    public void move(double speed) {
        Coordinate oldHead = getHead();
        int newX = (int) Math.round(oldHead.x() + speed * Math.cos(direction));
        int newY = (int) Math.round(oldHead.y() + speed * Math.sin(direction));
        Coordinate newHead = new Coordinate(newX, newY);

        body.add(0, newHead);

        if (growthPending <= 0) {
            body.remove(body.size() - 1);
        } else {
            growthPending--;
        }
    }
}
