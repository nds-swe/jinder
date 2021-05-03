package ch.abbts.nds.swe.swdt.jinder.nottdd;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MatcherTest {

  private Matcher matcher;

  @BeforeEach
  public void setUp() throws Exception {
    matcher = new Matcher();
  }

  @Test
  @DisplayName("If no skills match, the result must be empty")
  public void matchNone() {
    Candidate c = new Candidate() {
      @Override
      public String[] getSkills() {
        return new String[]{"maths", "software-design"};
      };
    };
    Profile p = new Profile() {
      @Override
      public String[] getSkills() {
        return new String[]{"maths", "algebra"};
      };
    };
    assertFalse(Arrays.asList(matcher.isMatch(c, p)).isEmpty(),
        "Intersection should be empty");
  }

  @Test
  @DisplayName("Matching candidate to profile should work if skills match")
  public void matchOne() {
    Candidate c = () -> new String[]{"maths", "software-design"};
    Profile p = () -> new String[]{"maths", "algebra"};
    String[] overlap = new String[]{"maths"};
    assertArrayEquals(overlap, matcher.isMatch(c, p),
        "Intersection of arrays should yield 'maths'");
  }

  @Test
  @DisplayName("Matching candidate to profile should work if two skills match, order must be ignored")
  public void matchTwo() {
    Candidate c = () -> new String[]{"maths", "software-design", "algebra"};
    Profile p = () -> new String[]{"maths", "algebra", "physics"};
    String[] overlap = new String[]{"algebra", "maths"};
    assertThat(Arrays.asList(overlap), containsInAnyOrder(matcher.isMatch(c, p)));
  }
}
