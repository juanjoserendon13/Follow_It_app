import processing.core.*;

/**
 * @author Juan Jose Se crea la clase ejecutable
 */
public class Ejecutable extends PApplet {
	Mundo mundo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#setup() se crea el setup el cual contiene el
	 * tamaño del lienzo y un objeto de logica
	 */
	public void setup() {

		mundo = new Mundo(this);

		size(1200, 700);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#draw() se crea el metodo estatico que
	 * muestra la interfaz
	 */
	public void draw() {
		background(255);
		mundo.pintar();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#mouseClicked() se crea el metodo estatico
	 * que permite la interaccion del mouse
	 */
	public void mouseClicked() {
		mundo.agregarPresa();
		mundo.mouse();
	}

}
