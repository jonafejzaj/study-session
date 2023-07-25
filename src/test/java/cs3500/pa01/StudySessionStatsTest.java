package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.StudySessionStats;
import org.junit.jupiter.api.Test;

/**
 * testing stats at end of session
 */
public class StudySessionStatsTest {
  StudySessionStats stats = new StudySessionStats();

  /**
   * testing easy counter
   */
  @Test
  public void testCountEasy() {
    //assertEquals(0, count);
    int result = stats.countEasy();
    assertEquals(0, result);
    result = stats.countEasy();
    assertEquals(1, result);
    result = stats.countEasy();
    assertEquals(2, result);
  }

  @Test
  public void testCountHard() {
    //assertEquals(0, count);
    int result = stats.countHard();
    assertEquals(0, result);
    result = stats.countHard();
    assertEquals(1, result);
    result = stats.countHard();
    assertEquals(2, result);
  }

  @Test
  public void testCountAnswered() {
    //assertEquals(0, count);
    int result = stats.countAnswered();
    assertEquals(0, result);
    result = stats.countAnswered();
    assertEquals(1, result);
    result = stats.countAnswered();
    assertEquals(2, result);
  }
}
