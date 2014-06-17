package com.jcui.pencilpilot;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * @author jcui
 *
 */
public final class Collider
{
	public static interface RemovalHandler<T extends GameObject>
    {
        void onRemove(T t);
    }

    public static interface ColliderHandler<T extends GameObject, U extends GameObject>
    {
        void onCollision(T t, U u);
    }

    public static interface SceneryHandler<T extends GameObject>
    {
        void onCollision(T t, Rectangle r);
    }
	
	/*
	 * @return true if the rectangles intersect, otherwise false.
	 */
	public static boolean intersects(Rectangle a, Rectangle b)
    {
        return (a.x + a.width > b.x) && (a.x < b.x + b.width)
                && (a.y + a.height > b.y) && (a.y < b.y + b.height);
    }
	
	/*
	 * @return true if the rectangle intersects with any rectangle in an Array.
	 */
	public static boolean intersects(Rectangle a, Array<Rectangle> rects)
    {
        for (int i = rects.size - 1; i >= 0; i--)
        {
            Rectangle b = rects.get(i);
            if (intersects(a, b))
            {
                return true;
            }
        }
        return false;
    }
	
	public static <V extends GameObject, W extends GameObject, T extends V, U extends W> void collide(
            T a, U b, ColliderHandler<V, W> callback)
    {
        if (a != b)
        {
            if (a.intersects(b))
            {
                callback.onCollision(a, b);
            }
        }
    }
  
}