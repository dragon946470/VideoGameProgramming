package com.game.utils;

public class Vector2f {

	public float x, y;

	public Vector2f() {
		x = 0;
		y = 0;
	}

	public Vector2f(float x2, float y2) {
		x = x2;
		y = y2;
	}

	public void add(Vector2f other) {
		x += other.x;
		y += other.y;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void scale(float s) {
		x *= s;
		y *= s;

	}

}
