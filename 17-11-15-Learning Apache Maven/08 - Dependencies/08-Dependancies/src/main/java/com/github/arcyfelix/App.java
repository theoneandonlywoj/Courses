package com.github.arcyfelix;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
        System.out.println( "Provide a non numeric String:" );
        
        if(isNumeric(scanner.nextLine())){
        	System.out.println("The provided String is numeric!");
        }else{
        	System.out.println("The provided String is valid.");
        }
        scanner.close();
    }

	private static boolean isNumeric(String nextLine) {
		return StringUtils.isNumeric(nextLine);
	}
}
