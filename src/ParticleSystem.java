import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author Juan Jose Clase tomada de un ejemplo de processing
 */
public class ParticleSystem {
	ArrayList<Particle> particles;
	PVector origin;
	PApplet app;

	ParticleSystem(PApplet app, PVector location) {
		origin = location.get();
		particles = new ArrayList<Particle>();
		this.app = app;
	}

	void addParticle() {
		particles.add(new Particle(app, origin));
	}

	void run() {
		for (int i = particles.size() - 1; i >= 0; i--) {
			Particle p = particles.get(i);
			p.run();
			if (p.isDead()) {
				particles.remove(i);
			}
		}
	}

	public void setOrigin(PVector origin) {
		this.origin = origin;
	}
}
