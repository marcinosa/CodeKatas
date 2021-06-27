/**
 * Copyright 2021 BNYMellon. All rights reserved.
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file.
 */

package bnymellon.codekatas.lambdakata.jdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This test will illustrate how you can use lambdas with Functional Interface types introduced in Java 8
 * like Consumer, Function and Predicate.
 *
 * Please refer to this tutorial for an overview of Lambdas for Java 8.
 * @see <a href="http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html">Lambda Quickstart</a>
 *
 * Then follow the TODOs in each test and convert the anonymous inner classes to lambdas and/or method references.
 */
public class FunctionalInterfaceTest
{
    @Test
    public void consumer()
    {
        var strings = List.of("one", "two", "three");

        // TODO - Can you remove the final keyword from the variable below?
        final var result = new ArrayList<String>();

        // TODO - Convert the anonymous inner class to a lambda
        var consumer = new Consumer<String>()
        {
            @Override
            public void accept(String each)
            {
                result.add(each.toUpperCase());
            }
        };
        consumer.accept("zero");
        Assertions.assertEquals(List.of("ZERO"), result);
        strings.forEach(consumer);
        Assertions.assertEquals(List.of("ZERO", "ONE", "TWO", "THREE"), result);
    }

    @Test
    public void predicateIsEven()
    {
        var numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        // TODO - Convert the anonymous inner class to a lambda
        var evenPredicate = new Predicate<Integer>()
        {
            @Override
            public boolean test(Integer integer)
            {
                return integer % 2 == 0;
            }
        };
        Assertions.assertTrue(evenPredicate.test(2));
        Assertions.assertFalse(evenPredicate.test(1));
        var evens = numbers.stream().filter(evenPredicate).collect(Collectors.toList());
        Assertions.assertTrue(evens.stream().allMatch(evenPredicate));
        Assertions.assertFalse(evens.stream().noneMatch(evenPredicate));
        Assertions.assertTrue(evens.stream().anyMatch(evenPredicate));
        Assertions.assertEquals(Interval.evensFromTo(1, 10), evens);
    }

    @Test
    public void predicateIsOdd()
    {
        var numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        // TODO - Convert the anonymous inner class to a lambda
        var oddPredicate = new Predicate<Integer>()
        {
            @Override
            public boolean test(Integer integer)
            {
                return integer % 2 == 1;
            }
        };
        Assertions.assertFalse(oddPredicate.test(2));
        Assertions.assertTrue(oddPredicate.test(1));
        var odds = numbers.stream().filter(oddPredicate).collect(Collectors.toList());
        Assertions.assertTrue(odds.stream().allMatch(oddPredicate));
        Assertions.assertFalse(odds.stream().noneMatch(oddPredicate));
        Assertions.assertTrue(odds.stream().anyMatch(oddPredicate));
        Assertions.assertEquals(Interval.oddsFromTo(1, 10), odds);
    }

    @Test
    public void function()
    {
        // TODO - Convert the anonymous inner class to a lambda and then a method reference
        var toUppercase = new Function<String, String>()
        {
            @Override
            public String apply(String s)
            {
                return s.toUpperCase();
            }
        };
        Assertions.assertEquals("UPPERCASE", toUppercase.apply("uppercase"));
        List<String> lowercase = List.of("a", "b", "c", "d");
        Set<String> uppercase = lowercase.stream().map(toUppercase).collect(Collectors.toSet());
        Assertions.assertEquals(Set.of("A", "B", "C", "D"), uppercase);
    }

    @Test
    public void supplier()
    {
        // TODO - Convert this anonymous inner class to a lambda and then to a constructor reference
        var supplier = new Supplier<List<String>>()
        {
            @Override
            public List<String> get()
            {
                return new CopyOnWriteArrayList<String>();
            }
        };
        Assertions.assertEquals(new CopyOnWriteArrayList<>(), supplier.get());
        Assertions.assertNotSame(supplier.get(), supplier.get());
        List<String> list = Stream.of("1", "2", "3").collect(Collectors.toCollection(supplier));
        Assertions.assertEquals(List.of("1", "2", "3"), list);
    }

    @Test
    public void biConsumer()
    {
        var result = new HashMap<String, String>();
        // TODO - Convert the anonymous inner class to a lambda
        var biConsumer = new BiConsumer<String, String>()
        {
            @Override
            public void accept(String key, String value)
            {
                result.put(key.toUpperCase(), value.toUpperCase());
            }
        };
        biConsumer.accept("a", "one");
        Assertions.assertEquals(Map.of("A", "ONE"), result);

        var lowercaseMap = Map.of("a", "one", "b", "two", "c", "three");
        lowercaseMap.forEach(biConsumer);
        Assertions.assertEquals(
                Map.of("A", "ONE", "B", "TWO", "C", "THREE"),
                result);
    }

    @Test
    public void unaryOperator()
    {
        // TODO - Convert the anonymous inner class to a lambda
        var squared = new UnaryOperator<Integer>()
        {
            @Override
            public Integer apply(Integer integer)
            {
                return integer * integer;
            }
        };
        Assertions.assertEquals(Integer.valueOf(4), squared.apply(2));
        Assertions.assertEquals(Integer.valueOf(9), squared.apply(3));
        Assertions.assertEquals(Integer.valueOf(16), squared.apply(4));

        // TODO - Convert the anonymous inner class to a lambda
        Assertions.assertTrue(Stream.iterate(2, squared).anyMatch(new Predicate<Integer>()
        {
            @Override
            public boolean test(Integer i)
            {
                return i.equals(Integer.valueOf(256));
            }
        }));
    }
}
