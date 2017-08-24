package com.Cognizant.C2.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ObjectPropFetch {
	
	public static String objectProp(String obj) throws Exception
	{
		File file = new File("..\\Cognizant20\\Object.Properties");
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(obj);	
	}

}
