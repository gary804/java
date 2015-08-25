import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

public class NearestNeighbor{
	static class Person {
		String name;
		long location;

		Person( String name, long location) {
			this.name = name;
			this.location = location;
		}

		 @Override
		public String toString(){
			return name+":"+location;
		}

	}
	

	public static void main(String[] args){
		List<Person> people = new ArrayList<Person>();
		for (char a='a'; a<='z'; a++){
			Person person = new Person(""+a, (long) (Math.random() * 100));	//randomNumber is: 0~99
			people.add(person);
		}
		System.out.println("Initial List: "+people);
		Collections.sort( people, new Comparator<Person>(){
			@Override
			public int compare(Person p1, Person p2){
				return (p1.location>p2.location)? 1 : -1;
			}
		});
		System.out.println("Initial Sorted List: "+people);

		/* works
		Iterator<Person> iterator = people.iterator();
		while (iterator.hasNext()) {
			final Person personi = iterator.next();
			int index = people.indexOf(personi);
			int startIndex = Math.max(index-3, 0);
			int endIndex =  Math.min(index+3, people.size()-1);
			List<Person> temp = new ArrayList<Person>();
			for (int i=startIndex; i<=endIndex; i++ ){
				if (i==index) continue;
				temp.add(people.get(i));
			}
			Collections.sort( temp, new Comparator<Person>(){
				@Override
				public int compare(Person p1, Person p2){
					return ( Math.abs(p1.location-personi.location))>( Math.abs(p2.location-personi.location))?1:-1;
				}
			});
			temp = temp.subList(0, 3);
			System.out.println(personi+": "+temp);
		}
		*/
		for (int i=0; i<people.size();i++){
			final Person personi = people.get(i);
			int startIndex = Math.max(i-3, 0);
			int endIndex = Math.min(i+3, people.size()-1);
			List<Person> piList = new ArrayList<Person>();
			for(int j=startIndex; j<= endIndex; j++){
				if (j==i) continue;
				piList.add(people.get(j));
			}
			Collections.sort(piList, new Comparator<Person>(){
				@Override
				public int compare(Person p1, Person p2){
					return (Math.abs(p1.location-personi.location))>(Math.abs(p2.location-personi.location))?1:-1;
				}
			});
			piList = piList.subList(0,3);
			System.out.println(personi+": "+piList);
		}
	}
}
