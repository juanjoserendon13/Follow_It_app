import processing.core.*;

/**
 * @author Juan Jose Se crea la clase objetivo que hereda de agente
 */
public class Objetivo extends Agente {

	/**
	 * se definen sus atributos
	 */
	private ParticleSystem ps;
	private PVector p;
	private PImage tarLib, tarHer, tarDes;

	/**
	 * @param app
	 * @param pos
	 * @param col
	 * @param miMundo
	 *            se define el constructor de la clase
	 */
	public Objetivo(PApplet app, PVector pos, int col, Mundo miMundo) {
		// ///////////////// se heredan los atributos de la clase padre
		super(app, pos, col, miMundo);
		ps = new ParticleSystem(app, new PVector(app.width / 2, 50));
		tarLib = app.loadImage("../data/TarLib.png");
		tarHer = app.loadImage("../data/TarHer.png");
		tarDes = app.loadImage("../data/TarDes.png");

		velMax = 2f;

	}

	// /////////////////////// se crea el metodo que corre el hilo
	// public void run() {
	// while (vivo == true) {
	//
	// try {
	// mover();
	// sleep(10);
	// } catch (Exception e) {
	// e.printStackTrace();
	// vivo = false;
	//
	// }
	//
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see Agente#mover() se sobreescribe el metodo el cual permite mover al
	 * elemento
	 */
	public void mover() {
		// TODO Auto-generated method stub
		if (miMundo.getPantalla() == 2) {
			aceleracion = PVector.random2D();
			// aceleracion.normalize();
			velocidad.add(aceleracion);
			velocidad.limit(velMax);
			pos.add(velocidad);

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

			huir();
		}
	}

	/**
	 * se crea un metodo el cual permite alejarce del rastreador en el momento
	 * en el que se cumplan ciertas condiciones
	 */
	public void huir() {
		miMundo.getRastreadores();
		for (int i = 0; i < miMundo.getRastreadores().size(); i++) {
			Agente a = miMundo.getRastreadores().get(i);

			if (PApplet.dist(a.getPos().x, a.getPos().y, pos.x, pos.y) < 100) {
				// aceleracion.add(PVector.sub(a.getPos(), pos));

				float ang = app.atan2(pos.y - a.getPos().y, pos.x
						- a.getPos().x);
				pos.x += app.cos((float) (ang + Math.PI)) * -1;
				pos.y += app.sin((float) (ang + Math.PI)) * -1;
				// System.out.println("entra");
			} else {
				setAceleracion(new PVector(0, 0));

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Agente#pintar() se sobreescribe el metodo que permite mostrar los
	 * objetivos
	 */

	public void pintar() {
		app.fill(col);
		app.stroke(0);
		if (isVivo() == true) {
			for (int i = 0; i < miMundo.getRastreadores().size(); i++) {
				Agente a = miMundo.getRastreadores().get(i);
				// app.line(a.getPos().x, a.getPos().y, pos.x, pos.y);
			}
		}
		app.imageMode(app.CENTER);
		if (isVivo() == true && isHerido() == false) {
			app.image(tarLib, pos.x, pos.y);
			// app.ellipse(pos.x, pos.y, 20, 20);
		}
		if (isHerido() == true && isVivo() == true) {
			app.image(tarHer, pos.x, pos.y);
			// app.fill(100);
			// app.ellipse(pos.x, pos.y, 20, 20);
		}
		if (isVivo() == false) {
			app.image(tarDes, pos.x, pos.y);
			ps.addParticle();
			ps.run();
			ps.setOrigin(new PVector(pos.x, pos.y));
			// app.fill(0);
			// app.ellipse(pos.x, pos.y, 20, 20);
		}
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
