package com.giraffecookie.giraffecat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;

public class GiraffeCat {

    enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED
    }

    enum Facing {
        LEFT,
        RIGHT
    }

    final static String TAG = GiraffeCat.class.getName();
    final static float RUNACC = 5;
    final static float FRICTION = 1f;
    final static float JUMP_FRAME_DURATION = 0.1f;

    Facing facing;
    JumpState js;

    CollisionBox cc;
    GFCPhysics physics;
    Vector2 accelerationVector;
    Vector2 playerFacingDirection;

    Animation standingRight;
    Animation standingLeft;
    Animation jumpingRight;
    Animation jumpingLeft;
    Animation sprite;

    float runAcc = RUNACC;
    float friction = FRICTION;
    long jsTime;

    public GiraffeCat() {
        playerFacingDirection = new Vector2();
        accelerationVector = new Vector2();
        cc = new CollisionBox(120, 80, 36, 36);
        physics = new GFCPhysics(cc);
        standingRight = Animator.loadSprite(ImgPath.GFCRR4, 64, 64);
        standingLeft = Animator.loadSprite(ImgPath.GFCLR4, 64, 64);
        jumpingRight = Animator.loadSprite(
                ImgPath.GFCJR,
                64,
                64,
                JUMP_FRAME_DURATION,
                3);
        jumpingLeft = Animator.loadSprite(
                ImgPath.GFCJL,
                64,
                64,
                JUMP_FRAME_DURATION,
                3);
        sprite = jumpingRight;
        facing = Facing.RIGHT;
        js = JumpState.FALLING;
        //cc.forceTo(120, 80);
        init();
    }

    public void init(){
        accelerationVector.set(0, 0);
        playerFacingDirection.set(1, 0); // Facing right
        physics.setVelocity(0, 0);
    }


    public void update(float delta) {
        //Gravity
        addForce(0, Constants.GRAVITY * delta);

        //jump state
        if (js != JumpState.JUMPING) {
            js = JumpState.FALLING;
        }

        //landing on the "ground"
        if (cc.getY() + cc.getHeight() > GiraffeCatGame.MODEL_HEIGHT) {
            js = JumpState.GROUNDED;
            cc.setY(GiraffeCatGame.MODEL_HEIGHT - cc.getHeight());
            physics.setVelocityY(0);
        }

        //input
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            switch (js) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }
        } else {
            endJump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta);
        }

        //x-axis friction
        if (physics.velocity.x != 0f)
            if (Math.abs(Math.signum(physics.velocity.x)*friction*delta) > Math.abs(physics.velocity.x))
                physics.velocity.x = 0;
            else
                addForce(-1*Math.signum(physics.velocity.x)*friction*delta, 0);

        physics.update(delta);
        sprite.update(delta);
    }

    private void startJump() {
        js = JumpState.JUMPING;
        jsTime = TimeUtils.nanoTime();
        switch (facing){
            case RIGHT:
                jumpingRight.restart();
                sprite = jumpingRight;
                break;
            case LEFT:
                jumpingLeft.restart();
                sprite = jumpingLeft;
                break;
        }

        continueJump();
    }

    private void continueJump() {
        if (js == JumpState.JUMPING) {
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jsTime);
            if (jumpDuration < Constants.MAX_JUMP_DURATION) {
                addForce(0, Constants.JUMP_SPEED);;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        if (js == JumpState.JUMPING) {
            js = JumpState.FALLING;
        }
    }

    public void moveLeft(float delta){

        switch (js){
            case JUMPING:
                //If we change facing in the middle of jumping
                if (facing == Facing.RIGHT) {
                    jumpingLeft.restart();
                    jumpingLeft.update(jumpingLeft.getCurrentFrameIndex() * JUMP_FRAME_DURATION);
                    sprite = jumpingLeft;
                }
                break;
            case FALLING:
                break;
            case GROUNDED:
                sprite = standingLeft;
                break;
        }
        facing = Facing.LEFT;
        playerFacingDirection.set(-1, 0);
        addForce(playerFacingDirection.x * runAcc * delta, 0);
    }

    public void moveRight(float delta){

        switch (js){
            case JUMPING:
                if (facing == Facing.LEFT) {
                    jumpingRight.restart();
                    jumpingRight.update(jumpingLeft.getCurrentFrameIndex() * JUMP_FRAME_DURATION);
                    sprite = jumpingRight;
                }
                break;
            case FALLING:
                break;
            case GROUNDED:
                sprite = standingRight;
                break;
        }
        facing = Facing.RIGHT;
        playerFacingDirection.set(1, 0);
        addForce(playerFacingDirection.x * runAcc * delta, 0);
    }

    public void addForce(float x, float y){
        accelerationVector.set(x,y);
        physics.addForce(accelerationVector);
    }

    public void render(Graphics g) {
        cc.draw(g);
        sprite.draw(g,
                cc.getX() - sprite.getCurrentFrame().getWidth() / 4,
                cc.getY() - sprite.getCurrentFrame().getHeight() / 4);
        g.drawString(""+Gdx.input.isKeyPressed(Input.Keys.RIGHT), 0, 0);
    }
}
