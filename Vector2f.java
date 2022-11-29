

package com.base.engine;


public class Vector2f {
    
    private float x;
    private float y;
    
    public Vector2f(float x, float y) {
        
        this.x = x;
        this.y = y;
    }
    
    public float length() {
        
        return (float)Math.sqrt(x * x + y * y);
    }
    
    public float dot(Vector2f r) {
        
        return x * r.getX() + y * r.getY();
    }
    
    public Vector2f normalise() {
        
        float length = length();
        
        return new Vector2f(x / length, y / length);
    }
    
    public Vector2f rotate(float angle) {
        
        double rad = (double)Math.toRadians(angle);
        double cos = (double)Math.cos(rad);
        double sin = (double)Math.sin(rad);
        
        return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
    }
    
    public Vector2f add(Vector2f r) {
        
        return new Vector2f(x + r.getX(), y + r.getY());
    }
    
    public Vector2f add(float amt) {
        
        return new Vector2f(x + amt, y + amt);
    }
    
    public Vector2f subtract(Vector2f r) {
        
        return new Vector2f(x - r.getX(), y - r.getY());
    }
    
    public Vector2f subtract(float amt) {
        
        return new Vector2f(x - amt, y - amt);
    }
    
    public Vector2f multiply(Vector2f r) {
        
        return new Vector2f(x * r.getX(), y * r.getY());
    }
    
    public Vector2f multiply(float amt) {
        
        return new Vector2f(x * amt, y * amt);
    }
    
    public Vector2f divide(Vector2f r) {
        
        return new Vector2f(x / r.getX(), y / r.getY());
    }
    
    public Vector2f divide(float amt) {
        
        return new Vector2f(x / amt, y / amt);
    }
    
    public Vector2f abs() {
        
        return new Vector2f(Math.abs(x), Math.abs(y));
    }
    
    public String toString() {
        
        return "(" + x + " " + y + ")";
    }
    
    public float getX() {
        
        return x;
    }
    
    public float getY() {
        
        return y;
    }
    
    public void setX(float x) {
        
        this.x = x;
    }
    
    public void setY(float y) {
        
        this.y = y;
    }
}
