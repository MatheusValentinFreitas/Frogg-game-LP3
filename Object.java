package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


abstract class Object {
	
	protected Texture img;
	protected Rectangle retangulo;
	protected Texture imgSapoUp;
	protected Texture imgSapoDown;
	protected Texture imgSapoLeft;
	protected Texture imgSapoRight;
	protected Texture imgSapoAtual;
	protected Sound sapoSom;
	
	public Object(Rectangle retangulo,Texture img) {
		this.img = img;
		this.retangulo = retangulo;
	}
	
	public Object(Rectangle retangulo,Texture imgSapoUp,Texture imgSapoDown,Texture imgSapoLeft,Texture imgSapoRight,Texture imgAtual,Sound sapoSom) {
		this.retangulo = retangulo;
		this.imgSapoUp = imgSapoUp;
		this.imgSapoDown = imgSapoDown;
		this.imgSapoLeft = imgSapoLeft;
		this.imgSapoRight = imgSapoRight;
		imgSapoAtual = imgSapoUp;
		this.sapoSom = sapoSom;
	}
	
	public abstract void move();
	
	public abstract void moveSapo(int direcao, int velocidade, int nivel);

	public abstract void handleEvent();

	public Texture getImg() {
		return img;
	}

	public void setImg(Texture img) {
		this.img = img;
	}

	public Rectangle getRetangulo() {
		return retangulo;
	}

	public void setRetangulo(Rectangle retangulo) {
		this.retangulo = retangulo;
	}

	public Texture getImgSapoAtual() {
		return imgSapoAtual;
	}

	public void setImgSapoAtual(Texture imgSapoAtual) {
		this.imgSapoAtual = imgSapoAtual;
	}
	
	
	
}
