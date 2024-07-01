package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Demo;
import com.example.demo.Service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empservice;

	@PostMapping("/postData")
	private Demo insertData(@RequestBody Demo emp) {
		Demo data = empservice.postEmpDetails(emp);
		return data;
		
	}

	@GetMapping("/getDetails")
	private List<Demo> getData() {
		List<Demo> Data = empservice.getEmployee();
		return Data;
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	private ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		try {
			 empservice.deleteEmployee(id);
			 return new ResponseEntity<>("employee deleted",HttpStatus.OK);
		}
		 catch(EntityNotFoundException e) {
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			 
		 }
		
	}
	
	@GetMapping("/gettingDetailsForUpdate/{id}")
	private ResponseEntity<?> getEmployeById(@PathVariable int id) {
			Demo employeeData = empservice.getEmployeById(id);
			System.out.println(employeeData);
			if(employeeData == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(employeeData);
			
		
	}
	
	@PatchMapping("/updatingEmpInDB/{id}")
	private ResponseEntity<?> updateEmployee(@PathVariable int id,@RequestBody Demo emp) {
		System.out.println(" updating API"+id);
		Demo data = empservice.updateEmployee(id, emp);
		if(data == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok(data);
		
	}
		
}
