package com.giraffecookie.giraffecat;

import com.badlogic.gdx.math.Vector2;

import org.mini2Dx.core.engine.Positionable;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;

public class GFCPhysics {

    Positionable position;
    Vector2 velocity;

    GFCPhysics(Positionable position) {
        this.position = position;
        velocity = new Vector2();
    }

    public void update(float delta) {
        updatePositionWithVelocity();
    }

    public void addForce(Vector2 force) {
        velocity.add(force);
    }

    public void addForceReverse(Vector2 force) {
        velocity.sub(force);
    }

    private void updatePositionWithVelocity() {
        ((CollisionBox)position).forceTo(
                velocity.x + position.getX(),
                velocity.y + position.getY());

    }

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }
}
