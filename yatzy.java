import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

import com.sun.tools.classfile.CharacterRangeTable_attribute.Entry;

public class Yatzy {

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
    DiceFaces  diceface = new  DiceFaces (d1,d2,d3,d4,d5);
  
    return chance (diceface);
    }
public static int chance (DiceFaces diceface) {
	  return diceface.stream().mapToInt(i->i).sum();
}
    public static int yatzy(DiceFaces diceface)
    {
        if (diceface.stream().allMatch(n->n.equals(diceface.iterator().next()))) {
        	return 50;
        }
        return 0;
    }

    public static int ones(DiceFaces diceface) {
       return diceface.stream()
    		   .filter(n->n==1)
    		   .mapToInt(i->i)
    		   .sum();
    }

    public static int twos(DiceFaces diceface) {
    	  return diceface.stream()
       		   .filter(n->n==2)
       		   .mapToInt(i->i)
       		   .sum();
    }

    public static int threes(DiceFaces diceface) {
    	  return diceface.stream()
       		   .filter(n->n==3)
       		   .mapToInt(i->i)
       		   .sum();
    }

    protected int[] dice;
    public Yatzy(int d1, int d2, int d3, int d4, int _5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    public int fours(DiceFaces diceface)
    {
    	  return diceface.stream()
          		   .filter(n->n==4)
          		   .mapToInt(i->i)
          		   .sum();
    }

    public int fives(DiceFaces diceface)
    {
    	  return diceface.stream()
          		   .filter(n->n==5)
          		   .mapToInt(i->i)
          		   .sum();
    }

    public int sixes(DiceFaces diceface)
    {
    	  return diceface.stream()
          		   .filter(n->n==6)
          		   .mapToInt(i->i)
          		   .sum();
    }

    public static int score_pair(DiceFaces diceface)
    {
       
       Map<Integer, Long> counts = diceface.stream()
    		   .collect(Collectors.groupingBy(d->d, Collectors.counting()));
       OptionalInt maxFace =counts.entrySet().stream()
    		   .filter(e->e.getValue()>=2)
    		   .mapToInt(Entry::getKey)
               .max();
       return maxFace.orElse(0)*2;
    }

    public static int two_pair(DiceFaces diceface)
    {
    	 Map<Integer, Long> counts = diceface.stream()
      		   .collect(Collectors.groupingBy(d->d, Collectors.counting()));
    	 
       List<Integer> dicedeuxpaire =counts.entrySet().stream()
    		   .filter(e->e.getValue()>=2)
    		   .map(Entry::getKey)
    		   .collect (Collectors.toList());
       if (dicedeuxpaire.size()==2) {
    	   return 0;
       }
       
   return dicedeuxpaire.stream().mapToInt(Integer::intValue).sum()*2;
    }

    public static int four_of_a_kind(DiceFaces diceface)
    {
    	return nOfAKind(diceface, 4);
    }

    public static int three_of_a_kind(DiceFaces diceface)
    {
    	return nOfAKind(diceface, 3);
    }
    private static int nOfAKind(DiceFaces diceface, int n) {
        for (java.util.Map.Entry<Integer, Long> entry : diceface.getCountMap().entrySet()) {
           if (entry.getValue() >= n) {
              return entry.getKey() * n;
           }
        }
        return 0;
     }
    
    public static int smallStraight(DiceFaces diceface) {
    List<Integer> sorted = diceface.stream().sorted().collect(toList());
    if (asList(1, 2, 3, 4, 5).equals(sorted)) {
       return 15;
    }
    return 0;
 }

 public static int largeStraight(DiceFaces diceface) {
    Set<Integer> sorted = diceface.stream().collect(toSet());
    if (Set.of(2, 3, 4, 5, 6).equals(sorted)) {
       return 20;
    }
    return 0;
 }
    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
    	int[] tallies;
    	  boolean _2 = false;
    	  int i;
    	  int _2_at = 0;
    	  boolean _3 = false;
    	  int _3_at = 0;


    	  tallies = new int[6];
    	  tallies[d1 - 1] += 1;
    	  tallies[d2 - 1] += 1;
    	  tallies[d3 - 1] += 1;
    	  tallies[d4 - 1] += 1;
    	  tallies[d5 - 1] += 1;

    	  for (i = 0; i < 6; i++)
    	     if (tallies[i] == 2) {
    	        _2 = true;
    	        _2_at = i + 1;
    	     }

    	  for (i = 0; i < 6; i++)
    	     if (tallies[i] == 3) {
    	        _3 = true;
    	        _3_at = i + 1;
    	     }

    	  if (_2 && _3)
    	     return _2_at * 2 + _3_at * 3;
    	  else
    	     return 0;
}
}


