package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FroggerGame extends ApplicationAdapter {

	public int HEIGHT = 448;
	public int WIDTH = 640;

	int vida = 4;
	int moscas = 5;
	int nivel = 1;
	int score = 0;
	
	String Score = new String("Score: ");
	String Nivel = new String("Nivel: ");
	String Vidas = new String("Vidas: ");
	
	SpriteBatch batch;

	BitmapFont font;

	Texture background;
	Texture background2;
	Texture background3;

	Texture imgSapoUp;
	Texture imgSapoDown;
	Texture imgSapoLeft;
	Texture imgSapoRight;
	Texture imgCarro;
	Texture imgOnibus;
	Texture imgCaminhonete;
	Texture imgAmbulancia;
	Texture imgFusca;
	Texture imgLog1;
	Texture imgLog2;
	Texture imgLog3;
	Texture imgMosca;
	
	Sound sapoSom;
	Music fundo;
	

	Object sapo;
	Array<Object> objects;

	@Override
	public void create() {

		batch = new SpriteBatch();
		carregarTexturas();
		spawnSapo();
		objects = new Array<Object>();
		spawnarVeiculos(nivel);
		carregarSons();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 1, 1);

		batch.begin();

		batch.draw(background, 0, 0, 640, 256);
		batch.draw(background2, 0, 256, 640, 448);
		batch.draw(background3, 0, 416);
		
		font.draw(batch,Vidas,0,25);
		font.draw(batch, Integer.toString(vida), 120, 25);
		
		font.draw(batch,Nivel,250,25);
		font.draw(batch,Integer.toString(nivel),380,25);
		
		font.draw(batch,Score,430,25);
		font.draw(batch,Integer.toString(score),570,25);
		
		
		
		for (Object rectangle : objects) {
			batch.draw(rectangle.getImg(), rectangle.getRetangulo().x, rectangle.getRetangulo().y);
		}

		batch.draw(sapo.getImgSapoAtual(), sapo.getRetangulo().x, sapo.getRetangulo().y);

		batch.end();
		
		sapo.handleEvent();

		Iterator<Object> iter = objects.iterator();

		while (iter.hasNext()) {

			Object rectangle = iter.next();

			rectangle.move();

			// Setar para remover a vida do sapo
			if (rectangle instanceof Veiculo) {
				if (rectangle.getRetangulo().overlaps(sapo.getRetangulo())) {
					vida -= 1;
					spawnSapo();
				}
			}

			if (rectangle instanceof Madeira) {
				
				/*
				if (rectangle.getRetangulo().overlaps(sapo.getRetangulo())) {
					

				} else {
					if (((sapo.getRetangulo().y >= 256) && (sapo.getRetangulo().y <= 400))) {
						vida -= 1;
					}
				}
				
				*/
				
				if (rectangle.getRetangulo().overlaps(sapo.getRetangulo())) {
					sapo.moveSapo(((Madeira) rectangle).getDirecao(), ((Madeira) rectangle).getVelocidade(),
							((Madeira) rectangle).getVelocidade());
				}

			}

			if (rectangle instanceof Mosca) {

				if (rectangle.getRetangulo().overlaps(sapo.getRetangulo())) {
					moscas -= 1;
					score += 10;
					spawnSapo();
					iter.remove();
				}

			}

		}
		
		if(vida == 0) {
			score = 0;
			nivel = 1;
			moscas = 5;
			vida = 4;
			objects = new Array<Object>();
			spawnarVeiculos(nivel);
		}else if(moscas == 0) {
			nivel += 1;
			moscas = 5;
			objects = new Array<Object>();
			spawnarVeiculos(nivel);
		}

	}

	@Override
	public void dispose() {
			batch.dispose();
		 font.dispose();

		 background.dispose();
		 background2.dispose();
		 background3.dispose();

		 imgSapoUp.dispose();
		 imgSapoDown.dispose();
		 imgSapoLeft.dispose();
		 imgSapoRight.dispose();
		 imgCarro.dispose();
		 imgOnibus.dispose();
		 imgCaminhonete.dispose();
		 imgAmbulancia.dispose();
		 imgFusca.dispose();
		 imgLog1.dispose();
		 imgLog2.dispose();
		 imgLog3.dispose();
		 imgMosca.dispose();
		
		 sapoSom.dispose();

	}

	private void spawnSapo() {
		Rectangle rectangle = new Rectangle();
		rectangle.x = WIDTH / 2;
		rectangle.y = 32;

		rectangle.width = 32;
		rectangle.height = 32;
		sapo = new Sapo(rectangle, imgSapoUp, imgSapoDown, imgSapoLeft, imgSapoRight, imgSapoUp, sapoSom);
	}

	private void spawnVeiculo(Texture img, int posX, int posY, int tamX, int tamY, int nivel, int velocidade,
			int direcao) {
		Rectangle rectangle = new Rectangle();
		rectangle.x = posX;
		rectangle.y = posY;

		rectangle.width = tamX;
		rectangle.height = tamY;

		Object carro = new Veiculo(rectangle, img, nivel, velocidade, direcao);
		objects.add(carro);
	}

	private void spawnMadeira(Texture img, int posX, int posY, int tamX, int tamY, int nivel, int velocidade,
			int direcao) {
		Rectangle rectangle = new Rectangle();
		rectangle.x = posX;
		rectangle.y = posY;

		rectangle.width = tamX;
		rectangle.height = tamY;

		Object madeira = new Madeira(rectangle, img, nivel, velocidade, direcao);
		objects.add(madeira);
	}

	private void spawnMosca(Texture img, int posX, int posY, int tamX, int tamY) {
		Rectangle rectangle = new Rectangle();
		rectangle.x = posX;
		rectangle.y = posY;

		rectangle.width = tamX;
		rectangle.height = tamY;

		Object mosca = new Mosca(rectangle, img);
		objects.add(mosca);
	}

	private void spawnarVeiculos(int nivel) {
		spawnVeiculo(imgCarro, 160, 64, 49, 32, nivel, 1, 1);
		spawnVeiculo(imgCarro, 480, 64, 49, 32, nivel, 1, 1);
		spawnVeiculo(imgCaminhonete, 32, 96, 49, 32, nivel, 1, 2);
		spawnVeiculo(imgCaminhonete, 32, 96, 49, 32, nivel, 1, 2);
		spawnVeiculo(imgCaminhonete, 128, 96, 49, 32, nivel, 1, 2);
		spawnVeiculo(imgCaminhonete, 352, 96, 49, 32, nivel, 1, 2);
		spawnVeiculo(imgCaminhonete, 448, 96, 49, 32, nivel, 1, 2);
		spawnVeiculo(imgAmbulancia, 96, 128, 45, 32, nivel, 1, 1);
		spawnVeiculo(imgAmbulancia, 512, 128, 45, 32, nivel, 1, 1);
		spawnVeiculo(imgFusca, 80, 160, 44, 32, nivel, 1, 2);
		spawnVeiculo(imgFusca, 176, 160, 44, 32, nivel, 1, 2);
		spawnVeiculo(imgFusca, 560, 160, 44, 32, nivel, 1, 2);
		spawnVeiculo(imgFusca, 464, 160, 44, 32, nivel, 1, 2);
		spawnVeiculo(imgOnibus, 64, 192, 61, 32, nivel, 1, 1);
		spawnVeiculo(imgOnibus, 384, 192, 61, 32, nivel, 1, 1);
		spawnMadeira(imgLog3, 111, 256, 49, 32, nivel, 1, 1);
		spawnMadeira(imgLog3, 271, 256, 49, 32, nivel, 1, 1);
		spawnMadeira(imgLog3, 382, 256, 49, 32, nivel, 1, 1);
		spawnMadeira(imgLog3, 493, 256, 49, 32, nivel, 1, 1);
		spawnMadeira(imgLog2, 164, 288, 156, 32, nivel, 1, 2);
		spawnMadeira(imgLog2, 484, 288, 156, 32, nivel, 1, 2);
		spawnMadeira(imgLog2, 214, 352, 156, 32, nivel, 1, 2);
		spawnMadeira(imgLog2, 534, 352, 156, 32, nivel, 1, 2);
		spawnMadeira(imgLog1, 220, 320, 231, 32, nivel, 1, 1);
		spawnMadeira(imgLog1, 220, 384, 231, 32, nivel, 1, 1);
		spawnMosca(imgMosca, 28, 416, 32, 32);
		spawnMosca(imgMosca, 164, 416, 32, 32);
		spawnMosca(imgMosca, 306, 416, 32, 32);
		spawnMosca(imgMosca, 442, 416, 32, 32);
		spawnMosca(imgMosca, 577, 416, 32, 32);
	}

	private void carregarTexturas() {
		imgSapoUp = new Texture(Gdx.files.internal("sapoCima.png"));
		imgSapoDown = new Texture(Gdx.files.internal("sapoBaixo.png"));
		imgSapoLeft = new Texture(Gdx.files.internal("sapoEsc.png"));
		imgSapoRight = new Texture(Gdx.files.internal("sapoDir.png"));
		imgCarro = new Texture(Gdx.files.internal("carro.png"));
		imgOnibus = new Texture(Gdx.files.internal("onibus.png"));
		imgCaminhonete = new Texture(Gdx.files.internal("caminhonete.png"));
		imgAmbulancia = new Texture(Gdx.files.internal("ambulancia.png"));
		imgFusca = new Texture(Gdx.files.internal("fusca.png"));
		background = new Texture(Gdx.files.internal("rodovia.png"));
		background2 = new Texture(Gdx.files.internal("agua.png"));
		background3 = new Texture(Gdx.files.internal("final.png"));
		imgLog1 = new Texture(Gdx.files.internal("log1.png"));
		imgLog2 = new Texture(Gdx.files.internal("log2.png"));
		imgLog3 = new Texture(Gdx.files.internal("log3.png"));
		font = new BitmapFont(Gdx.files.internal("GameFont.fnt"));
		imgMosca = new Texture(Gdx.files.internal("mosca.png"));
	}
	
	private void carregarSons() {
		sapoSom = Gdx.audio.newSound(Gdx.files.internal("Moeda.mp3"));
		fundo = Gdx.audio.newMusic(Gdx.files.internal("VideoGame-Theme-Frogger.wav"));
		
		fundo.setLooping(true);
		fundo.play();
	}
}
