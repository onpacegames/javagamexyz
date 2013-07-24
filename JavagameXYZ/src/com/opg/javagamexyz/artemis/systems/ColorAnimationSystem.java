package com.opg.javagamexyz.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.opg.javagamexyz.artemis.components.ColorAnimation;
import com.opg.javagamexyz.artemis.components.Sprite;

public class ColorAnimationSystem extends EntityProcessingSystem {
	@Mapper protected ComponentMapper<ColorAnimation> cam;
	@Mapper protected ComponentMapper<Sprite> sm;
	
	@SuppressWarnings("unchecked")
	public ColorAnimationSystem() {
		super(Aspect.getAspectForAll(ColorAnimation.class, Sprite.class));
	}

	@Override
	protected void process(Entity e) {
		ColorAnimation c = cam.get(e);
		Sprite sprite = sm.get(e);
		
		// Alpha Animation
		if (c.alphaAnimate) {
			sprite.a += c.alphaSpeed * world.delta;
			
			boolean reverse = false;
			if (sprite.a > c.alphaMax) {
				sprite.a = c.alphaMax;
				reverse = true;
			} else if (sprite.a < c.alphaMin) {
				sprite.a = c.alphaMin;
				reverse = true;
			}
			
			if (reverse) {
				if (c.repeat) {
					c.alphaSpeed = -c.alphaSpeed;
				} else {
					c.alphaAnimate = false;
				}
			}
		}
		
		// Red Animation
		if (c.redAnimate) {
			sprite.r += c.redSpeed * world.delta;
			
			boolean reverse = false;
			if (sprite.r > c.redMax) {
				sprite.r = c.redMax;
				reverse = true;
			} else if (sprite.r < c.redMin) {
				sprite.r = c.redMin;
				reverse = true;
			}
			
			if (reverse) {
				if (c.repeat) {
					c.redSpeed = -c.redSpeed;
				} else {
					c.redAnimate = false;
				}
			}
		}
		
		// Green Animation
		if (c.greenAnimate) {
			sprite.g += c.greenSpeed * world.delta;
			
			boolean reverse = false;
			if (sprite.g > c.greenMax) {
				sprite.g = c.greenMax;
				reverse = true;
			} else if (sprite.g < c.greenMin) {
				sprite.g = c.greenMin;
				reverse = true;
			}
			
			if (reverse) {
				if (c.repeat) {
					c.greenSpeed = -c.greenSpeed;
				} else {
					c.greenAnimate = false;
				}
			}
		}
		
		// Blue Animation
		if (c.blueAnimate) {
			sprite.b += c.blueSpeed * world.delta;
			
			boolean reverse = false;
			if (sprite.b > c.blueMax) {
				sprite.b = c.blueMax;
				reverse = true;
			} else if (sprite.b < c.blueMin) {
				sprite.b = c.blueMin;
				reverse = true;
			}
			
			if (reverse) {
				if (c.repeat) {
					c.blueSpeed = -c.blueSpeed;
				} else {
					c.blueAnimate = false;
				}
			}
		}
	}
}