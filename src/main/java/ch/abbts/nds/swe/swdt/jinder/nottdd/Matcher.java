package ch.abbts.nds.swe.swdt.jinder.nottdd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Matcher {
  public String[] isMatch(Candidate candidate, Profile profile){
    Set<String> s1 = new HashSet<String>(Arrays.asList(candidate.getSkills()));
    Set<String> s2 = new HashSet<String>(Arrays.asList(profile.getSkills()));
    s1.retainAll(s2);
    return s1.toArray(new String[s1.size()]);
  }
}
