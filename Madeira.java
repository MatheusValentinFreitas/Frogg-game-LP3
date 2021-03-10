package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Madeira extends Object {

	private int nivel;
	private int velocidade;
	private int direcao;
	
	public Madeira(Rectangle retangulo, Texture img, int nivel, int velocidade, int direcao) {
		super(retangulo, img);
		this.nivel = nivel;
		this.velocidade = velocidade;
		this.direcao = direcao;
	}

	@Override
	public void move() {
		if(direcao == 1) {
			getRetangulo().x -= velocidade * nivel;
			
			if (retangulo.x < - retangulo.width)
				retangulo.x = 640;
			
			
		}else if(direcao == 2) {
			getRetangulo().x += velocidade * nivel;
			
			if (retangulo.x > 640 + retangulo.width)
				retangulo.x = - retangulo.width;
		}
	}

	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveSapo(int direcao, int velocidade, int nivel) {
		// TODO Auto-generated method stub
		
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
	
	
	
	
	
}
