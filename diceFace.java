
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DiceFaces   implements Iterable<Integer>{
private final int[] dices;

public int[] getDices() {
return dices;
}
public DiceFaces(int d1, int d2, int d3, int d4, int d5) {
this.dices= new int[]{d1,d2,d3,d4,d5};
}
@Override
public Iterator<Integer> iterator() {
return stream().iterator();

}
 public  Stream <Integer> stream(){
 return IntStream.of(dices).boxed();
}
public Map<Integer, Long> getCountMap() {
	   return stream().collect(groupingBy(d -> d, counting()));
	return null;
}
private Object counting() {
	// TODO Auto-generated method stub
	return null;
}}