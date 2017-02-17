package com.bowling.alley;

import java.util.ArrayList;
import java.util.Scanner;

public class BowlingAlleyMain {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		
		try {
			int setScore = 0;
			//loop to control number of sets
			for(int i = 0; i < 10; i++) {
				
				int count = 0, strikeOne = 0, strikeTwo = 0, finalSetExtraStrike = 0; 
				boolean isStrike = false, isSpare = false, isStrikeFinalSet = false, isSpareFinalSet = false;
				
				//loop to control number of strikes per set
				while(count < 2) {				
					
					//count++;
					
					System.out.println("Enter pins striked in attempt " + (count + 1) + " of set " + (i + 1));
					
					//only integer values
					if(scan.hasNextInt()) {
						
						int userInput = scan.nextInt();
						
						//input range check
						if(userInput >= 0 && userInput <= 10) {
							
							count++;
							
							if(count == 1) {
								strikeOne = userInput;
								
								//bonus for strike
								if(strikeOne == 10) {
									strikeOne = strikeOne + 10;
									isStrike = true;
									
									//extra strike check if strike in final set
									if(i == 9) {
										isStrikeFinalSet = true;
									}
								}
								
							} else if(count == 2 && !(strikeOne == 10)) {
								strikeTwo = userInput;
								
								//bonus for strike
								if(strikeTwo == 10) {
									strikeTwo = strikeTwo + 10;
									isStrike = true;
									
									//extra strike check if strike in final set
									if(i == 9) {
										isStrikeFinalSet = true;
									}
								}
							}
							
						} else {
							System.out.println("Value entered not valid. Please re-enter.");
							scan = new Scanner (System.in);
						}
						
					} else {
						
						System.out.println("Only numeric values allowed");
						scan = new Scanner (System.in);
					}
				}
				
				setScore = setScore + strikeOne + strikeTwo;
				
				if((strikeOne + strikeTwo) == 10) {
					setScore = setScore + 5;
					isSpare = true;
					
					//extra strike check if spare in final set
					if(i == 9) {
						isSpareFinalSet = true;
					}
				}
				
				//check for extra strike in last set
				if(i == 9 && (isSpareFinalSet || isStrikeFinalSet)) {
					
					boolean retry = true;
					
					while(retry) {
						
						System.out.println("Enter pins striked in  extra attempt of last set " + (i + 1));
						
						//only integer values
						if(scan.hasNextInt()) {
							
							int userInput = scan.nextInt();
							
							//input range check
							if(userInput >= 0 && userInput <= 10) {
									
								finalSetExtraStrike = userInput;
								setScore = setScore + finalSetExtraStrike;
								retry = false;
									
							} else {
								System.out.println("Value entered not valid. Please re-enter.");
								scan = new Scanner(System.in);
							}
								
						} else {
							System.out.println("Only numeric values allowed");
							scan = new Scanner(System.in);
						}
					}
				}
				
				ArrayList<String> setScoreBreakUp = new ArrayList<String>();
				
				if(isStrike) {
					setScoreBreakUp.add(" ");
					setScoreBreakUp.add("X");
					
					if(i == 9 && (isSpareFinalSet || isStrikeFinalSet)) {
						setScoreBreakUp.add(String.valueOf(finalSetExtraStrike));
					}
					
					setScoreBreakUp.add(String.valueOf(setScore));
				} else if(isSpare) {
					setScoreBreakUp.add(String.valueOf(strikeOne));
					setScoreBreakUp.add("/");
					
					if(i == 9 && (isSpareFinalSet || isStrikeFinalSet)) {
						setScoreBreakUp.add(String.valueOf(finalSetExtraStrike));
					}
					
					setScoreBreakUp.add(String.valueOf(setScore));
				} else {
					setScoreBreakUp.add(String.valueOf(strikeOne));
					setScoreBreakUp.add(String.valueOf(strikeTwo));
					setScoreBreakUp.add(String.valueOf(setScore));
				}
				
				for(int j = 0; j < setScoreBreakUp.size(); j++) {
					
					if((setScoreBreakUp.size() - j) == 2) {
						System.out.println(setScoreBreakUp.get(j));
					} else {
						System.out.print(setScoreBreakUp.get(j) + " ");
					}
						
				}
			}
			
			System.out.println(setScore);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}
}