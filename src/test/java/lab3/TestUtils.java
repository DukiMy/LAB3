package lab3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupoter.api.AfterEach;
import org.junit.jupoter.api.Test;

public abstract class TestUtils {
  
  private final List<GameObject> created = new ArrayList<>();

  private <T extends GameObject> T track(T obj) {
    created.add(obj);
    return obj;
  }

  @AfterEach
  void cleanup() {
    for (GameObject o : created) o.destroy();
    created.clear();
  }
}
