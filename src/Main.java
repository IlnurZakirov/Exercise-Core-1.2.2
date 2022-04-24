import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println(persons
                .stream()
                .filter(person -> person.getAge() < 18)
                .count());

        List<String> listOfConscripts = persons
                .stream()
                .filter(person -> person.getAge() <= 27)
                .filter(person -> person.getAge() >= 18)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        Collection<Person> workerPersons = persons
                .stream()
                .filter(person -> (person.getEducation()).equals(Education.HIGHER))
                .filter(person -> {
                    return ((person.getSex()).equals(Sex.MAN) ? (person.getAge() <= 65) : (person.getAge() <= 60));
                })
                .filter(person -> person.getAge() >= 18)
                .sorted((p1, p2) -> (p1.getFamily()).compareTo(p2.getFamily()))
                .collect(Collectors.toList());
    }
}
