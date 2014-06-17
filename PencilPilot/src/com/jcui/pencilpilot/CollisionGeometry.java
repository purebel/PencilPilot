package com.jcui.pencilpilot;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CollisionGeometry {

	private final Array<Rectangle> rectArray;
	private final Rectangle rect;
	
	public CollisionGeometry(Array<Rectangle> rectArray)
    {
        this.rectArray = rectArray;
        rect = new Rectangle();
    }
	
	public boolean intersects(Rectangle other, float x, float y)
    {
        boolean result = false;
        for (int i = 0; i < rectArray.size; i++)
        {
            Rectangle run = rectArray.get(i);
            setRectangle(rect, x + run.x, y + run.y, run.width, run.height);
            if (Collider.intersects(rect, other))
            {
                result = true;
                break;
            }
        }
        return result;
    }
	 public boolean intersects(float x, float y, CollisionGeometry other,
	            float otherX, float otherY)
	    {
	        boolean result = false;
	        for (int i = 0; i < rectArray.size; i++)
	        {
	            Rectangle run = rectArray.get(i);
	            setRectangle(rect, x + run.x, y + run.y, run.width, run.height);
	            if (other.intersects(rect, otherX, otherY))
	            {
	                result = true;
	                break;
	            }
	        }
	        return result;
	    }
	 
	 public void setRectangle(Rectangle r, float x, float y, float w, float h)
	    {
	        r.x = x;
	        r.y = y;
	        r.width = w;
	        r.height = h;
	    }
}
