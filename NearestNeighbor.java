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
			Person person = new Person(""+a, (long) (Math.random() * 100));
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

		Iterator<Person> iterator = people.iterator();
		while (iterator.hasNext()) {
			final Person personi = iterator.next();
			//System.out.println(";" + personi.name +":"+ personi.location);
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
	}
}

/*
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class NearestNeighbor {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i=0;i<10;i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println("Initial List: "+list);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return (i2.intValue() > i1.intValue()) ? 1 : -1;
            }
        });
        System.out.println("Sorted List: "+list);
    }
}
*/
