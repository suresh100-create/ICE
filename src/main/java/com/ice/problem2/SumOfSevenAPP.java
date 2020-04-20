package com.ice.problem2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SumOfSevenAPP {

	public static void main(String[] args) {

		System.out.println(" Enter the values e.g: 1,2,4,5,6,7 :");
		String str;
		Scanner readIn = new Scanner(System.in);
		str = readIn.nextLine();
		String[] nums = str.split(",");
		int[] inputArray = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			inputArray[i] = Integer.parseInt(nums[i]);
			// System.out.println(inputArray[i]);

		}

		 readIn.close();

		Arrays.sort(inputArray);
		HashMap<Integer, Integer> res = new HashMap<>();
		for (int i = 0; i < inputArray.length; i++) {
			for (int j = i + 1; j < inputArray.length; j++) {

				if ((inputArray[i] + inputArray[j]) == 7) {

					if (!(res.containsKey(inputArray[i]))) {
						res.put(inputArray[i], inputArray[j]);
					}

				}
			}
		}

		res.entrySet().forEach(e -> {
			System.out.print("{" + e.getKey() + "," + e.getValue() + "}");

		});

	}

}
