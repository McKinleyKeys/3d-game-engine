

package com.base.engine;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;


public class Camera {
    
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);
    
    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;
    
    private boolean mouseLocked;
    private Vector2f centerPosition;
    private final float sensitivity = 0.35f;
    
    public Camera() {
        
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }
    
    public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
        
        this.pos = pos;
        this.forward = forward;
        this.up = up;
        
        forward.normalise();
        up.normalise();
        
        centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);
    }
    
    public void input() {
        
        float moveAmt = (float)(10 * Time.getDelta());
        float rotAmt = (float)(100 * Time.getDelta());
        
        if (Input.getKey(Input.KEY_ESCAPE)) {
            
            Input.setCursor(true);
            mouseLocked = false;
        }
        
        if (Input.getMouseDown(0)) {
            
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }
        
        if (Input.getKey(Input.KEY_W))
            move(getForward(), moveAmt);
        if (Input.getKey(Input.KEY_S))
            move(getForward(), -moveAmt);
        if (Input.getKey(Input.KEY_A))
            move(getLeft(), moveAmt);
        if (Input.getKey(Input.KEY_D))
            move(getRight(), moveAmt);
        
        if (Input.getKey(Input.KEY_UP))
            rotateX(-rotAmt);
        if (Input.getKey(Input.KEY_DOWN))
            rotateX(rotAmt);
        if (Input.getKey(Input.KEY_LEFT))
            rotateY(-rotAmt);
        if (Input.getKey(Input.KEY_RIGHT))
            rotateY(rotAmt);
        
        if (mouseLocked) {
            
            Vector2f deltaPos = Input.getMousePosition().subtract(centerPosition);
            
            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;
            
            if (rotY)
                rotateY(deltaPos.getX() * sensitivity);
            if (rotX)
                rotateX(-deltaPos.getY() * sensitivity);
            
            if (rotY || rotX)
                Input.setMousePosition(centerPosition);
        }
    }
    
    public void move(Vector3f dir, float amt) {
        
        pos = pos.add(dir.multiply(amt));
    }
    
    public void rotateX(float angle) {
        
        Vector3f xAxis = yAxis.cross(forward).normalise();
        
        forward = forward.rotate(angle, xAxis).normalise();
        
        up = forward.cross(xAxis).normalise();
    }
    
    public void rotateY(float angle) {
        
        Vector3f xAxis = yAxis.cross(forward).normalise();
        
        forward = forward.rotate(angle, yAxis).normalise();
        
        up = forward.cross(xAxis).normalise();
    }
    
    public Vector3f getLeft() {
        
        Vector3f left = forward.cross(up);
        left.normalise();
        
        return left;
    }
    
    public Vector3f getRight() {
        
        Vector3f right = up.cross(forward);
        right.normalise();
        
        return right;
    }
    
    public Vector3f getPos() {
        
        return pos;
    }
    
    public Vector3f getForward() {
        
        return forward;
    }
    
    public Vector3f getUp() {
        
        return up;
    }
    
    public void setPos(Vector3f pos) {
        
        this.pos = pos;
    }
    
    public void setForward(Vector3f forward) {
        
        this.forward = forward;
    }
    
    public void setUp(Vector3f up) {
        
        this.up = up;
    }
}
