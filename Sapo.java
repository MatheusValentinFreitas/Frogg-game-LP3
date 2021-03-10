package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sapo extends Object{	

	public Sapo(Rectangle retangulo, Texture imgSapoUp, Texture imgSapoDown, Texture imgSapoLeft,
			Texture imgSapoRight,Texture imgAtual,Sound sapoSom) {
		super(retangulo, imgSapoUp, imgSapoDown, imgSapoLeft, imgSapoRight, imgAtual, sapoSom);
	}
	
	public void handleEvent() {
		
		//Controle X
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			retangulo.x -= retangulo.width;
			setImgSapoAtual(imgSapoLeft); 
			imgSapoAtual = imgSapoLeft;
			//sapoSom.play();
		
		}
			
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			retangulo.x += retangulo.width;
			setImgSapoAtual(imgSapoRight); 
			//sapoSom.play();


		}
			

		// make sure the bucket stays within the screen bounds
		if (retangulo.x < 0)
			retangulo.x = 640 - retangulo.width;
		if (retangulo.x > 640 - retangulo.width)
			retangulo.x = 0;
		
		//Controle Y
		
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			retangulo.y -= retangulo.height;
			setImgSapoAtual(imgSapoDown); 
			//sapoSom.play();

		}
			
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			retangulo.y += retangulo.height;
			setImgSapoAtual(imgSapoUp); 
			//sapoSom.play();


		}
			

		//Aqui esta contiduou a parte de orientacao do eixo y no caso esta setado para que ele possa aparecer na parte final do jogo 
		//Para que o aplicador possa avaliar os componentes do jogo
		if (retangulo.y < 32)
			retangulo.y = 448 - retangulo.height;
		if (retangulo.y > 448 - retangulo.height)
			retangulo.y = 32;
		
	}

	@Override
	public void moveSapo(int direcao, int velocidade, int nivel) { 
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
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
