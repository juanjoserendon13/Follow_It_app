import processing.core.*;

/**
 * @author Juan Jose Se crea la clase agente la cual extiende de thread
 */
public abstract class Agente extends Thread {

	/**
	 * se definen los atributos a usar
	 */
	protected PApplet app;
	protected PVector pos, velocidad, aceleracion;
	protected int col;
	protected float velMax;
	protected Mundo miMundo;
	boolean vivo;
	protected boolean herido;;

	/**
	 * @param app
	 * @param pos
	 * @param col
	 * @param miMundo
	 *            se crea el constructor de la clase
	 */
	public Agente(PApplet app, PVector pos, int col, Mundo miMundo) {
		this.miMundo = miMundo;
		this.app = app;
		this.pos = pos;
		this.col = col;
		this.vivo = true;
		velocidad = new PVector(0, 0);
		aceleracion = new PVector(-0.01f, 0.01f);
		velMax = 3f;
	}

	/**
	 * se define un metodo abstracto que permite mover elementos
	 */
	public abstract void mover();

	/**
	 * se define un metodo pintar que nos muestra los elementos
	 */
	public abstract void pintar();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run() se sobreescribe el metodo run para indicarle
	 * lo que deba hacer
	 */
	public void run() {
		while (vivo == true) {

			try {
				mover();
				sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
				vivo = false;

			}

		}
	}

	/**
	 * @return
	 */
	public PVector getPos() {
		return pos;
	}

	/**
	 * @return
	 */
	public boolean isVivo() {
		return vivo;
	}

	/**
	 * @param vivo
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * @return
	 */
	public boolean isHerido() {
		return herido;
	}

	/**
	 * @param herido
	 */
	public void setHerido(boolean herido) {
		this.herido = herido;
	}

	/**
	 * @return
	 */
	public boolean getHerido() {
		return herido;
	}

}
