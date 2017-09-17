import processing.core.*;

/**
 * @author Juan Jose Se crea la clase rastreador la cual hereda de agente
 */
public class Rastreador extends Agente {

	/**
	 * se definen sus atributos
	 */
	private int zona;
	private int segundos = 0;
	private PImage rasLib, rasAct;
	private boolean estado = false;
	private float ang = 1;
	private float t;
	private ParticleSystem ps;

	/**
	 * @param app
	 * @param pos
	 * @param col
	 * @param miMundo
	 *            se define su constructor
	 */
	public Rastreador(PApplet app, PVector pos, int col, Mundo miMundo) {

		// ///////////////// se heredan los atributos de la clase padre
		super(app, pos, col, miMundo);
		ps = new ParticleSystem(app, new PVector(app.width / 2, 50));
		this.zona = 150;

		rasLib = app.loadImage("../data/RasLib.png");
		rasAct = app.loadImage("../data/RasAct.png");
		velMax = 3f;
		t = app.random(1000);
	}

	// /////////////////////// se crea el metodo que corre el hilo
	// public void run() {
	// while (vivo == true) {
	// mover();
	// try {
	// // mover();
	// sleep(10);
	// } catch (Exception e) {
	// e.printStackTrace();
	// // vivo = false;
	//
	// } finally {
	// perseguir();
	//
	// }
	//
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see Agente#mover() se sobreescribe el metodo que permite mover el
	 * elemento
	 */
	public void mover() {
		// TODO Auto-generated method stub

		if (miMundo.getPantalla() == 2) {

			aceleracion = PVector.random2D();

			// aceleracion.x = app.map(app.noise(t), 0, 1, -2, 2);
			// aceleracion.y = app.map(app.noise(t + 1000), 0, 1, -2, 2);
			t += 0.0001;

			// aceleracion = new PVector((float) app.random(-2, 2),
			// (float) app.random(-2, 2));
			aceleracion.mult((float) 1);
			// aceleracion.normalize();
			velocidad.add(aceleracion);
			velocidad.limit(velMax);
			aceleracion.normalize();

			pos.add(velocidad);
			// aceleracion.mult(0);

			// /////////////// limites
			if ((pos.x > app.width + 22)) {
				pos.x = 0;

			}
			if ((pos.y > app.height + 22)) {
				pos.y = 0;

			}
			if ((pos.x < -22)) {
				pos.x = app.width;

			}
			if ((pos.y < -22)) {
				pos.y = app.height;

			}

			perseguir();
			matar();

		}
	}

	/**
	 * se crea un metodo que persigue los objetivos
	 */
	public void perseguir() {

		miMundo.getObjetivos();

		for (int i = 0; i < miMundo.getObjetivos().size(); i++) {

			Agente a = miMundo.getObjetivos().get(i);
			// ////////////////////// dist que evalua las posiciones de
			// cazadores y presas
			if (PApplet.dist(a.getPos().x, a.getPos().y, pos.x, pos.y) < zona / 2
					&& a.isVivo() == true) {
				//
				// aceleracion.add(PVector.sub(a.getPos(), pos));
				// estado = true;
				// /////////////// operaciones matematicas que ocasiona la
				// persecucion de la presa
				float ang = app.atan2(pos.y - a.getPos().y, pos.x
						- a.getPos().x);
				pos.x += app.cos((float) (ang + Math.PI)) * 1;
				pos.y += app.sin((float) (ang + Math.PI)) * 1;
				// System.out.println("entra");

			} else {
				setAceleracion(new PVector(0, 0));
				// estado = false;

			}
			System.out.println(estado);

		}
		// for (int i = 0; i < miMundo.getObjetivos().size(); i++) {
		//
		// Agente a = miMundo.getObjetivos().get(i);
		// if (PApplet.dist(a.getPos().x, a.getPos().y, pos.x, pos.y) > 150) {
		// estado = false;
		// }
		//
		// }
	}

	/**
	 * Se crea un metodo que interrumpe los hilos de acuerdo a ciertas
	 * condiciones
	 */
	public void matar() {
		miMundo.getObjetivos();
		for (int i = 0; i < miMundo.getObjetivos().size(); i++) {
			Agente a = miMundo.getObjetivos().get(i);

			if (app.dist(a.getPos().x, a.getPos().y, pos.x, pos.y) < 40) {

				a.setHerido(true);

				if (app.frameCount % 60 == 0) {
					segundos++;
					if (segundos == 1) {
						System.out.println("entra");
						a.interrupt();
					}

				}

			} else {
				segundos = 0;
			}

		}
	}

	// float ang = app.atan2(pos.y - pre.getPos().y, pos.x -
	// pre.getPos().x);
	// float ang = app.atan2(pos.y - pre.getPos().y, pos.x -
	// pre.getPos().x);
	// pos.x = pos.x + app.cos(ang) * 1;
	// pos.y = pos.y + app.sin(ang) * 1;

	// if (PApplet.dist(pos.x, pos.y, pre.getPos().x, pre.getPos().y) < 100)
	// {
	// setAceleracion(PVector.sub(pre.getPos(), pos));
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see Agente#pintar() se sobreescribe el metodo pintar el cual mostrara el
	 * elemento
	 */

	public void pintar() {
		// TODO Auto-generated method stub
		app.noStroke();
		app.fill(255, 0, 0, 10);
		app.ellipse(pos.x, pos.y, zona, zona);
		app.fill(col);
		// app.ellipse(pos.x, pos.y, 20, 20);
		miMundo.getObjetivos();
		app.imageMode(app.CENTER);

		app.pushMatrix();

		app.translate(pos.x, pos.y);
		ang = app.atan2(velocidad.y, velocidad.x);
		app.rotate(ang);

		// ang += 0.1;
		if (estado == false) {
			app.image(rasLib, 0, 0);
		}

		for (int i = 0; i < miMundo.getObjetivos().size(); i++) {

			Agente a = miMundo.getObjetivos().get(i);
			// ////////////////////// dist que evalua las posiciones de
			// cazadores y presas
			if (PApplet.dist(a.getPos().x, a.getPos().y, pos.x, pos.y) < zona / 2
					&& a.isVivo() == true) {
				app.image(rasAct, 0, 0);

			}

		}
		app.popMatrix();
		app.imageMode(app.CORNER);
		app.noFill();

	}

	/**
	 * @param aceleracion
	 */
	public void setAceleracion(PVector aceleracion) {
		this.aceleracion = aceleracion;
	}

}
