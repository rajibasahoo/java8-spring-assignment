package java8;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;

public class FilterList {

	public static void main(String[] args) {
		
		//1.Finding names starting with C
		List<String> names = Arrays.asList("Chris", "HTML", "XML", "CSS");

		System.out.println(names.stream().filter(name -> name.startsWith("C")).collect(Collectors.toList()));

		//2.Finding numbers which are even
		List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		
		System.out.println("original list: " + numbers);
	    List<Integer> even = numbers.stream()
	                                .map(s -> Integer.valueOf(s))
	                                .filter(number -> number % 2 == 0)
	                                .collect(Collectors.toList());
	    System.out.println("only even numbers: " + even);
	    
	    //3.Finding numbers which are even then square it then add total
	    Integer result = numbers.stream()
	    								.map(s -> Integer.valueOf(s))
	    								.filter(number -> number % 2 == 0)
	    								.map(p -> p = (int) Math.pow(p, 2))	    								
	    								.collect(Collectors.summingInt(Integer::intValue));
	    
	    System.out.println("addition of even number's square: " + result);
	    
	    //4.convert the list to map
	    List<Employee> employeeList = new ArrayList<>(Arrays.asList(
                new Employee(1, "A", 100),
                new Employee(2, "A", 200),
                new Employee(3, "B", 300),
                new Employee(4, "B", 400),
                new Employee(5, "C", 500),
                new Employee(6, "C", 600)));
	    //with out lambda 
	    Map<Integer, String> result1 = employeeList.stream().collect(
                Collectors.toMap(Employee::getId, Employee::getName));

	    	System.out.println("Result in Map : " +result1);
	    //with lambda	
	    Map<Integer, String> result2 = employeeList.stream().collect(
	                Collectors.toMap(x -> x.getId(), x -> x.getName()));

	        System.out.println("Result in Map : " + result2);
	       
	       //stream merging  
	        Stream<Integer> stream1 = Stream.of(1, 3, 5);
	        Stream<Integer> stream2 = Stream.of(2, 4, 6);
	     
	        Stream<Integer> resultingStream = Stream.concat(stream1, stream2);
	        System.out.println("Merged Stream : " + resultingStream.collect(Collectors.toList()));
	        
	        //if-else with stream 
	        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	        
	        ints.stream()
	            .forEach(i -> {
	                if (i.intValue() % 2 == 0) {
	                    Assert.assertTrue(i.intValue() % 2 == 0);
	                    System.out.println("Even number");
	                } else {
	                    Assert.assertTrue(i.intValue() % 2 != 0);
	                    System.out.println("Odd number");
	                }
	            });

		
	}

}
