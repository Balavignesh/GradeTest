/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.balavignesh.gradetest;

import com.balavignesh.gradebook.domain.Student;
import com.balavignesh.gradebook.domain.StudentList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author BalaVignesh
 */
public class GradeApp {
    private Client client;
	private WebTarget resource;
	int totalTests = 0;
	int failedTests = 0;
	
	public GradeApp(String url) {
		client = ClientBuilder.newClient();
		resource = client.target(url);
	}
	
	public int getTotalTests() {
		return totalTests;
	}

	public int getFailedTests() {
		return failedTests;
	}
	
	public String getUri(String path) {
		return resource.getUri() + "/" + path;
	}
	
	public class TestResult {
		public final String name;
		public final String method;
		public final String uri;
		public final boolean succeeded;
		public final String errorMessage;
		public TestResult(String name, String method, String uri, boolean succeeded, String errorMessage) {
			this.name = name;
			this.method = method;
			this.uri = uri;
			this.succeeded = succeeded;
			this.errorMessage = errorMessage;
		}
		public void report(PrintStream ps) {
			ps.println(name + ": " + method + " " + uri);
			if( succeeded ) {
				ps.println("  succeeded");
			} else {
				ps.println("  failed");
				ps.println("  " + errorMessage);
			}
		}
	}
	
	public void record(TestResult result) {
		record(result,System.out);
	}
		
	public void record(TestResult result, PrintStream ps) {
		totalTests++;
		if( !result.succeeded ) failedTests++;
		result.report(ps);
	}
	
	public TestResult noStudent(String student) {
		String name = "no-"+student;
		boolean succeeded = false;
		String errorMessage = null;
		String path = "student/" + student;
		try {
			resource.path(path).request(MediaType.TEXT_XML).get(Student.class);
                        errorMessage = "  student " + student + " exists; please specify another name with -Dstudent=name";
        } catch (WebApplicationException e) {
            if( e.getResponse().getStatus() == HttpURLConnection.HTTP_NOT_FOUND ) {
            	succeeded = true;
            } else {
            	errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
            }
		}
		return new TestResult(name,"GET",getUri(path),succeeded,errorMessage);
	}

	public TestResult gradeStudent(String grade, String verb, String student) {
		String name = student+"-" + verb.toLowerCase() + "s-" + grade.toLowerCase();
		boolean succeeded = false;
		String errorMessage = null;
		String path = "student/" + student + "/grade/" + grade;
		try {
			if( "post".equals(verb.toLowerCase()) ) {
				resource.path(path).request().post(Entity.text(""));
			} else {
				resource.path(path).request().put(Entity.text(""));
			}
			succeeded = true;
        } catch (WebApplicationException e) {
            errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
 		}
		return new TestResult(name,verb.toUpperCase(),getUri(path),succeeded,errorMessage);
	}

	public TestResult studentGets(String grade, String student) {
		String name = student+"-gets-" + grade.toLowerCase();
		boolean succeeded = false;
		String errorMessage = null;
		Student waldo;
		String path = "student/" + student;
		try {
			waldo = resource.path(path).request(MediaType.TEXT_XML).get(Student.class);
			if( ! grade.equalsIgnoreCase(waldo.getGrade()) ) {
				errorMessage = student + " got " + waldo.getGrade() + ", expecting " + grade;
			} else if( ! student.equalsIgnoreCase(waldo.getName()) ) {
				errorMessage = student + "'s name should not be " + waldo.getName();
			} else {
				succeeded = true;
			}
        } catch (WebApplicationException e) {
            errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
 		}
		return new TestResult(name,"GET",getUri(path),succeeded,errorMessage);
	}
	
	public TestResult studentIncluded(String student) {
		String name = student+"-included";
		boolean succeeded = false;
		String errorMessage = null;
		StudentList everybody;
		String path = "student";
		try {
			everybody = resource.path(path).request().accept(MediaType.TEXT_XML).get(StudentList.class);
			for( Student st : everybody.getStudent() ) {
				if( student.equals(st.getName()) ) {
					succeeded = true;
					break;
				}
			}
			if( !succeeded ) {
				errorMessage = student + " not in list of all students";
			}
        } catch (WebApplicationException e) {
            errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
 		}
		return new TestResult(name,"GET",getUri(path),succeeded,errorMessage);
	}
        
        public TestResult studentCount(int count) {
		String name = "Total-count";
		boolean succeeded = false;
		String errorMessage = null;
		StudentList everybody;
		String path = "student";
		try {
			everybody = resource.path(path).request().accept(MediaType.TEXT_XML).get(StudentList.class);
			if( everybody!=null && everybody.getStudent()!=null && everybody.getStudent().size()==count) {
                            succeeded = true;
			}
			if( !succeeded ) {
				errorMessage = "Total count of all students should be "+count;
			}
        } catch (WebApplicationException e) {
            errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
 		}
		return new TestResult(name,"GET",getUri(path),succeeded,errorMessage);
	}

	public TestResult bogusStudentGrade(String student,String grade) {
		String name = student+"-bogus-grade";
		boolean succeeded = false;
		String errorMessage = null;
		String path = "student/" + student + "/grade/" + grade;
		try {
			Response response =resource.path(path).request().post(Entity.text(""));
                        if(response!=null && response.getStatus() != -1 &&
                                response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode() ){
                          succeeded = true;   
                        }
                        if("".equals(grade)){
                            grade="\"\"";
                        }
			errorMessage = "bogus grade " + grade + " should not have been allowed";
        } catch (WebApplicationException e) {
            if( e.getResponse().getStatus() == HttpURLConnection.HTTP_BAD_REQUEST ) {
            	succeeded = true;
            } else {
                errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
            }
 		}
		return new TestResult(name,"POST",getUri(path),succeeded,errorMessage);
	}

	public TestResult goodbyeStudent(String student) {
		String name = "goodbye-"+student;
		boolean succeeded = false;
		String errorMessage = null;
		String path = "student/" + student;
		try {
			Response response = resource.path(path).request().delete();
			succeeded = true;
        } catch (WebApplicationException e) {
            errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
 		}
		return new TestResult(name,"DELETE",getUri(path),succeeded,errorMessage);
	}
        
        public TestResult goodbyeNoStudent(String student) {
		String name = "goodbye-no-"+student;
		boolean succeeded = false;
		String errorMessage = null;
		String path = "student/" + student;
		try {
			Response response = resource.path(path).request().delete();
                         if(response!=null && response.getStatus() != -1 &&
                                response.getStatus() == Response.Status.NOT_FOUND.getStatusCode() ){
                          succeeded = true;   
                        }
                         errorMessage = " Expected HTTP response " + Response.Status.NOT_FOUND.getStatusCode();
        } catch (WebApplicationException e) {
            errorMessage = " unexpected HTTP response " + e.getResponse().getStatus();
 		}
		return new TestResult(name,"DELETE",getUri(path),succeeded,errorMessage);
	}
        
         public static String decodeValue(String value) {
                try {
                    return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex.getCause());
                }
            }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hosturl = System.getProperty("url","http://localhost:8080/GradeBook/resources");
		GradeApp ga = new GradeApp(hosturl);
                ArrayList<String> Students = new ArrayList<String>(
                Arrays.asList("Velma","Scooby%20Doo","Shaggy","Fred","Daphne"));
                
                ArrayList<String> grades = new ArrayList<String>(
                Arrays.asList("A+","A-","B+","B-","C+","C-","D+","D-","A","B","C","D","E","F","I","W","Z","a+","a%2B","d"));
                
                ArrayList<String> bogusGrades = new ArrayList<String>(
                Arrays.asList("bogus","f+","","A!","G"));
                
		TestResult noWaldoResult = ga.noStudent(Students.get(0));
		ga.record(noWaldoResult);
		if( !noWaldoResult.succeeded) {
			System.exit(1);
		}
                ga.record(ga.noStudent(Students.get(1)));
                ga.record(ga.noStudent(Students.get(2)));
                ga.record(ga.noStudent(Students.get(3)));
                ga.record(ga.noStudent(Students.get(4)));
		ga.record(ga.gradeStudent(grades.get(0),"post",Students.get(0)));
		ga.record(ga.studentIncluded(Students.get(0)));
		ga.record(ga.studentGets(grades.get(0),Students.get(0)));
		ga.record(ga.gradeStudent(grades.get(2),"put",Students.get(0)));
		ga.record(ga.studentGets(grades.get(2),Students.get(0)));
		ga.record(ga.bogusStudentGrade(Students.get(0),bogusGrades.get(0)));
                ga.record(ga.studentCount(1));
		ga.record(ga.goodbyeStudent(Students.get(0)));
		ga.record(ga.noStudent(Students.get(0)));
                ga.record(ga.gradeStudent(grades.get(0),"post",Students.get(0)));
                ga.record(ga.gradeStudent(grades.get(1),"post",Students.get(1)));
                ga.record(ga.gradeStudent(grades.get(2),"post",Students.get(2)));
                ga.record(ga.gradeStudent(grades.get(3),"post",Students.get(3)));
                ga.record(ga.gradeStudent(grades.get(4),"post",Students.get(4)));
                ga.record(ga.studentCount(5));
                ga.record(ga.studentGets(grades.get(0),Students.get(0)));
                ga.record(ga.studentGets(grades.get(1),decodeValue(Students.get(1))));
                ga.record(ga.studentGets(grades.get(2),Students.get(2)));
                ga.record(ga.studentGets(grades.get(3),Students.get(3)));
                ga.record(ga.studentGets(grades.get(4),Students.get(4))); 
                ga.record(ga.gradeStudent(grades.get(5),"put",Students.get(0)));
                ga.record(ga.gradeStudent(grades.get(6),"put",Students.get(1)));
                ga.record(ga.gradeStudent(grades.get(7),"put",Students.get(2)));
                ga.record(ga.gradeStudent(grades.get(8),"put",Students.get(3)));
                ga.record(ga.gradeStudent(grades.get(9),"put",Students.get(4)));
                ga.record(ga.studentCount(5));
                ga.record(ga.studentGets(grades.get(5),Students.get(0)));
                ga.record(ga.studentGets(grades.get(6),decodeValue(Students.get(1))));
                ga.record(ga.studentGets(grades.get(7),Students.get(2)));
                ga.record(ga.studentGets(grades.get(8),Students.get(3)));
                ga.record(ga.studentGets(grades.get(9),Students.get(4)));
                ga.record(ga.gradeStudent(grades.get(10),"put",Students.get(0)));
                ga.record(ga.gradeStudent(grades.get(11),"put",Students.get(1)));
                ga.record(ga.gradeStudent(grades.get(12),"put",Students.get(2)));
                ga.record(ga.gradeStudent(grades.get(13),"put",Students.get(3)));
                ga.record(ga.gradeStudent(grades.get(14),"put",Students.get(4)));
                ga.record(ga.studentCount(5));
                ga.record(ga.studentGets(grades.get(10),Students.get(0)));
                ga.record(ga.studentGets(grades.get(11),decodeValue(Students.get(1))));
                ga.record(ga.studentGets(grades.get(12),Students.get(2)));
                ga.record(ga.studentGets(grades.get(13),Students.get(3)));
                ga.record(ga.studentGets(grades.get(14),Students.get(4)));           
                ga.record(ga.gradeStudent(grades.get(15),"put",Students.get(0)));
                ga.record(ga.gradeStudent(grades.get(16),"put",Students.get(1)));
                ga.record(ga.gradeStudent(grades.get(17),"put",Students.get(2)));
                ga.record(ga.gradeStudent(grades.get(18),"put",Students.get(3)));
                ga.record(ga.gradeStudent(grades.get(19),"put",Students.get(4)));
                ga.record(ga.studentCount(5));
                ga.record(ga.studentGets(grades.get(15),Students.get(0)));
                ga.record(ga.studentGets(grades.get(16),decodeValue(Students.get(1))));
                ga.record(ga.studentGets(grades.get(17),Students.get(2)));
                ga.record(ga.studentGets(decodeValue(grades.get(18)),Students.get(3)));
                ga.record(ga.studentGets(grades.get(19),Students.get(4)));
                ga.record(ga.bogusStudentGrade(Students.get(1),bogusGrades.get(1)));
                ga.record(ga.bogusStudentGrade(Students.get(2),bogusGrades.get(2)));
                ga.record(ga.bogusStudentGrade(Students.get(3),bogusGrades.get(3)));
                ga.record(ga.bogusStudentGrade(Students.get(4),bogusGrades.get(4)));
                ga.record(ga.goodbyeStudent(Students.get(0)));
                ga.record(ga.goodbyeStudent(Students.get(1)));
                ga.record(ga.goodbyeStudent(Students.get(2)));
                ga.record(ga.goodbyeStudent(Students.get(3)));
                ga.record(ga.goodbyeStudent(Students.get(4)));
                ga.record(ga.goodbyeNoStudent(Students.get(0)));
                ga.record(ga.goodbyeNoStudent(Students.get(1)));
                ga.record(ga.goodbyeNoStudent(Students.get(2)));
                ga.record(ga.goodbyeNoStudent(Students.get(3)));
                ga.record(ga.goodbyeNoStudent(Students.get(4)));
                ga.record(ga.studentCount(0));
                
		System.out.println(ga.getFailedTests() + " of " + ga.getTotalTests() + " tests failed");
        System.exit(0);        
	}

}
