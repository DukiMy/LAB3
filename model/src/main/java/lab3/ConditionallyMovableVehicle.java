/**
 *  Utfärdat av Durim Miziraj
 *  Kontakt: gusmizdu@student.gu.se
 */

package lab3;

import java.awt.Color;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.finite;

abstract class ConditionallyMovableVehicle extends Vehicle {

  public ConditionallyMovableVehicle (
    final int nrDoors,
    final double enginePower,
    final Color color,
    final String modelName,
    final double x,
    final double y
  ) { super(nrDoors, enginePower, color, modelName, x, y); }

  protected abstract boolean canMove();

  @Override
  public void move() { if (canMove()) super.move(); }

  private static void validate(double d) {
    isTrue(d >= 0.0d);
    finite(d);
  }

  @Override
  public void gas(final double speedIncrease) {
    validate(speedIncrease);
    if (canMove()) super.gas(speedIncrease);
  }

  protected abstract double speedFactor();

  @Override
	protected double increasedSpeedFactor(final double increase) {
    validate(increase);
    return getCurrentSpeed() + speedFactor() * increase;
  }

  @Override
  protected double decreasedSpeedFactor(final double decrease) {
    validate(decrease);
    return getCurrentSpeed() - speedFactor() * decrease;
  }
}
