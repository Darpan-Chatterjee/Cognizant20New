package com.Cognizant.C2.Pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Cognizant.C2.Utility.GlobalVariable;
import com.Cognizant.C2.Utility.ObjectPropFetch;

public class CreateTaskPage {
	
	private String textArea,startDt,endDt,projAct,nonProjAct,phase,activity,process,addBtn,saveBtn;
	private String status,category,detail;
	private String resourcePicker,resourceDarpan,resourceListC2,resourceC2Part1,resourceC2Part2;
	private String resourceSave;
	
	public CreateTaskPage() throws Exception
	{
		textArea=ObjectPropFetch.objectProp("textArea");
		startDt=ObjectPropFetch.objectProp("startDt");
		endDt=ObjectPropFetch.objectProp("endDt");
		projAct=ObjectPropFetch.objectProp("projAct");
		nonProjAct=ObjectPropFetch.objectProp("nonProjAct");
		phase=ObjectPropFetch.objectProp("phase");
		activity=ObjectPropFetch.objectProp("activity");
		process=ObjectPropFetch.objectProp("process");
		addBtn=ObjectPropFetch.objectProp("addBtn");
		saveBtn=ObjectPropFetch.objectProp("saveBtn");	
		
		status=ObjectPropFetch.objectProp("status");
		category=ObjectPropFetch.objectProp("category");
		detail=ObjectPropFetch.objectProp("detail");
		resourcePicker=ObjectPropFetch.objectProp("resourcePicker");
		resourceDarpan=ObjectPropFetch.objectProp("resourceDarpan");
		resourceListC2=ObjectPropFetch.objectProp("resourceListC2");
		resourceC2Part1=ObjectPropFetch.objectProp("resourceC2Part1");
		resourceC2Part2=ObjectPropFetch.objectProp("resourceC2Part2");
		resourceSave=ObjectPropFetch.objectProp("resourceSave");
	}
	
	public void CreateTask(String task_type,String task_name,String phaseVal,String activityVal,String process_Task,String start_Dt,String end_Dt,String resource) throws InterruptedException
	{
		System.out.println("Current window Id in Create Task Page"+GlobalVariable.myDriver.getWindowHandle());
		
		WebDriverWait wt = new WebDriverWait(GlobalVariable.myDriver, 10);
		wt.until(ExpectedConditions.elementToBeClickable(GlobalVariable.myDriver.findElement(By.xpath(textArea))));
		
		GlobalVariable.myDriver.findElement(By.xpath(textArea)).sendKeys(task_name);
		Thread.sleep(2000);
		if(task_type.equals("Project Activity"))
		{
			GlobalVariable.myDriver.findElement(By.xpath(projAct)).click(); 
			Thread.sleep(1000);
			Select drpdwn1= new Select(GlobalVariable.myDriver.findElement(By.xpath(phase)));
			drpdwn1.selectByVisibleText(phaseVal);
			Thread.sleep(1000);
			Select drpdwn2= new Select(GlobalVariable.myDriver.findElement(By.xpath(activity)));
			drpdwn2.selectByVisibleText(activityVal);
			Thread.sleep(1000);
			Select drpdwn3= new Select(GlobalVariable.myDriver.findElement(By.xpath(process)));
			drpdwn3.selectByVisibleText(process_Task);
			Thread.sleep(1000);

			JavascriptExecutor js = (JavascriptExecutor) GlobalVariable.myDriver;
			WebElement el_StartDt = GlobalVariable.myDriver.findElement(By.xpath(startDt));
			WebElement el_EndDt = GlobalVariable.myDriver.findElement(By.xpath(endDt));
			GlobalVariable.myDriver.findElement(By.xpath(startDt)).click();
			js.executeScript("arguments[0].setAttribute('value', '"+start_Dt+"')",el_StartDt);
			Thread.sleep(1000);
			GlobalVariable.myDriver.findElement(By.xpath(endDt)).click();
			js.executeScript("arguments[0].setAttribute('value', '"+end_Dt+"')",el_EndDt);
			Thread.sleep(1000);
			GlobalVariable.myDriver.findElement(By.xpath(textArea)).click();
		}
		else if(task_type.equals("Non-Project Activity"))
		{
			GlobalVariable.myDriver.findElement(By.xpath(nonProjAct)).click(); 
			Thread.sleep(1000);
			Select drpdwn1= new Select(GlobalVariable.myDriver.findElement(By.xpath(status)));
			drpdwn1.selectByVisibleText(phaseVal);
			Thread.sleep(1000);
			Select drpdwn2= new Select(GlobalVariable.myDriver.findElement(By.xpath(category)));
			drpdwn2.selectByVisibleText(activityVal);
			Thread.sleep(1000);
			Select drpdwn3= new Select(GlobalVariable.myDriver.findElement(By.xpath(detail)));
			drpdwn3.selectByVisibleText(process_Task);
			Thread.sleep(1000);

			JavascriptExecutor js = (JavascriptExecutor) GlobalVariable.myDriver;
			WebElement el_StartDt = GlobalVariable.myDriver.findElement(By.xpath(startDt));
			WebElement el_EndDt = GlobalVariable.myDriver.findElement(By.xpath(endDt));
			GlobalVariable.myDriver.findElement(By.xpath(startDt)).click();
			js.executeScript("arguments[0].setAttribute('value', '"+start_Dt+"')",el_StartDt);
			Thread.sleep(1000);
			GlobalVariable.myDriver.findElement(By.xpath(endDt)).click();
			js.executeScript("arguments[0].setAttribute('value', '"+end_Dt+"')",el_EndDt);
			Thread.sleep(1000);
		}
		
		String parent_window= GlobalVariable.myDriver.getWindowHandle();
		GlobalVariable.myDriver.findElement(By.xpath(resourcePicker)).click();
		Thread.sleep(1000);
		/*Set<String>s1=GlobalVariable.myDriver.getWindowHandles();
		Iterator<String> I1= s1.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parent_window.equals(child_window))
			{
				System.out.println("Parent Id:"+ parent_window);
				System.out.println("Child Id:"+ child_window);
				GlobalVariable.myDriver.switchTo().window(child_window);
				Thread.sleep(2000);
			}
		}*/
		if(!resource.equals("Chatterjee,Darpan"))
		{
			GlobalVariable.myDriver.findElement(By.xpath(resourceDarpan)).click();
			Thread.sleep(2000);
			List<WebElement>resourceList=GlobalVariable.myDriver.findElements(By.xpath(resourceListC2));
			int counter =0;
			for(WebElement el_resource :resourceList)
			{
				counter++;
				if(el_resource.getText().equals(resource))
				{
					GlobalVariable.myDriver.findElement(By.xpath(resourceC2Part1+counter+resourceC2Part2)).click();
					Thread.sleep(2000);
				}
				System.out.println(el_resource.getText());
			}
		}
		GlobalVariable.myDriver.findElement(By.xpath(resourceSave)).click();
		Thread.sleep(1000);
		GlobalVariable.myDriver.switchTo().window(parent_window);
		GlobalVariable.myDriver.findElement(By.xpath(saveBtn)).click();
		Thread.sleep(2000);
		GlobalVariable.myDriver.findElement(By.xpath(addBtn)).click();
		Thread.sleep(1000);	
		//GlobalVariable.myDriver.findElement(By.xpath(textArea)).clear();
	}

}
