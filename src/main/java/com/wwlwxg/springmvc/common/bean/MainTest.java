package com.wwlwxg.springmvc.common.bean;

import java.io.IOException;

import org.iherus.codegen.qrcode.QrcodeGenerator;
import org.iherus.codegen.qrcode.SimpleQrcodeGenerator;

public class MainTest {
	public static void main(String[] args) throws IOException {
		QrcodeGenerator generator = new SimpleQrcodeGenerator();
		generator.generate("送我五块钱，你会走大运O(∩_∩)O").toFile("C:/Users/jecky/Desktop//a.jpg");
	}
}
