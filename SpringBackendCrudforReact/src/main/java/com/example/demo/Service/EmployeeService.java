package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Entity.Demo;
import com.example.demo.Repository.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	@Autowired
	private  EmployeeRepo empRepo;
	
	public Demo postEmpDetails(Demo emp) {	
		return empRepo.save(emp);
		
	}
	
	
	public List<Demo> getEmployee(){
		return empRepo.findAll();
		
	}
	
	public String deleteEmployee(int id) {
		if(!empRepo.existsById(id)) {
			throw new EntityNotFoundException(" employee not found in dataBase");
		}
		empRepo.deleteById(id);
		return "employee Deleted";
		
	}
//	getting details for updating
	public Demo getEmployeById(int id) {
		System.out.println("got all the data to form from DataBase");
		return  (empRepo.findById(id).orElse(null));
		
	}
	
	public Demo updateEmployee(int id, Demo emp)  // getting data from updating Form. 
	{
		Optional<Demo> data = empRepo.findById(id); // this will check in DB for matched id and tells present or not
		if(data.isPresent())   // it will for present
		{
			Demo existingEmpData = data.get(); // already old data in DB is set to existingEmpData object.
			
			existingEmpData.setDepartment(emp.getDepartment()); // now setting the data from Form obj to existing obj
			existingEmpData.setEmail(emp.getEmail());
			existingEmpData.setEmpname(emp.getEmpname());
			existingEmpData.setPhonenumber(emp.getPhonenumber());
			System.out.println("i am setting the data to dB");
			return empRepo.save(existingEmpData);
		}
		else {
			return null;
			
		}
		
		
	}
}
