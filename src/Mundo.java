import java.util.ArrayList;

import processing.core.*;

/**
 * @author Juan Jose Se crea la clase mundo
 */
public class Mundo {
	/**
	 * se crean los atributos necesarios, para realizar el funcionamiento de la
	 * aplicacion
	 */
	private PApplet app;
	private int pantalla;
	private int posXr, posYr;
	private PImage fondoIni, fondoIns, fondoApp;
	private PImage botonUno, botonDos, botonTres;
	private float escala = 112;
	private float ang = 3.7f;
	private boolean crecer, decrecer;

	/**
	 * Se crean las colecciones que contendran los elementos de la interaccion
	 */
	ArrayList<Agente> objetivos = new ArrayList<Agente>();
	ArrayList<Agente> rastreadores = new ArrayList<Agente>();
	int col, colD;

	/**
	 * @param app
	 *            se crea el constructor de la clase mundo
	 */
	public Mundo(PApplet app) {

		this.posXr = 320;
		this.posYr = 234;
		this.app = app;
		col = app.color(0, 255, 0);
		colD = app.color(0, 0, 255);
		this.pantalla = 0;

		fondoIni = app.loadImage("../data/inicio.png");
		fondoIns = app.loadImage("../data/instru.png");
		fondoApp = app.loadImage("../data/aplicacion.png");

		botonUno = app.loadImage("../data/iniciar.png");
		botonDos = app.loadImage("../data/comenzar.png");
		botonTres = app.loadImage("../data/Reset.png");

		// ////////////// se crea una estructura repetitiva que adicionara los
		// elementos necesarios a las colecciones
		for (int i = 0; i <= 5; i++) {

			Objetivo p = new Objetivo(app, new PVector((int) app.random(20,
					1180), (int) app.random(20, 180)), col, this);

			p.start();
			objetivos.add(p);

			Rastreador c = new Rastreador(app, new PVector((int) app.random(20,
					1180), (int) app.random(500, 680)), colD, this);

			c.start();
			rastreadores.add(c);

		}
	}

	/**
	 * @param posX
	 * @param posY
	 *            metodo que permite animar los rect de la pantalla inicial
	 */
	public void animacion(int posX, int posY) {

		if (pantalla == 0) {
			// ///////// animacion de los rect en la pantalla 1
			app.stroke(255);
			app.noFill();
			if (escala == 112) {
				decrecer = true;
				crecer = false;
			}
			if (decrecer == true) {
				escala -= 1;
			}

			if (escala == 20) {
				crecer = true;
				decrecer = false;
			}
			if (crecer == true) {
				escala += 1;
			}
			app.pushMatrix();
			app.rectMode(app.CENTER);
			app.translate(posX, posY);
			app.rotate(app.PI / ang);
			app.rect(0, 0, escala, escala);
			app.rectMode(app.CORNER);
			app.popMatrix();
			// ang += 0.1;

		}
	}

	/**
	 * metodo que muestra los elementos de la interaccion y su respectiva
	 * interfaz
	 */
	public void pintar() {
		switch (pantalla) {
		case 0:
			app.image(fondoIni, 0, 0);
			if (app.mouseX >= 446 && app.mouseX <= 758 && app.mouseY >= 443
					&& app.mouseY <= 500) {
				app.image(botonUno, 446, 443);
			}
			animacion(posXr, posYr);
			animacion(posXr + 560, posYr);
			break;
		case 1:
			app.image(fondoIns, 0, 0);

			if (app.mouseX >= 919 && app.mouseX <= 1172 && app.mouseY >= 620
					&& app.mouseY <= 668) {
				app.image(botonDos, 919, 620);
			}

			break;
		case 2:

			app.image(fondoApp, 0, 0);

			if (app.mouseX >= 1073 && app.mouseX <= 1181 && app.mouseY >= 17
					&& app.mouseY <= 124) {
				app.image(botonTres, 1073, 17);
			}

			for (int i = 0; i < objetivos.size(); i++) {
				Objetivo pre = (Objetivo) objetivos.get(i);
				pre.pintar();

			}
			for (int i = 0; i < rastreadores.size(); i++) {
				Rastreador caz = (Rastreador) rastreadores.get(i);
				caz.pintar();

			}

			break;
		}

	}

	/**
	 * Metodo que permite adicionar elementons a una determinada coleccion
	 */
	public void agregarPresa() {
		if (app.mouseButton == app.LEFT && pantalla == 2) {

			Objetivo p = new Objetivo(app, new PVector(app.mouseX, app.mouseY),
					col, this);
			p.start();
			objetivos.add(p);

		}
	}

	/**
	 * Metodo que permite usar el mouse como modo de navegacion entre las
	 * pantallas
	 */
	public void mouse() {
		switch (pantalla) {
		case 0:

			if (app.mouseX >= 446 && app.mouseX <= 758 && app.mouseY >= 443
					&& app.mouseY <= 500) {
				pantalla++;
			}
			break;
		case 1:

			if (app.mouseX >= 919 && app.mouseX <= 1172 && app.mouseY >= 620
					&& app.mouseY <= 668) {
				pantalla++;

			}
			break;
		case 2:

			if (app.mouseX >= 1073 && app.mouseX <= 1181 && app.mouseY >= 17
					&& app.mouseY <= 124) {
				for (int i = 0; i < objetivos.size(); i++) {
					Agente a = objetivos.get(i);
					a.interrupt();

					objetivos.clear();

				}
			}
			break;
		}
	}

	/**
	 * @return
	 */
	public ArrayList<Agente> getObjetivos() {
		return objetivos;
	}

	/**
	 * @return
	 */
	public ArrayList<Agente> getRastreadores() {
		return rastreadores;
	}

	/**
	 * @return
	 */
	public int getPantalla() {
		return pantalla;
	}
}
