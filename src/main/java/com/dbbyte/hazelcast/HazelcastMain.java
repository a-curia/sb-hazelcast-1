package com.dbbyte.hazelcast;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastMain {

	public static void main(String[] args) {
		HazelcastInstance hz = Hazelcast.newHazelcastInstance();
		
		// key/value storage with Hazelcast maps
		Map<String, String> employees = hz.getMap("employees");
		// employees is a namespace, and should be unique name across the cluster and fixed number of partitions (default 271)
		// Each key falls into a partition where partitionId = hash(keyData)%PARTITION_COUNT
		
		if(employees.containsKey("1"))
	    {
		    employees.put("6", "emp6");
	
	    } else {
	        employees.put("1", "emp1");
	        employees.put("2", "emp2");
	        employees.put("3", "emp3");
	        employees.put("4", "emp4");
	        employees.put("5", "emp5");
	    }
		
		System.out.println("Total number of employees " + employees.size());

		
//		// LIST
//		List<String> countries = hz.getList("persons");
//		persons.addAll(employees.keySet());
//		persons.add("tom");
//		persons.add("john");
//		persons.add("tom"); // duplicate entry
//		
//		// SETS
//		Set<String> persons = hz.getSet("persons");
//		persons.addAll(employees.values());
//		persons.add("tom");
//		persons.add("john");
//		persons.add("jobin");
//		
//		// QUEUE - structure would be incredibly useful if we had a number of tasks to individually handle by a number of client workers
//		BlockingQueue<String> arrivals = hz.getQueue("arrivals");
//
//	    while (true) {
//	      String arrival = arrivals.take();
//
//	      System.err.println(
//	        "New arrival from: " + arrival);
//	    }
		
		// REPLICATED MAP
		// week consistency, data replicated on each node; lost or missing updates are neither tracked nor resent;
		// is suitable for immutable objects, catalogue data, or idempotent calculable data
		// used in cases of immutable slow moving data like config
		// ReplicatedMap interface supports EntryListeners
		// there are no atomic guarantees to writes or reads
		Map<String, String> employeesConfig = hz.getReplicatedMap("employeesConfig");
		employeesConfig.put("1", "emp1");
		employeesConfig.put("2", "emp2");
		employeesConfig.put("3", "emp3");
		employeesConfig.put("4", "emp4");
		employeesConfig.put("5", "emp5");
	    
	    System.out.println("Total number of employeesConfig " + employeesConfig.size());
		
		
	}

}
