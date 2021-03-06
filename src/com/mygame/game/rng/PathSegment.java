package com.mygame.game.rng;

import java.util.ArrayList;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PathSegment {
	
	private Vector2 start;
	private Vector2 end;
	private PathSegment parent;
	private float angle;
	private ArrayList<Vector3> pathData;
	
	public PathSegment(){
		start = new Vector2(0, 0);
		end = new Vector2(0, 0);
		this.angle = 0;
		parent = null;
		pathData = new ArrayList<Vector3>();
	}
	
	public PathSegment(float x1, float y1, float x2, float y2){
		start = new Vector2(x1, y1);
		end = new Vector2(x2, y2);
		this.angle = 0;
		parent = null;
		pathData = new ArrayList<Vector3>();
	}
	
	public PathSegment(Vector2 a, float length, float angle){
		start = a;
		end = new Vector2((float)(start.x + (length * Math.cos(angle))), (float)(start.y + (length * Math.sin(angle))));
		this.angle = angle;
		parent = null;
		pathData = new ArrayList<Vector3>();
	}
	
	public PathSegment(Vector2 a, float length, float angle, PathSegment parent){
		start = a;
		end = new Vector2((float)(start.x + (length * Math.cos(angle))), (float)(start.y + (length * Math.sin(angle))));
		this.angle = angle;
		this.parent = parent;
		pathData = new ArrayList<Vector3>();
	}
	
	public double startDistFromPlayer(float x, float y){
		return Math.sqrt(Math.pow((start.x - x), 2) + Math.pow((start.y - y), 2));
	}
	
	public double endDistFromPlayer(float x, float y){
		return Math.sqrt(Math.pow((end.x - x), 2) + Math.pow((end.y - y), 2));
	}
	
	public float getXLength(){
		return end.x - start.x;
	}
	
	public float getYLength(){
		return end.y - start.y;
	}
	
	public float getLength(){
		return (float)Math.sqrt(Math.pow(getXLength(), 2) + Math.pow(getYLength(), 2));
	}
	
	public float getAngle(){
		return angle;
	}
	
	public Vector2 getStart(){
		return start;
	}
	
	public Vector2 getEnd(){
		return end;
	}
	
	public PathSegment getParent(){
		return parent;
	}
	
	public ArrayList<Vector3> getPathData(){
		return pathData;
	}
	
	public void setPathData(ArrayList<Vector3> data){
		pathData = data;
	}
	
	public Vector2 getEndWithLength(float length){
		return new Vector2((float)(start.x + (length * Math.cos(angle))), (float)(start.y + (length * Math.sin(angle))));
	}
	
	public boolean getIntersection(PathSegment a){
		return Intersector.intersectSegments(start, end, a.getStart(), a.getEnd(), null);
	}
	
	public float getDistanceFromPoint(Vector2 a){
		return Intersector.distanceSegmentPoint(start, end, a);
	}
	
	

}
