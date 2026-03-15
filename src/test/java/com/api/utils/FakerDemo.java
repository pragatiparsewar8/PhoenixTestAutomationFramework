package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		System.out.println(firstName+ lastName);
		
		
		String buildingNumber = faker.address().buildingNumber();
		System.out.println(buildingNumber);
		
		System.out.println(faker.address().streetName());
		System.out.println(faker.address().city());
		System.out.println(faker.number().digits(10));
		System.out.println(faker.numerify("AIR#########"));
		
		System.out.println(faker.internet().emailAddress());
	}

}
