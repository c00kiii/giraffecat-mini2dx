package com.giraffecookie.giraffecat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayDeque;

public class GiraffeCat implements Pausable, Renderable, Updatable {

    enum JumpState {
        JUMPING,
        FALLING,
        GROUNDED
    }

    enum RunState {
        RUNNING,
        STILL
    }

    enum Facing {
        LEFT,
        RIGHT
    }

    final static String TAG = GiraffeCat.class.getName();
    final static float RUNACC = 30;
    final static float FRICTION = 20f;
    static final float JUMP_SPEED = -6.2f;
    final static float JUMP_FRAME_DURATION = 0.1f;
    final static float RUN_FRAME_DURATION = 0.05f;
    final static float MAX_VELOCITY_X = 5;
    final static float FEET_HEIGHT = 10;
    final static float HEAD_HEIGHT = 40;
    final static float FOOT_PADDING_R = 16;
    final static float FOOT_PADDING_L = 22;
    final static float HEAD_PADDING_R  = 34;
    final static float HEAD_PADDING_L = 12;
    final static float COLLISION_THRESHOLD = 1;

    Facing facing;
    JumpState js;
    RunState rs;

    CollisionBox cBox;
    CollisionBox hBox;
    GFCPhysics physics;
    Vector2 accelerationVector;
    Vector2 playerFacingDirection;

    Animation runningRight;
    Animation runningLeft;
    Animation jumpingRight;
    Animation jumpingLeft;
    Animation standingRight;
    Animation standingLeft;
    Animation sprite;

    ArrayDeque<Platform> platforms;

    float runAcc = RUNACC;
    float friction = FRICTION;
    float footPadding = FOOT_PADDING_R;
    float headPadding = HEAD_PADDING_R;
    long jsTime;
    boolean paused;
    boolean aboveSolid;

    public GiraffeCat(ArrayDeque<Platform> platforms) {
        this.platforms = platforms;
        playerFacingDirection = new Vector2();
        accelerationVector = new Vector2();
        cBox = new CollisionBox(120, 80, 26, 18);
        hBox = new CollisionBox(
                cBox.getRenderX() - footPadding + headPadding,
                cBox.getRenderY() + cBox.getRenderHeight() - HEAD_HEIGHT,
                18,
                18
        );
        physics = new GFCPhysics(cBox);
        runningRight = Animator.loadSprite(
                ImgPath.GFCRR,
                64,
                64,
                RUN_FRAME_DURATION,
                8
        );
        runningRight.setLooping(true);
        runningLeft = Animator.loadSprite(
                ImgPath.GFCLR,
                64,
                64,
                RUN_FRAME_DURATION,
                8
        );
        runningLeft.setLooping(true);
        jumpingRight = Animator.loadSprite(
                ImgPath.GFCJR,
                64,
                64,
                JUMP_FRAME_DURATION,
                3
        );
        jumpingLeft = Animator.loadSprite(
                ImgPath.GFCJL,
                64,
                64,
                JUMP_FRAME_DURATION,
                3
        );
        standingRight = Animator.loadSprite(
                ImgPath.GFCSR,
                64,
                64
        );
        standingLeft = Animator.loadSprite(
                ImgPath.GFCSL,
                64,
                64
        );
        sprite = jumpingRight;
        facing = Facing.RIGHT;
        js = JumpState.FALLING;
        rs = RunState.STILL;
        paused = false;
        aboveSolid = false;
        init();
    }

    public void init(){
        accelerationVector.set(0, 0);
        playerFacingDirection.set(1, 0); // Facing right
        physics.setVelocity(0, 0);
    }


    public void update(float delta) {
        if (!paused) {
            aboveSolid = false;
            for (Platform p : platforms)
                if (cBox.intersects(p.cb))
                    if (cBox.getY() + cBox.getHeight() > p.cb.getRenderY()
                            && cBox.getY() + cBox.getHeight() < p.cb.getRenderY() + COLLISION_THRESHOLD){
                        aboveSolid = true;
                        js = JumpState.GROUNDED;
                        cBox.setY(p.cb.getRenderY() - cBox.getHeight());
                        physics.setVelocityY(0);
                    }
            if (!aboveSolid)
                if (js == JumpState.GROUNDED)
                    js = JumpState.FALLING;
            //Gravity
            if (js != JumpState.GROUNDED)
                addForce(0, Constants.GRAVITY * delta);

            //run state
            rs = RunState.STILL;

            //landing on the "ground"
            if (cBox.getY() + cBox.getHeight() > GiraffeCatGame.MODEL_HEIGHT) {
                js = JumpState.GROUNDED;
                cBox.setY(GiraffeCatGame.MODEL_HEIGHT - cBox.getHeight());
                physics.setVelocityY(0);
            }

            //input
            switch (js) {
                case GROUNDED:
                    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                        startJump();
                    }
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveLeft(delta);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveRight(delta);
            }
            if (rs == RunState.STILL) {
                if (js == JumpState.GROUNDED) {
                    switch (facing) {
                        case LEFT:
                            sprite = standingLeft;
                            break;
                        case RIGHT:
                            sprite = standingRight;
                            break;
                    }
                }
            }

            //x-axis friction
            if (physics.velocity.x != 0f)
                if (Math.abs(Math.signum(physics.velocity.x) * friction * delta) > Math.abs(physics.velocity.x))
                    physics.velocity.x = 0;
                else
                    addForce(-1 * Math.signum(physics.velocity.x) * friction * delta, 0);
            if (Math.abs(physics.velocity.x) > MAX_VELOCITY_X) {
                physics.velocity.x = Math.signum(physics.velocity.x) * MAX_VELOCITY_X;
            }

            //update physics
            physics.update(delta);



            //update head box position
            hBox.forceTo(
                    cBox.getRenderX() - footPadding + headPadding,
                    cBox.getRenderY() + cBox.getRenderHeight() - HEAD_HEIGHT
            );
            sprite.update(delta);
        }
    }

    public void pause(){
        paused = true;
    }

    public void play(){
        paused = false;
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
            addForce(0, JUMP_SPEED);;
            endJump();
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
                if (facing == Facing.RIGHT) {
                    sprite = jumpingLeft;
                }
                break;
            case GROUNDED:
                sprite = runningLeft;
                break;
        }
        facing = Facing.LEFT;
        rs = RunState.RUNNING;
        footPadding = FOOT_PADDING_L;
        headPadding = HEAD_PADDING_L;
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
                if (facing == Facing.LEFT) {
                    sprite = jumpingRight;
                }
                break;
            case GROUNDED:
                sprite = runningRight;
                break;
        }
        facing = Facing.RIGHT;
        rs = RunState.RUNNING;
        footPadding = FOOT_PADDING_R;
        headPadding = HEAD_PADDING_R;
        playerFacingDirection.set(1, 0);
        addForce(playerFacingDirection.x * runAcc * delta, 0);
    }

    public void addForce(float x, float y){
        accelerationVector.set(x,y);
        physics.addForce(accelerationVector);
    }

    public void render(Graphics g) {
        //g.drawString(""+aboveSolid, 0, 0);
        cBox.draw(g);
        hBox.draw(g);
        sprite.draw(g,
                cBox.getRenderX() - footPadding,
                cBox.getRenderY() + FEET_HEIGHT - (sprite.getCurrentFrame().getHeight() - cBox.getRenderHeight())
        );

    }
}
