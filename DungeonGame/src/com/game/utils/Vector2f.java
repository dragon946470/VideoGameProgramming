package com.game.utils;

public class Vector2f {

	public float x, y;

	public Vector2f() {

	}

	public Vector2f(Vector2f v) {
		x = v.x;
		y = v.y;
	}

	public Vector2f(float x2, float y2) {
		x = x2;
		y = y2;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector2f direction) {
		x += direction.x;
		y += direction.y;

	}

	public void scale(float scale) {
		x *= scale;
		y *= scale;
	}

	public float magnitude() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public void normalize() {
		float magnitude = magnitude();

		x /= magnitude;
		y /= magnitude;
	}

}
