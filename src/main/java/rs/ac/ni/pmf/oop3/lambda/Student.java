package rs.ac.ni.pmf.oop3.lambda;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Student
{
	String firstName;
	String lastName;
	Integer age;
}
