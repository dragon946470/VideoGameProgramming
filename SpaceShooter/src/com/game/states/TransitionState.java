package com.game.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.spaceshooter.Game;
import com.game.spaceshooter.SpaceShooterGame;

public class TransitionState extends State {
	private State from;
	private State to;

	private enum STATE {
		IN, OUT;
	}

	private STATE state;

	private float timer;
	private float TRANS_TIME = 0.8f;

	private float START_ALPHA = 0f;
	private float END_ALPHA = 1f;
	private float alpha = 0f;

	public TransitionState(SpaceShooterGame game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		timer += delta;

		if (timer > TRANS_TIME) {
			timer = 0f;
			if (state == STATE.IN) {
				state = STATE.OUT;
			} else if (state == STATE.OUT) {
				//game.setState(to);
			}

		}

		if (state == STATE.IN) {
			alpha = apply(START_ALPHA, END_ALPHA, timer / TRANS_TIME);
			from.update(delta);

		}

		if (state == STATE.OUT) {
			alpha = apply(END_ALPHA, START_ALPHA, timer / TRANS_TIME);
			to.update(delta);

		}

	}

	@Override
	public void render(Graphics2D g2) {

		if (state == STATE.IN)
			from.render(g2);
		else if (state == STATE.OUT)
			to.render(g2);

		g2.setColor(new Color(0, 0, 0, alpha));
		g2.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());

	}

	public void start(State from, State to) {
		this.from = from;
		this.to = to;
		state = STATE.IN;
		alpha = 0f;
		timer = 0;
		//game.setState(this);

	}

	private float apply(float a, float b, float p) {
		return (b - a) * p + a;
	}

}