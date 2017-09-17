import processing.core.*;
import processing.core.PVector;

/**
 * @author Juan Jose clase tomada de un ejemplo de processing
 */
public class Particle {

	PApplet app;
	PVector location;
	PVector velocity;
	PVector acceleration;
	float lifespan;

	/**
	 * @param app
	 * @param l
	 */
	Particle(PApplet app, PVector l) {
		acceleration = new PVector(0, (float) 0.05);
		velocity = new PVector(app.random(-1, 2), app.random(-1, 0));
		location = l.get();
		lifespan = 100;
		this.app = app;
	}

	void run() {
		update();
		display();
	}

	// Method to update location
	void update() {
		velocity.add(acceleration);
		location.add(velocity);
		lifespan -= 1.0;
	}

	// Method to display
	void display() {
		app.stroke(255, lifespan);
		app.fill(255, lifespan);
		app.ellipse(location.x, location.y, 5, 5);
	}

	// Is the particle still useful?
	boolean isDead() {
		if (lifespan < 0.0) {
			return true;
		} else {
			return false;
		}
	}

}
