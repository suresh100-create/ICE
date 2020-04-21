package com.ice.problem1;

import static java.util.regex.Pattern.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CusipApp {

	public static void main(String args[]) {
		
		Pattern C_PATTERN = compile("[A-Za-z0-9]{8}");
		Pattern P_PATTERN = compile("[0-9]*(\\.[0-9]+)?");
		String fileName = AppConfigCommon.getPathToTargetFile(args);
		List<String> lines = new ArrayList<>();
// pass the file name in the command argument..else it takes the test.txt file from src/main/resources/ folder
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			lines = br.lines().collect(Collectors.toList());

			LinkedHashMap<Integer, String> tempCusipMap = new LinkedHashMap<>();
			LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();

			int cusipHeaderRow = 0;
			String lastPricerow = "";
			for (String line : lines) {
				Matcher matchPattern = C_PATTERN.matcher(line);
				if (matchPattern.matches()) {
					tempCusipMap.put(cusipHeaderRow, line);
					if (cusipHeaderRow > 0) {
						String keyCUSIP = tempCusipMap.get(cusipHeaderRow - 1);
						String valuePrice = lastPricerow;
						resultMap.put(keyCUSIP, valuePrice);
						lastPricerow = "";
					}
					cusipHeaderRow++;

				} else {
					matchPattern.usePattern(P_PATTERN);
					if (matchPattern.matches()) {
						lastPricerow = line;
					}

				}

			}
                        if(cusipHeaderRow>1) {
			String keyCUSIP = tempCusipMap.get(cusipHeaderRow - 1);
			String valuePrice = lastPricerow;
			resultMap.put(keyCUSIP, valuePrice);
		        }
			resultMap.entrySet().forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
