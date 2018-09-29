package com.mahesh.soap.soapservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.mahesh.soap.soapservice.beans.Course;
import com.mahesh.soap.soapservice.service.CourseDetailsService;


@Endpoint
public class CouseDetailsEndPoint {
	
	@Autowired
	CourseDetailsService service;
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		Course course = service.findCourseById(request.getId());
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		response.setCourseDetails(courseDetails);
		return response;
		
	}
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processGetAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){
		
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		List<Course> courses = service.findAll();
		
		for(Course course : courses){
			CourseDetails courseDetails = new CourseDetails();
			courseDetails.setId(course.getId());
			courseDetails.setName(course.getName());
			courseDetails.setDescription(course.getDescription());
			response.getCourseDetails().add(courseDetails);
		}
		return response;
		
	}
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request){
		
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		int status = service.deleteById(request.getId());
		response.setStatus(status);
		return response;
		
	}

}
