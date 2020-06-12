package com.giraffecookie.giraffecat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayDeque;

public class Level implements Updatable, Renderable, Pausable{

    ArrayDeque<Pausable> pq;
    ArrayDeque<Renderable> rq;
    ArrayDeque<Updatable> uq;

    public Level() {
        pq = new ArrayDeque<Pausable>();
        rq = new ArrayDeque<Renderable>();
        uq = new ArrayDeque<Updatable>();
    }

    public void update(float delta) {
        for (Updatable u : uq)
            u.update(delta);
    }

    public void render(Graphics g) {
        for (Renderable r : rq)
            r.render(g);
    }

    public void pause(){
        for (Pausable p : pq)
            p.pause();
    }

    public void play(){
        for (Pausable p : pq)
            p.play();
    }

    public void addObject(Object o){
        if (o instanceof Pausable){
            pq.add((Pausable)o);
        }
        if (o instanceof Renderable){
            rq.add((Renderable)o);
        }
        if (o instanceof Updatable){
            uq.add((Updatable)o);
        }
    }

}
