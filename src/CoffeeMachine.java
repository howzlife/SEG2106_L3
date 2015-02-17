/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.5095 modeling language!*/
import java.util.*;


// line 2 "model.ump"
// line 80 "model.ump"
public class CoffeeMachine
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CoffeeMachine Attributes
  private int cups;
  private int balance;
  private boolean water;
  private Delivery del;

  //CoffeeMachine State Machines
  enum Machine { Idle, CheckBalance, CheckCups, Active, MakingTea, MakeTea, MakeCoffee, AddSugar, ReturnCoin }
  enum MachineMakingTea { Null, WaterOk, HeatWater, FillWater, CupOfWater }
  private Machine machine;
  private MachineMakingTea machineMakingTea;

  //Helper Variables
  private TimedEventHandler timeoutActiveToReturnCoinHandler;
  private TimedEventHandler timeoutMakeTeaToCheckCupsHandler;
  private TimedEventHandler timeoutMakeCoffeeToCheckCupsHandler;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CoffeeMachine(Delivery del)
  {
	this.del = del;
    cups = 10;
    balance = 0;
    water = true;
    setMachineMakingTea(MachineMakingTea.Null);
    setMachine(Machine.Idle);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCups(int aCups)
  {
    boolean wasSet = false;
    cups = aCups;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(int aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public boolean setWater(boolean aWater)
  {
    boolean wasSet = false;
    water = aWater;
    wasSet = true;
    return wasSet;
  }

  public int getCups()
  {
    return cups;
  }

  public int getBalance()
  {
    return balance;
  }

  public boolean getWater()
  {
    return water;
  }

  public String getMachineFullName()
  {
    String answer = machine.toString();
    if (machineMakingTea != MachineMakingTea.Null) { answer += "." + machineMakingTea.toString(); }
    return answer;
  }

  public Machine getMachine()
  {
    return machine;
  }

  public MachineMakingTea getMachineMakingTea()
  {
    return machineMakingTea;
  }

  public boolean insert(int amount)
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case Idle:
        // line 10 "model.ump"
        addMoney(amount);
        setMachine(Machine.CheckCups);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition1__()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case CheckBalance:
        if (getBalance()==0)
        {
          setMachine(Machine.Idle);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition2__()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case CheckBalance:
        setMachine(Machine.Active);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition3__()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case CheckCups:
        if (getCups()>0)
        {
          setMachine(Machine.CheckBalance);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition4__()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case CheckCups:
        setMachine(Machine.ReturnCoin);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean tea()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case Active:
        if (getBalance()>4)
        {
          setMachine(Machine.MakingTea);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean coffee()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case Active:
        if (getBalance()>9)
        {
          setMachine(Machine.MakeCoffee);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean timeoutActiveToReturnCoin()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case Active:
        setMachine(Machine.ReturnCoin);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean timeoutMakeTeaToCheckCups()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case MakeTea:
        exitMachine();
        setMachine(Machine.CheckCups);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean sugar()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case MakeTea:
        exitMachine();
        setMachine(Machine.AddSugar);
        wasEventProcessed = true;
        break;
      case MakeCoffee:
        exitMachine();
        setMachine(Machine.AddSugar);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean timeoutMakeCoffeeToCheckCups()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case MakeCoffee:
        exitMachine();
        setMachine(Machine.CheckCups);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition5__()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case AddSugar:
        setMachine(Machine.CheckCups);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition6__()
  {
    boolean wasEventProcessed = false;
    
    Machine aMachine = machine;
    switch (aMachine)
    {
      case ReturnCoin:
        setMachine(Machine.Idle);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterMakingTea()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case Null:
        setMachineMakingTea(MachineMakingTea.WaterOk);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitMakingTea()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case WaterOk:
        setMachineMakingTea(MachineMakingTea.Null);
        wasEventProcessed = true;
        break;
      case HeatWater:
        setMachineMakingTea(MachineMakingTea.Null);
        wasEventProcessed = true;
        break;
      case FillWater:
        setMachineMakingTea(MachineMakingTea.Null);
        wasEventProcessed = true;
        break;
      case CupOfWater:
        setMachineMakingTea(MachineMakingTea.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean waterOk()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case WaterOk:
        if (getWater()==true)
        {
          setMachineMakingTea(MachineMakingTea.HeatWater);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean waterNotOkay()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case WaterOk:
        if (getWater()==false)
        {
          exitMachine();
          setMachine(Machine.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean waterHeated()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case HeatWater:
        setMachineMakingTea(MachineMakingTea.FillWater);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean warm()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case FillWater:
        setMachineMakingTea(MachineMakingTea.CupOfWater);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean dispenseWater()
  {
    boolean wasEventProcessed = false;
    
    MachineMakingTea aMachineMakingTea = machineMakingTea;
    switch (aMachineMakingTea)
    {
      case CupOfWater:
        exitMachine();
        setMachine(Machine.MakeTea);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitMachine()
  {
    switch(machine)
    {
      case MakingTea:
        exitMakingTea();
        break;
      case MakeTea:
        // line 43 "model.ump"
        removeCup();
        // line 44 "model.ump"
        removeMoney(5);
        break;
      case MakeCoffee:
        // line 49 "model.ump"
        removeCup();
        // line 50 "model.ump"
        removeMoney(10);
        break;
    }
  }

  private void setMachine(Machine aMachine)
  {
    machine = aMachine;

    // entry actions and do activities
    switch(machine)
    {
      case CheckBalance:
        __autotransition1__();
        __autotransition2__();
        break;
      case CheckCups:
        __autotransition3__();
        __autotransition4__();
        break;
      case MakingTea:
        if (machineMakingTea == MachineMakingTea.Null) { setMachineMakingTea(MachineMakingTea.WaterOk); }
        break;
      case AddSugar:
        __autotransition5__();
        break;
      case ReturnCoin:
        // line 58 "model.ump"
        empty();
        __autotransition6__();
        break;
    }
  }

  private void setMachineMakingTea(MachineMakingTea aMachineMakingTea)
  {
    machineMakingTea = aMachineMakingTea;
    if (machine != Machine.MakingTea && aMachineMakingTea != MachineMakingTea.Null) { setMachine(Machine.MakingTea); }
  }

  private void startTimeoutActiveToReturnCoinHandler()
  {
    timeoutActiveToReturnCoinHandler = new TimedEventHandler(this,"timeoutActiveToReturnCoin",20);
  }

  private void stopTimeoutActiveToReturnCoinHandler()
  {
    timeoutActiveToReturnCoinHandler.stop();
  }

  private void startTimeoutMakeTeaToCheckCupsHandler()
  {
    timeoutMakeTeaToCheckCupsHandler = new TimedEventHandler(this,"timeoutMakeTeaToCheckCups",5);
  }

  private void stopTimeoutMakeTeaToCheckCupsHandler()
  {
    timeoutMakeTeaToCheckCupsHandler.stop();
  }

  private void startTimeoutMakeCoffeeToCheckCupsHandler()
  {
    timeoutMakeCoffeeToCheckCupsHandler = new TimedEventHandler(this,"timeoutMakeCoffeeToCheckCups",5);
  }

  private void stopTimeoutMakeCoffeeToCheckCupsHandler()
  {
    timeoutMakeCoffeeToCheckCupsHandler.stop();
  }

  public static class TimedEventHandler extends TimerTask  
  {
    private CoffeeMachine controller;
    private String timeoutMethodName;
    private double howLongInSeconds;
    private Timer timer;
    
    public TimedEventHandler(CoffeeMachine aController, String aTimeoutMethodName, double aHowLongInSeconds)
    {
      controller = aController;
      timeoutMethodName = aTimeoutMethodName;
      howLongInSeconds = aHowLongInSeconds;
      timer = new Timer();
      timer.schedule(this, (long)howLongInSeconds*1000);
    }
    
    public void stop()
    {
      timer.cancel();
    }
    
    public void run ()
    {
      if ("timeoutActiveToReturnCoin".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutActiveToReturnCoin();
        if (shouldRestart)
        {
          controller.startTimeoutActiveToReturnCoinHandler();
        }
        return;
      }
      if ("timeoutMakeTeaToCheckCups".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutMakeTeaToCheckCups();
        if (shouldRestart)
        {
          controller.startTimeoutMakeTeaToCheckCupsHandler();
        }
        return;
      }
      if ("timeoutMakeCoffeeToCheckCups".equals(timeoutMethodName))
      {
        boolean shouldRestart = !controller.timeoutMakeCoffeeToCheckCups();
        if (shouldRestart)
        {
          controller.startTimeoutMakeCoffeeToCheckCupsHandler();
        }
        return;
      }
    }
  }

  public void delete()
  {}

  // line 64 "model.ump"
  public void addMoney(int a){
    balance += a;
  }

  // line 68 "model.ump"
  public void removeMoney(int a){
    balance -= a;
  }

  // line 72 "model.ump"
  public void empty(){
    balance = 0;
  }

  // line 76 "model.ump"
  public void removeCup(){
    cups--;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "cups" + ":" + getCups()+ "," +
            "balance" + ":" + getBalance()+ "," +
            "water" + ":" + getWater()+ "]"
     + outputString;
  }
}