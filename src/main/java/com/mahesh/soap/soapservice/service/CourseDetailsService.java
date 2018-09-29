package com.mahesh.soap.soapservice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mahesh.soap.soapservice.beans.Course;

@Component
public class CourseDetailsService {
	
	private static List<Course> courses = new ArrayList<Course>();
	
	static{
		Course course = new Course(1,"Spring","Spring book");
		courses.add(course);
		
		Course course2 = new Course(2,"Spring boot","Spring boot book");
		courses.add(course2);
		
		Course course3 = new Course(3,"Maven","MAven book");
		courses.add(course3);
	}
	
	public Course findCourseById(int id){
		for(Course cd : courses){
			if(cd.getId() == id){
				return cd;
			}else{
				return null;
			}
		}
		return null;
	}
	
	public List<Course> findAll(){
		return courses;
	}
	
	public int deleteById(int id){
		for(Course cd : courses){
			if(cd.getId() ==id){
				courses.remove(cd);
				return id;
			}else{
				return -1;
			}
		}
		return -1;
	}

}
