package web;

/*
Klass för objektet Friend - alltså alla användare som finns på denna webbtjänst (som därmed kan ta emot gåva från andra)
 */
/**
 *
 * @author nikolaj
 */
public class Friend {

    private String name;
    private String desc;

    public Friend() {
    }

    public Friend(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
