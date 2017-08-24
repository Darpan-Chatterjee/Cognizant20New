package com.Cognizant.C2.Pages;

import org.openqa.selenium.By;

import com.Cognizant.C2.Utility.GlobalVariable;
import com.Cognizant.C2.Utility.ObjectPropFetch;

public class loginPage {
	
	private String loginId;
	private String password;
	private String loginBtn;
	
	public loginPage() throws Exception
	{
		loginId=ObjectPropFetch.objectProp("loginId");
		password=ObjectPropFetch.objectProp("password");
		loginBtn=ObjectPropFetch.objectProp("loginBtn");	
	}
	
	public void loginC2(String uid,String pwd) throws Exception
	{
		GlobalVariable.myDriver.findElement(By.xpath(loginId)).sendKeys(uid);
		Thread.sleep(2000);
		GlobalVariable.myDriver.findElement(By.xpath(password)).sendKeys(pwd);
		Thread.sleep(2000);
		GlobalVariable.myDriver.findElement(By.xpath(loginBtn)).click();
		Thread.sleep(2000);
	}

}
