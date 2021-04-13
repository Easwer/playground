package com.easwer.collection.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExample {

    public static void main(String[] args) {
        List<Employee> sampleList = createEmployees();
        StreamsExample streamsExample = new StreamsExample();
        // Create stream with size lesser than original stream based on Predicate.
        // streamsExample.testFilter(sampleList);

        // Create stream of same size with manipulation.
        // streamsExample.testMap(sampleList);

        // Create stream with size higher than original stream.
        // streamsExample.testFlatMap(sampleList);

        // TODO: understand more
        // streamsExample.testPeek(sampleList);

        // Find and return sub stream of distinct values
        // streamsExample.testDistinct(sampleList);

        // Limit number of elements in sub stream
        // streamsExample.testLimit(sampleList);

        // return true if predicate matches any one element
        // streamsExample.testAnyMatch(sampleList);

        // return true if predicate matches All
        // streamsExample.testAllMatch(sampleList);

        // return true if predicate matches no element
        // streamsExample.testNoneMatch(sampleList);

        // return stream size
        // streamsExample.testCount(sampleList);

        // return any random element in stream
        // for sequential stream mostly first element.
        // for parallel stream random element
        // streamsExample.testFindAny(sampleList);

        // return first element in stream
        // streamsExample.testFindFirst(sampleList);

        // iterate the stream
        // streamsExample.testForEach(sampleList);

        // return min element based on the provided comparetor
        streamsExample.testMin(sampleList);

        // return max element based on the provided comparetor
        streamsExample.testMax(sampleList);
    }

    private static List<Employee> createEmployees() {
        return Arrays.asList(new Employee(1, "Apple", new Date(), "Chennai"),
                new Employee(2, "Ball", new Date(), "Bangalore"), new Employee(3, "Cat", new Date(), "Bangalore"),
                new Employee(4, "Dog", new Date(), "Chennai"), new Employee(5, "Eleplant", new Date(), "Chennai"),
                new Employee(2, "Ball", new Date(), "Bangalore"), new Employee(3, "Cat", new Date(), "Bangalore"),
                new Employee(6, "Cat", new Date(), ""));
    }

    /**
     * filter() in streams will create an output stream.
     * 
     * @param sampleList
     */
    public void testFilter(List<Employee> sampleList) {
        System.out.println("*** FILTER START ***");
        System.out.println("*** Location == Chennai ***");
        sampleList.stream().filter((value) -> {
            return value.getLocation().equalsIgnoreCase("Chennai");
        }).forEach((value) -> System.out.println(value.getName()));
        Predicate<Employee> locationPredicate = value -> ((Employee) value).getLocation().equalsIgnoreCase("Bangalore");
        System.out.println("*** Location == Bangalore ***");
        sampleList.stream().filter(locationPredicate).forEach((value) -> System.out.println(value.getName()));
        System.out.println("*** FILTER END ***");
    }

    /**
     * map() in streams will create an output stream.
     * 
     * @param sampleList
     */
    public void testMap(List<Employee> sampleList) {
        System.out.println("*** MAP START ***");
        System.out.println("Name Length: ");
        sampleList.stream().map((testStr) -> testStr.getName().length()).forEach((value) -> System.out.println(value));
        System.out.println("Name: ");
        sampleList.stream().map((testStr) -> testStr.getName()).forEach((value) -> System.out.println(value));
        System.out.println("*** MAP END ***");
    }

    public void testFlatMap(List<Employee> sampleList) {
        System.out.println("*** FLAT MAP START ***");
        Stream<String> stream = Arrays
                .asList("One flew over the cuckoo's nest", "To kill a muckingbird", "Gone with the wind").stream();
        stream.flatMap((value) -> {
            String[] split = value.split(" ");
            return (Stream<String>) Arrays.asList(split).stream();
        }).forEach((value) -> System.out.println(value));
        System.out.println("*** FLAT MAP END ***");
    }

    public void testCollect(List<Employee> sampleList) {
        Stream<Employee> stream = sampleList.stream();
    }

    public void testReduce(List<Employee> sampleList) {
        Stream<Employee> stream = sampleList.stream();
    }

    public void testGroupingBy(List<Employee> sampleList) {
        Stream<Employee> stream = sampleList.stream();
    }

    public void testDistinct(List<Employee> sampleList) {
        System.out.println("*** DISTINCT END ***");
        System.out.println("Distinct Array: ");
        List<String> distinctStrings = Arrays.asList("one", "two", "three", "one").stream().distinct()
                .collect(Collectors.toList());
        System.out.println(distinctStrings);
        System.out.println("Distinct Employee: ");
        List<Employee> employees = sampleList.stream().distinct().collect(Collectors.toList());
        System.out.println(employees);
        System.out.println("*** DISTINCT END ***");
    }

    public void testLimit(List<Employee> sampleList) {
        System.out.println("*** LIMIT START ***");
        System.out.println("Limit One: ");
        sampleList.stream().limit(1).forEach((value) -> System.out.println(value));
        System.out.println("Limit Three: ");
        sampleList.stream().limit(3).forEach((value) -> System.out.println(value));
        System.out.println("*** LIMIT END ***");
    }

    public void testAnyMatch(List<Employee> sampleList) {
        System.out.println("*** ANY MATCH START ***");
        System.out.println("Finding Chennai:");
        Predicate<Employee> chennaiPredicate = (value) -> value.getLocation().equalsIgnoreCase("Chennai");
        if (sampleList.stream().anyMatch(chennaiPredicate)) {
            System.out.println("Chennai Available");
        } else {
            System.out.println("Chennai Not Available");
        }
        System.out.println("Finding Nagercoil:");
        Predicate<Employee> locationPredicate = (value) -> value.getLocation().equalsIgnoreCase("Nagercoil");
        if (sampleList.stream().anyMatch(locationPredicate)) {
            System.out.println("Nagercoil Available");
        } else {
            System.out.println("Nagercoil Not Available");
        }
        System.out.println("*** ANY MATCH END ***");
    }

    public void testAllMatch(List<Employee> sampleList) {
        System.out.println("*** ALL MATCH START ***");
        Predicate<Employee> emptyPredicate = (value) -> !value.getLocation().isEmpty();
        if (sampleList.stream().allMatch(emptyPredicate)) {
            System.out.println("Location Available for all");
        } else {
            System.out.println("Location Not Available for all");
        }
        emptyPredicate = (value) -> !value.getName().isEmpty();
        if (sampleList.stream().allMatch(emptyPredicate)) {
            System.out.println("Name Available for all");
        } else {
            System.out.println("Name Not Available for all");
        }
        System.out.println("*** ALL MATCH END ***");
    }

    public void testNoneMatch(List<Employee> sampleList) {
        System.out.println("*** NONE MATCH START ***");
        Predicate<Employee> emptyPredicate = (value) -> value.getLocation().isEmpty();
        if (sampleList.stream().noneMatch(emptyPredicate)) {
            System.out.println("Location Available for all");
        } else {
            System.out.println("Location Not Available for all");
        }
        Predicate<Employee> namePredicate = (value) -> value.getName().isEmpty();
        if (sampleList.stream().noneMatch(namePredicate)) {
            System.out.println("Name Available for all");
        } else {
            System.out.println("Name Not Available for all");
        }
        System.out.println("*** NONE MATCH END ***");
    }

    public void testCount(List<Employee> sampleList) {
        System.out.println("*** COUNT START ***");
        System.out.println("Total Chennai: "
                + sampleList.stream().filter((value) -> value.getLocation().equalsIgnoreCase("Chennai")).count());
        System.out.println("Total Bangalore: "
                + sampleList.stream().filter((value) -> value.getLocation().equalsIgnoreCase("Bangalore")).count());
        Stream<String> stream = Arrays
                .asList("One flew over the cuckoo's nest", "To kill a muckingbird", "Gone with the wind").stream();
        System.out.println("Array Count: " + stream.count());
        Stream<String> stream2 = Arrays
                .asList("One flew over the cuckoo's nest", "To kill a muckingbird", "Gone with the wind").stream();
        System.out.println("Sub Array Count: " + stream2.flatMap((value) -> {
            String[] split = value.split(" ");
            return (Stream<String>) Arrays.asList(split).stream();
        }).count());
        System.out.println("*** COUNT END ***");
    }

    public void testFindAny(List<Employee> sampleList) {
        System.out.println("*** FIND ANY START ***");
        for (int i = 1; i <= 5; i++) {
            Stream<Employee> seqStream = sampleList.stream();
            Stream<Employee> parStream = sampleList.parallelStream();
            Optional<Employee> seqString = seqStream.findAny();
            if (seqString.isPresent())
                System.out.println("Sequential stream: " + seqString.get());
            Optional<Employee> parString = parStream.findAny();
            if (parString.isPresent())
                System.out.println("Parallel stream: " + parString.get());
        }
        System.out.println("*** FIND ANY END ***");
    }

    public void testFindFirst(List<Employee> sampleList) {
        System.out.println("*** FIND FIRST START ***");
        Optional<Employee> eOptional = sampleList.stream().findFirst();
        if (eOptional.isPresent())
            System.out.println(eOptional.get());
        System.out.println("*** FIND FIRST END ***");
    }

    public void testForEach(List<Employee> sampleList) {
        System.out.println("*** FOR EACH START ***");
        sampleList.stream().filter((value) -> {
            return !value.getLocation().isEmpty();
        }).forEach((value) -> System.out.println(value.getName()));
        System.out.println("*** FOR EACH END ***");
    }

    public void testMin(List<Employee> sampleList) {
        System.out.println("*** MIN START ***");
        Stream<Employee> stream = sampleList.stream();
        Comparator<Employee> comparator = (emp1, emp2) -> {
            return emp1.getName().compareTo(emp2.getName());
        };
        Optional<Employee> min = stream.min(comparator);
        if (min.isPresent())
            System.out.println(min.get());
        System.out.println("*** MIN END ***");
    }

    public void testMax(List<Employee> sampleList) {
        System.out.println("*** MAX START ***");
        Stream<Employee> stream = sampleList.stream();
        Comparator<Employee> comparator = (emp1, emp2) -> {
            return emp1.getName().compareTo(emp2.getName());
        };
        Optional<Employee> max = stream.max(comparator);
        if (max.isPresent())
            System.out.println(max.get());
        System.out.println("*** MAX END ***");
    }

    public void testToArray(List<Employee> sampleList) {
        System.out.println("*** TO ARRAY START ***");
        Stream<Employee> stream = sampleList.stream();
        System.out.println("***  END ***");
    }

    public void testPeek(List<Employee> sampleList) {
        System.out.println("***  START ***");
        Stream<Employee> stream = sampleList.stream();
        System.out.println("***  END ***");
    }

}
