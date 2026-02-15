/**
 *  Utfärdat av Durim Miziraj
 *  Kontakt: gusmizdu@student.gu.se
 */

package lab3;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.util.Objects.requireNonNull;

import java.awt.Color;

import org.apache.commons.lang3.Validate;

import lab3.interfaces.Movable;

abstract class Vehicle extends GameObject implements Movable {

  private static final double TURN_RATE = 0.1d;
	private final int nrDoors;
  private double direction;
	private double enginePower;
	private double currentSpeed;
	private Color color;
	private String modelName;

	protected Vehicle(int nrDoors, double enginePower, Color color, String modelName, double x, double y) {
    super(x, y);

    Validate.inclusiveBetween(1, 5, nrDoors);
    Validate.isTrue(enginePower > 0.0d);
    Validate.finite(enginePower);
    requireNonNull(color);
    Validate.notBlank(modelName);
    Validate.finite(x);
    Validate.finite(y);

    this.direction = 0.0d;
		this.nrDoors = nrDoors;
		this.enginePower = enginePower;
		this.color = color;
		this.modelName = modelName;
	  stopEngine();
  }

  public double getDirection() { return direction; }

	public int getNrDoors() { return nrDoors; }

	public double getEnginePower() { return enginePower; }

	public double getCurrentSpeed() { return currentSpeed; }

	public int getColor() { return color.getRGB(); }

  public String getModelName() { return modelName; }

  public void setColor(int c) { color = new Color(c); }

	public void startEngine() { setCurrentSpeed(0.1d); }

	public void stopEngine() { setCurrentSpeed(0.0d); }

  public void turnLeft() { direction += TURN_RATE; }

  public void turnRight() { direction -= TURN_RATE; }

  private static final void validate(double lowBound, double highBound, double arg) {
    Validate.finite(arg);
    Validate.isTrue(lowBound <= arg && arg <= highBound);
  }

  public void brake(double speedDecrease) {
    validate(0.0d, 1.0d, speedDecrease);
    decreaseSpeed(speedDecrease);
	}

  public void gas(double speedIncrease) {
    validate(0.0d, 1.0d, speedIncrease);
    increaseSpeed(speedIncrease);
	}

  public void move() {
    super.mutatePoint(
      super.getX() + sin(direction) * getCurrentSpeed(),
      super.getY() + cos(direction) * getCurrentSpeed()
    );
  }

  private void setCurrentSpeed(double setSpeed) {
    if (setSpeed > getEnginePower()) {
      currentSpeed = getEnginePower();
      return;

    } else if (setSpeed < 0.0d) {
      currentSpeed = 0.0d;

    } else {
      currentSpeed = setSpeed;
    }
  }

	private void increaseSpeed(double speedIncrease) {
		setCurrentSpeed(
      increasedSpeedFactor(speedIncrease)
    );
	}

	private void decreaseSpeed(double speedDecrease) {
		setCurrentSpeed(
      decreasedSpeedFactor(speedDecrease)
    );
	}

  protected abstract double increasedSpeedFactor(double speedIncrease);
  protected abstract double decreasedSpeedFactor(double speedDecrease);
}
