package ge.edu.sangu;

import ge.edu.sangu.logger.Level;
import ge.edu.sangu.logger.Logger;

/**
 * This is a data dummy Person class.
 */
public class Person {
    private static final Logger log = new Logger(Level.DEBUG, System.out);

    private String name;
    private int age;

    public Person(String name, int age) {
        log.debug("Called Person(..) constructor with arguments: " + name + ", " + age);
        this.name = name;
        this.age = age;
    }

    /**
     * This returns person's name.
     *
     * @return person'name
     */
    public String getName() {
        log.debug("Called Person's getName()");
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            log.warn("No name provided");
            throw new IllegalArgumentException("Name must no be null or blank");
        }
        log.debug("Called Person's setName(..) with argument: " + name);
        this.name = name;
    }

    public int getAge() {
        log.debug("Called Person's getAge()");
        return age;
    }

    public void setAge(int age) {
        log.debug("Called Person's setAge(..) with argument: " + age);
        this.age = age;
    }
}
