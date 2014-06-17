package com.jcui.pencilpilot;

import com.badlogic.gdx.math.Rectangle;

public class GameObject {

	public static final int STATE_NORMAL = 1;
	public static final int STATE_DEAD = 1;

	// Object position and size
	protected float x;
	protected float y;
	private float width;
	private float height;
	
	private boolean inCollision;
	private boolean isDemandRemove;
	private Rectangle bounds;
	
	protected float stateTime;
	protected int state;
	protected CollisionGeometry geometry;

	public GameObject() {
		bounds = new Rectangle();
		state = STATE_NORMAL;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void translate(float offsetX, float offsetY) {
		this.x += offsetX;
		this.y += offsetY;
	}

	public Rectangle bounds() {
		bounds.x = getX();
		bounds.y = getY();
		bounds.width = getWidth();
		bounds.height = getHeight();
		return bounds;
	}

	/**
	 * Returns true if this game object's bounds intersect with the given game
	 * object.
	 */
	public boolean boundsIntersect(GameObject go) {
		return Collider.intersects(bounds(), go.bounds());
	}
	
	/**
     * Returns true if this game object's bounds intersect with the given
     * rectangle.
     */
    public boolean boundsIntersect(Rectangle r)
    {
        return Collider.intersects(bounds(), r);
    }


    /**
     * Returns true if this game object's collision geometry intersects with the
     * given rectangle.
     */
    public boolean geometryIntersects(Rectangle r)
    {
        return geometry.intersects(r, getX(), getY());
    }
    
    /**
     * Returns true if this game object's collision geometry intersects with
     * another game object's collision geometry.
     * 
     * @param go
     *            the other game object.
     * @return true if the geometries intersect.
     */
    public boolean geometryIntersects(GameObject go)
    {
        return geometry.intersects(getX(), getY(), go.geometry, go.getX(),
                go.getY());
    }
    /**
     * Returns true if this game object is in collision with a rectangle. It
     * first does a simple box test against this game object's bounds, then, if
     * that's true, tests its collision geometry against the rectangle.
     * 
     * @param r
     *            the rectangle to intersect.
     * @return true if this game object intersects the rectangle.
     */
    public boolean intersects(Rectangle r)
    {
        return boundsIntersect(r)
                && (geometry == null || geometryIntersects(r));
    }
    
    /**
	 * Returns true if this game object is in collision with another game
	 * object. It first does a bounds test, then, if that's true, tests its
	 * collision geometry against the other game object's collision geometry.
	 */
	public boolean intersects(GameObject go) {
		if (!boundsIntersect(go)) {
			return false;
		}
		if (geometry == null) {
			return go.geometry == null || go.geometryIntersects(bounds());
		} else if (go.geometry == null) {
			return geometryIntersects(go.bounds());
		}
		return geometryIntersects(go);
	}
	
	public boolean isInCollision()
    {
        return inCollision;
    }

    public void setInCollision(boolean inCollision)
    {
        this.inCollision = inCollision;
    }

    public boolean isDemandRemove()
    {
        return isDemandRemove;
    }

    public void setDemandRemove(boolean isDemandRemove)
    {
        this.isDemandRemove = isDemandRemove;
    }

    public void reset()
    {
        inCollision = false;
        isDemandRemove = false;
        state = STATE_NORMAL;
    }
    
}
