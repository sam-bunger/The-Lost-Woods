package com.mygame.game.handlers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygame.game.B2D.B2DVars;
import com.mygame.game.entities.Attack;
import com.mygame.game.entities.GameObj;
import com.mygame.game.entities.HealthBar;

public class InteractionManager {
	private Array<Body> bodies;
	private ArrayList<Attack> attackQue;
	private World world;
	
	public InteractionManager(World world){
		this.world = world;
		bodies = new Array<Body>();
		attackQue = new ArrayList<Attack>();
		world.getBodies(bodies);
		createContactListener(world);
	}
	
	public void newAttack(Attack a){
		attackQue.add(a);
	}
	
	public void deleteAttack(){
		attackQue.remove(0);
	}
	
	public void createContactListener(World w){
		w.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                Object fixtureA = contact.getFixtureA().getBody().getUserData();
                Object fixtureB = contact.getFixtureB().getBody().getUserData();
                
                //Gdx.app.log("beginContact", "FixtureA: " + fixtureA +  "    FixtureB: " + fixtureB);

                if(fixtureA instanceof Attack) { 
            		if(fixtureB instanceof GameObj) {
            			((GameObj) fixtureB).setHealth(((GameObj) fixtureB).getHealth() - ((Attack) fixtureA).getDamage());
            			System.out.println(fixtureB + " health is currently " + ((GameObj) fixtureB).getHealth());
            		}
                }else if(fixtureB instanceof Attack) {
                	if(fixtureA instanceof GameObj) {
                		((GameObj) fixtureA).setHealth(((GameObj) fixtureA).getHealth() - ((Attack) fixtureB).getDamage());
            			System.out.println(fixtureA + " health is currently " + ((GameObj) fixtureA).getHealth());
            		}
                }

            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                //Gdx.app.log("beginContact", "between " + fixtureA.getBody().getUserData() + " and " + fixtureB.getBody().getUserData());
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
	}
}