package com.mingyuans.reflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yanxq on 17/6/4.
 */
public class ReflectTest {

    @Test
    public void testFieldReflect() throws Exception {
        String tag = Reflect.onClass(Person.class).field("TAG").getValue();
        assertEquals("Human", tag);

        Reflect.onClass(Person.class).field("TAG").setValue("Animal");
        assertEquals("Animal", Person.getTAG());

        Person person = new Person("ming", "yuan");
        Reflect.onObj(person).field("mFirstName").setValue("mings");
        assertEquals("mings", person.getFirstName());

        tag = Reflect.onName(Person.class.getName()).field("TAG").getValue();
        assertEquals("Animal", tag);

        int age = Reflect.onObj(person).field("mPersonAge").getValue();
        assertEquals(0, age);
    }

    @Test
    public void testMethodReflect() throws Exception {
        Person person = (Person) Reflect.onClass(Person.class)
                .method("create")
                .find(new Class[]{String.class, String.class})
                .call("Hello", "world");

        assertEquals("Hello", person.getFirstName());
    }
}