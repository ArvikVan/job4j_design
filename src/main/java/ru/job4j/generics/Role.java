package ru.job4j.generics;

public class Role extends Base {
    @Override
    public String getId() {
        return super.getId();
    }

    protected Role(String id) {
        super(id);
    }
}