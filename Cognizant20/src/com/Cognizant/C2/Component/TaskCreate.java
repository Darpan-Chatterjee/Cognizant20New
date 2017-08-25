package com.Cognizant.C2.Component;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Cognizant.C2.Pages.CreateTaskPage;
import com.Cognizant.C2.Pages.loginPage;
import com.Cognizant.C2.Utility.FunctionLibrary;
import com.Cognizant.C2.Utility.GlobalVariable;
import com.Cognizant.C2.Utility.ObjectPropFetch;

public class TaskCreate {
	
	@BeforeTest
	public void Setup() throws Exception
	{
		GlobalVariable.loginWindow=ObjectPropFetch.objectProp("loginWindow");
		GlobalVariable.workTab=ObjectPropFetch.objectProp("workTab");
		GlobalVariable.create=ObjectPropFetch.objectProp("create");
		GlobalVariable.createFractal=ObjectPropFetch.objectProp("createFractal");
		
		GlobalVariable.task=ObjectPropFetch.objectProp("task");
		GlobalVariable.statusDrp=ObjectPropFetch.objectProp("statusDrp");
		GlobalVariable.planStrtDt=ObjectPropFetch.objectProp("planStrtDt");
		GlobalVariable.planEndDt=ObjectPropFetch.objectProp("planEndDt");
		GlobalVariable.actStrtDt=ObjectPropFetch.objectProp("actStrtDt");
		GlobalVariable.actEndDt=ObjectPropFetch.objectProp("actEndDt");
		GlobalVariable.DtPart1=ObjectPropFetch.objectProp("DtPart1");
		GlobalVariable.DtPart2=ObjectPropFetch.objectProp("DtPart2");
		GlobalVariable.actEffortImg=ObjectPropFetch.objectProp("actEffortImg");
		GlobalVariable.inputHrs=ObjectPropFetch.objectProp("inputHrs");
		GlobalVariable.taskSubSave=ObjectPropFetch.objectProp("taskSubSave");
		GlobalVariable.mandatoryChkList=ObjectPropFetch.objectProp("mandatoryChkList");
		GlobalVariable.mandatoryYes=ObjectPropFetch.objectProp("mandatoryYes");
		
		FunctionLibrary.browserOpen();
		  try
		  {
			 if(GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.loginWindow)).isDisplayed())
			 {
				 loginPage loginObj=new loginPage();
				 String uid=FunctionLibrary.ExceDataFetch("Login",1,1);
				 String pwd=FunctionLibrary.ExceDataFetch("Login",1,2);
				 loginObj.loginC2(uid, pwd);
			 }
		  }
		  catch(Exception e)
		  {
			  System.out.println("You are in Cognizant Intranet");
		  }
		  Thread.sleep(2000);
		  try
		  {
			  if(GlobalVariable.myDriver.getTitle().contains("Cognizant 2.0"))
			  {
				  System.out.println("Login Successful");
			  }
		  }
		  catch(Exception e)
		  {
			  System.out.println("Login Un-Successful");
			  System.exit(0);
		  }
		  
		  try
		  {
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.workTab)).click(); 
		  }
		  catch(NoSuchElementException e)
		  {
			  System.out.println("Work Tab is Not Clickable");
			  System.exit(0);
		  }
	}
	
	@AfterTest
	public void TearDown() throws Exception
	{
		GlobalVariable.myDriver.close();
	}
	
  @Test
  public void UserTaskCreate() throws Exception 
  {
	  String parent_window= GlobalVariable.myDriver.getWindowHandle();
	  Thread.sleep(2000);
	  WebElement el_Create=null;
	  WebElement el_CreateFractal=null;
	  try 
	  {
		  el_Create = GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.create));
		  el_CreateFractal = GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.createFractal));
	  } 
	  catch (Exception e) 
	  {
		  System.out.println("Create Task option is Not Clickable");
		  System.exit(0);
	  }
	  FunctionLibrary.MouseHover(el_Create);
	  Thread.sleep(1000);
	  FunctionLibrary.MouseHover(el_CreateFractal);

	  Set<String>s1=GlobalVariable.myDriver.getWindowHandles();
	  Iterator<String> I1= s1.iterator();
	  while(I1.hasNext())
	  {
		  String child_window=I1.next();
		  if(!parent_window.equals(child_window))
		  {
			  System.out.println("Parent Id:"+ parent_window);
			  System.out.println("Child Id:"+ child_window);
			  GlobalVariable.myDriver.switchTo().window(child_window);
			  Thread.sleep(3000);
		  }
	  }
	  CreateTaskPage CreateTaskPageObj= new CreateTaskPage();
	  for(int i=1;;i++)
	  {
		  try
		  {
			  if(!FunctionLibrary.ExceDataFetch("Details",i,1).equals(""))
			  {
				  String task_type=FunctionLibrary.ExceDataFetch("Details",i,1);
				  String task_name=FunctionLibrary.ExceDataFetch("Details",i,2);
				  String phase=FunctionLibrary.ExceDataFetch("Details",i,3);
				  String activity=FunctionLibrary.ExceDataFetch("Details",i,4);
				  String process_Task=FunctionLibrary.ExceDataFetch("Details",i,5);
				  String start_Dt=FunctionLibrary.ExceDataFetch("Details",i,6);
				  String end_Dt=FunctionLibrary.ExceDataFetch("Details",i,7);
				  String resource=FunctionLibrary.ExceDataFetch("Details",i,8);
				  CreateTaskPageObj.CreateTask(task_type,task_name,phase,activity,process_Task,start_Dt,end_Dt,resource); 
			  }
		  }
		  catch(NullPointerException ex)
		  {
			  System.out.println("All The Tasks Have Been Created");
			  GlobalVariable.myDriver.close();
			  GlobalVariable.myDriver.switchTo().window(parent_window);
			  GlobalVariable.myDriver.navigate().refresh();
			  break;
		  }

	  }	  	  
  }
  
  //@Test
  public void UserTaskSubmit() throws Exception 
  {
	  String parent_window= GlobalVariable.myDriver.getWindowHandle();
	  Thread.sleep(2000);
	  GlobalVariable.myDriver.switchTo().frame("ifrContent");
	  List<WebElement> el_task=null;
	  int taskCount=0;
	  Select drpDwnStatus=null;
	  try
	  {
		  el_task=GlobalVariable.myDriver.findElements(By.xpath(GlobalVariable.task));
		  taskCount=el_task.size();
		  System.out.println("No of Task :"+taskCount);
		  for(int i=1;i<=taskCount-1;i++)
		  {
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.task)).click();
			  Thread.sleep(4000);
			  int startDt=FunctionLibrary.DateExtract(GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.planStrtDt)).getText());
			  int endDt=FunctionLibrary.DateExtract(GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.planEndDt)).getText());
			  System.out.println("Planned Start Date:"+startDt);
			  System.out.println("Planned End Date:"+endDt);
			  drpDwnStatus=new Select(GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.statusDrp)));
			  drpDwnStatus.selectByVisibleText("Complete");
			  Thread.sleep(2000);
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.actStrtDt)).click();
			  Thread.sleep(1000);
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.DtPart1+startDt+GlobalVariable.DtPart2)).click();
			  Thread.sleep(1000);
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.actEndDt)).click();
			  Thread.sleep(1000);
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.DtPart1+endDt+GlobalVariable.DtPart2)).click();
			  Thread.sleep(1000);
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.actEffortImg)).click();
			  Thread.sleep(1000);
			  GlobalVariable.myDriver.switchTo().defaultContent();
			  Set<String>s1=GlobalVariable.myDriver.getWindowHandles();
			  Iterator<String> I1= s1.iterator();
			  while(I1.hasNext())
			  {
				  String child_window=I1.next();
				  if(!parent_window.equals(child_window))
				  {
					  System.out.println("Parent Id:"+ parent_window);
					  System.out.println("Child Id:"+ child_window);
					  GlobalVariable.myDriver.switchTo().window(child_window);
					  Thread.sleep(3000);
				  }
			  }
			  List<WebElement>inputHrsEL=GlobalVariable.myDriver.findElements(By.xpath(GlobalVariable.inputHrs));
			  for(WebElement el : inputHrsEL)
			  {
				  el.sendKeys("8");
				  Thread.sleep(1000);
			  }
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.taskSubSave)).click();
			  Thread.sleep(1000);
			  GlobalVariable.myDriver.switchTo().window(parent_window);
			  GlobalVariable.myDriver.switchTo().frame("ifrContent");
			  Thread.sleep(1000);
			  try
			  {
				  if(GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.mandatoryChkList)).isDisplayed())
				  {
					  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.mandatoryChkList)).click();
					  Thread.sleep(1000);
					  List<WebElement>yesRadio=GlobalVariable.myDriver.findElements(By.xpath(GlobalVariable.mandatoryYes));
					  if(yesRadio.size()>0)
					  {
						  for(WebElement el : yesRadio)
						  {
							  el.click();
							  Thread.sleep(1000);
						  }  
					  }
				  }
			  }
			  catch(Exception e)
			  {
				  System.out.println("No Mandatary Checklist Present");
			  }
			  GlobalVariable.myDriver.findElement(By.xpath(GlobalVariable.taskSubSave)).click();
			  Thread.sleep(3000);
			  GlobalVariable.myDriver.switchTo().defaultContent();
			  GlobalVariable.myDriver.navigate().refresh();
			  Thread.sleep(3000);
			  GlobalVariable.myDriver.switchTo().frame("ifrContent");
			  Thread.sleep(1000);
		  }
	  }
	  catch(Exception e)
	  {
		  System.out.println("No Task is available to submit");
		  System.exit(0); 
	  }	  
  }
  
}
