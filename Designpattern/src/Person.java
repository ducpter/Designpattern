import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private String birthDate;
    private String gender;
    private Person spouse;
    private List<Person> children;

    /**
     *
     * @param name
     * @param birthDate
     * @param gender
     */
    public Person(String name, String birthDate, String gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    /**
     *
     * @param partner
     */
    public void marry(Person partner) {
        if (this.spouse == null && partner.spouse == null) {
            this.spouse = partner;
            partner.spouse = this;
        }
    }

    /**
     *
     * @param child
     */
    public void addChild(Person child) {
        children.add(child);
    }

    /**
     *
     * @return
     */
    public Person getSpouse() {
        return spouse;
    }

    /**
     *
     * @return
     */
    public boolean isSingle() {
        return spouse == null;
    }

    /**
     *
     * @return
     */
    public boolean hasTwoChildren() {
        return children.size() == 2;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public List<Person> getChildren() {
        return children;
    }

    public static List<Person> findSingles(List<Person> people) {
        return people.stream().filter(Person::isSingle).collect(Collectors.toList());
    }

    public static List<Person> findCouplesWithTwoChildren(List<Person> people) {
        return people.stream()
                .filter(p -> p.spouse != null && p.hasTwoChildren())
                .collect(Collectors.toList());
    }

    public static List<Person> findLatestGeneration(List<Person> people) {
        return people.stream()
                .filter(p -> p.children.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Tạo đối tượng Person và thiết lập mối quan hệ
        Person james = new Person("James", "1970-01-01", "Male");
        Person hana = new Person("Hana", "1972-02-02", "Female");
        james.marry(hana);

        Person ryan = new Person("Ryan", "1995-03-03", "Male");
        Person kai = new Person("Kai", "1997-04-04", "Male");
        james.addChild(ryan);
        james.addChild(kai);

        Person jennifer = new Person("Jennifer", "1998-05-05", "Female");
        kai.marry(jennifer);

        Person child1 = new Person("Child1", "2020-06-06", "Female");
        Person child2 = new Person("Child2", "2021-07-07", "Male");
        kai.addChild(child1);
        kai.addChild(child2);

        List<Person> allPeople = List.of(james, hana, ryan, kai, jennifer, child1, child2);

        // Tìm kiếm các cá nhân độc thân
        System.out.print("Single individuals: ");
        List<String> singleNames = findSingles(allPeople).stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(singleNames);

        // Tìm kiếm các cặp có hai con
        System.out.print("\nCouples with two children: ");
        List<String> coupleNames = findCouplesWithTwoChildren(allPeople).stream()
                .map(p -> p.getName() + " and " + p.getSpouse().getName())
                .collect(Collectors.toList());
        System.out.println(coupleNames);

        // Tìm kiếm thế hệ mới nhất
        System.out.print("\nLatest generation: ");
        List<String> latestGenerationNames = findLatestGeneration(allPeople).stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(latestGenerationNames);
    }
}

