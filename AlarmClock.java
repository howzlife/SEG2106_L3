/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.5095 modeling language!*/



/**
 * This is the flat version of the state machine
 */
// line 3 "model.ump"
// line 100 "model.ump"
// line 105 "model.ump"
public class AlarmClock
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AlarmClock Attributes
  private int currentHour;
  private int currentMinutes;
  private int alarmHour;
  private int alarmMinutes;
  private int soundLevel;
  private boolean alarmSet;
  private String alarmType;

  //AlarmClock State Machines
  enum AlarmSystem { alarmNotActive, alarmActive, showAlarmTime, setTime, triggerRadio, triggerSound }
  enum AlarmSystemShowAlarmTime { Null, setAlarmTime }
  enum AlarmSystemSetTime { Null, displayTime }
  enum AlarmSystemTriggerRadio { Null, playRadio, playingRadio, snooze }
  enum AlarmSystemTriggerSound { Null, playSound, emitSoundNotMaxLevel, emitSoundMaxLevel }
  enum AlarmSystemTriggerSoundEmitSoundNotMaxLevel { Null, firstMinute, increaseToMaxLevel }
  enum AlarmSystemTriggerSoundEmitSoundMaxLevel { Null, first2Minutes, snoozeSound }
  private AlarmSystem alarmSystem;
  private AlarmSystemShowAlarmTime alarmSystemShowAlarmTime;
  private AlarmSystemSetTime alarmSystemSetTime;
  private AlarmSystemTriggerRadio alarmSystemTriggerRadio;
  private AlarmSystemTriggerSound alarmSystemTriggerSound;
  private AlarmSystemTriggerSoundEmitSoundNotMaxLevel alarmSystemTriggerSoundEmitSoundNotMaxLevel;
  private AlarmSystemTriggerSoundEmitSoundMaxLevel alarmSystemTriggerSoundEmitSoundMaxLevel;
  
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AlarmClock()
  {
    currentHour = 0;
    currentMinutes = 0;
    alarmHour = 0;
    alarmMinutes = 0;
    soundLevel = 1;
    alarmSet = false;
    setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.Null);
    setAlarmSystemSetTime(AlarmSystemSetTime.Null);
    setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.Null);
    setAlarmSystemTriggerSound(AlarmSystemTriggerSound.Null);
    setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel.Null);
    setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.Null);
    setAlarmSystem(AlarmSystem.alarmNotActive);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean incrementCurrentHour()
  {
    boolean wasSet = false;
    currentHour++;
    currentHour %= 24;
    wasSet = true;
    return wasSet;
  }

  public boolean incrementCurrentMinutes(int aCurrentMinutes)
  {
    boolean wasSet = false;
    currentMinutes += aCurrentMinutes;
    currentMinutes %= 60;
    wasSet = true;
    return wasSet;
  }

  public boolean incrementAlarmHour()
  {
    boolean wasSet = false;
    alarmHour += 1;
    alarmHour %= 24;
    wasSet = true;
    return wasSet;
  }

  public boolean incrementAlarmMinute(int aAlarmMinute)
  {
    boolean wasSet = false;
    alarmMinutes += aAlarmMinute;
    alarmMinutes %= 60;
    wasSet = true;
    return wasSet;
  }

  public int getCurrentHour()
  {
    return currentHour;
  }

  public int getCurrentMinutes()
  {
    return currentMinutes;
  }

  public int getAlarmHour()
  {
    return alarmHour;
  }

  public int getAlarmMinutes()
  {
    return alarmMinutes;
  }
  
  public boolean getAlarmActive() {
	  if (alarmSet) return true; else return false;
  }
  
  public String getAlarmSystemFullName()
  {
    String answer = alarmSystem.toString();
    if (alarmSystemShowAlarmTime != AlarmSystemShowAlarmTime.Null) { answer += "." + alarmSystemShowAlarmTime.toString(); }
    if (alarmSystemSetTime != AlarmSystemSetTime.Null) { answer += "." + alarmSystemSetTime.toString(); }
    if (alarmSystemTriggerRadio != AlarmSystemTriggerRadio.Null) { answer += "." + alarmSystemTriggerRadio.toString(); }
    if (alarmSystemTriggerSound != AlarmSystemTriggerSound.Null) { answer += "." + alarmSystemTriggerSound.toString(); }
    if (alarmSystemTriggerSoundEmitSoundNotMaxLevel != AlarmSystemTriggerSoundEmitSoundNotMaxLevel.Null) { answer += "." + alarmSystemTriggerSoundEmitSoundNotMaxLevel.toString(); }
    if (alarmSystemTriggerSoundEmitSoundMaxLevel != AlarmSystemTriggerSoundEmitSoundMaxLevel.Null) { answer += "." + alarmSystemTriggerSoundEmitSoundMaxLevel.toString(); }
    return answer;
  }

  public AlarmSystem getAlarmSystem()
  {
    return alarmSystem;
  }

  public AlarmSystemShowAlarmTime getAlarmSystemShowAlarmTime()
  {
    return alarmSystemShowAlarmTime;
  }

  public AlarmSystemSetTime getAlarmSystemSetTime()
  {
    return alarmSystemSetTime;
  }

  public AlarmSystemTriggerRadio getAlarmSystemTriggerRadio()
  {
    return alarmSystemTriggerRadio;
  }

  public AlarmSystemTriggerSound getAlarmSystemTriggerSound()
  {
    return alarmSystemTriggerSound;
  }

  public AlarmSystemTriggerSoundEmitSoundNotMaxLevel getAlarmSystemTriggerSoundEmitSoundNotMaxLevel()
  {
    return alarmSystemTriggerSoundEmitSoundNotMaxLevel;
  }

  public AlarmSystemTriggerSoundEmitSoundMaxLevel getAlarmSystemTriggerSoundEmitSoundMaxLevel()
  {
    return alarmSystemTriggerSoundEmitSoundMaxLevel;
  }

  public boolean rest()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmNotActive:
        setAlarmSystem(AlarmSystem.alarmNotActive);
        wasEventProcessed = true;
        break;
      case alarmActive:
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }
  
  public boolean setSoundLevel(int x) {
	  soundLevel = x;
	  return true;
  }
  
  public int getSoundLevel() {
	  return soundLevel;
  }
  
  public boolean setAlarm()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmNotActive:
        setAlarmSystem(AlarmSystem.showAlarmTime);
        wasEventProcessed = true;
        break;
      case alarmActive:
        setAlarmSystem(AlarmSystem.showAlarmTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setTime()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmNotActive:
        setAlarmSystem(AlarmSystem.setTime);
        wasEventProcessed = true;
        break;
      case alarmActive:
        setAlarmSystem(AlarmSystem.setTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean getActiveAlarmState() {
	  if (alarmType == ("radio")) return true; 
	  return false;
  }
  
  public boolean activateAlarm(int x)
  {
    boolean wasEventProcessed = false;
	if (x == 0) alarmType = "radio"; else alarmType = "sound";
    alarmSet = true;
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmNotActive:
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean deActivateAlarm()
  {
    boolean wasEventProcessed = false;
    alarmSet = false;
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmActive:
        setAlarmSystem(AlarmSystem.alarmNotActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean alarmTriggeredRadio()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmActive:
        setAlarmSystem(AlarmSystem.triggerRadio);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean alarmTriggeredSound()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystem aAlarmSystem = alarmSystem;
    switch (aAlarmSystem)
    {
      case alarmActive:
        setAlarmSystem(AlarmSystem.triggerSound);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterShowAlarmTime()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case Null:
        setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.setAlarmTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitShowAlarmTime()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case setAlarmTime:
        setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean increaseMin(int amount)
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case setAlarmTime:
        // line 27 "model.ump"
        setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.setAlarmTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemSetTime)
    {
      case displayTime:
        // line 36 "model.ump"
        setAlarmSystem(AlarmSystem.setTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean increaseHour()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case setAlarmTime:
        // line 28 "model.ump"
        setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.setAlarmTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemSetTime)
    {
      case displayTime:
        // line 37 "model.ump"
        setAlarmSystem(AlarmSystem.setTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean holdDownMin()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case setAlarmTime:
        // line 29 "model.ump"
        setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.setAlarmTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemSetTime)
    {
      case displayTime:
        // line 38 "model.ump"
        setAlarmSystem(AlarmSystem.setTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setAlarmReleasedA()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case setAlarmTime:
        exitAlarmSystem();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setAlarmReleaseIA()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime = alarmSystemShowAlarmTime;
    switch (aAlarmSystemShowAlarmTime)
    {
      case setAlarmTime:
        exitAlarmSystem();
        setAlarmSystem(AlarmSystem.alarmNotActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterSetTime()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemSetTime)
    {
      case Null:
        setAlarmSystemSetTime(AlarmSystemSetTime.displayTime);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitSetTime()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemSetTime)
    {
      case displayTime:
        setAlarmSystemSetTime(AlarmSystemSetTime.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setTimeReleasedA()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemSetTime)
    {
      case displayTime:
        exitAlarmSystem();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setTimeReleaseIA()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemSetTime aAlarmSystemSetTime = alarmSystemSetTime;
    switch (aAlarmSystemSetTime)
    {
      case displayTime:
        exitAlarmSystem();
        setAlarmSystem(AlarmSystem.alarmNotActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterTriggerRadio()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerRadio aAlarmSystemTriggerRadio = alarmSystemTriggerRadio;
    switch (aAlarmSystemTriggerRadio)
    {
      case Null:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.playRadio);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitTriggerRadio()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerRadio aAlarmSystemTriggerRadio = alarmSystemTriggerRadio;
    switch (aAlarmSystemTriggerRadio)
    {
      case playRadio:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.Null);
        wasEventProcessed = true;
        break;
      case playingRadio:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.Null);
        wasEventProcessed = true;
        break;
      case snooze:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean outputRadio()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerRadio aAlarmSystemTriggerRadio = alarmSystemTriggerRadio;
    switch (aAlarmSystemTriggerRadio)
    {
      case playRadio:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.playingRadio);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean snoozeHit()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerRadio aAlarmSystemTriggerRadio = alarmSystemTriggerRadio;
    AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel = alarmSystemTriggerSoundEmitSoundNotMaxLevel;
    AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel = alarmSystemTriggerSoundEmitSoundMaxLevel;
    switch (aAlarmSystemTriggerRadio)
    {
      case playingRadio:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.snooze);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
    {
      case firstMinute:
        exitAlarmSystemTriggerSound();
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.snooze);
        wasEventProcessed = true;
        break;
      case increaseToMaxLevel:
        exitAlarmSystemTriggerSound();
        setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.snoozeSound);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundMaxLevel)
    {
      case first2Minutes:
        setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.snoozeSound);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean sleepHit()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerRadio aAlarmSystemTriggerRadio = alarmSystemTriggerRadio;
    AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel = alarmSystemTriggerSoundEmitSoundNotMaxLevel;
    AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel = alarmSystemTriggerSoundEmitSoundMaxLevel;
    switch (aAlarmSystemTriggerRadio)
    {
      case playingRadio:
        exitAlarmSystem();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      case snooze:
        exitAlarmSystem();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
    {
      case firstMinute:
        exitAlarmSystemTriggerSound();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      case increaseToMaxLevel:
        exitAlarmSystemTriggerSound();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundMaxLevel)
    {
      case first2Minutes:
        exitAlarmSystemTriggerSound();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      case snoozeSound:
        exitAlarmSystemTriggerSound();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean wait5Mins()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerRadio aAlarmSystemTriggerRadio = alarmSystemTriggerRadio;
    AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel = alarmSystemTriggerSoundEmitSoundMaxLevel;
    switch (aAlarmSystemTriggerRadio)
    {
      case snooze:
        setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.playRadio);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundMaxLevel)
    {
      case snoozeSound:
        setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.first2Minutes);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterTriggerSound()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSound aAlarmSystemTriggerSound = alarmSystemTriggerSound;
    AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel = alarmSystemTriggerSoundEmitSoundNotMaxLevel;
    AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel = alarmSystemTriggerSoundEmitSoundMaxLevel;
    switch (aAlarmSystemTriggerSound)
    {
      case Null:
        setAlarmSystemTriggerSound(AlarmSystemTriggerSound.playSound);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
    {
      case Null:
        setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel.firstMinute);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundMaxLevel)
    {
      case Null:
        setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.first2Minutes);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitTriggerSound()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSound aAlarmSystemTriggerSound = alarmSystemTriggerSound;
    AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel = alarmSystemTriggerSoundEmitSoundNotMaxLevel;
    AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel = alarmSystemTriggerSoundEmitSoundMaxLevel;
    switch (aAlarmSystemTriggerSound)
    {
      case playSound:
        setAlarmSystemTriggerSound(AlarmSystemTriggerSound.Null);
        wasEventProcessed = true;
        break;
      case emitSoundNotMaxLevel:
        setAlarmSystemTriggerSound(AlarmSystemTriggerSound.Null);
        wasEventProcessed = true;
        break;
      case emitSoundMaxLevel:
        setAlarmSystemTriggerSound(AlarmSystemTriggerSound.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
    {
      case firstMinute:
        setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel.Null);
        wasEventProcessed = true;
        break;
      case increaseToMaxLevel:
        setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aAlarmSystemTriggerSoundEmitSoundMaxLevel)
    {
      case first2Minutes:
        setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.Null);
        wasEventProcessed = true;
        break;
      case snoozeSound:
        setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean outputSoundL1()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSound aAlarmSystemTriggerSound = alarmSystemTriggerSound;
    switch (aAlarmSystemTriggerSound)
    {
      case playSound:
        setAlarmSystemTriggerSound(AlarmSystemTriggerSound.emitSoundNotMaxLevel);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean outputSoundL2()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSound aAlarmSystemTriggerSound = alarmSystemTriggerSound;
    switch (aAlarmSystemTriggerSound)
    {
      case playSound:
        setAlarmSystemTriggerSound(AlarmSystemTriggerSound.emitSoundMaxLevel);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean wait1Minute()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel = alarmSystemTriggerSoundEmitSoundNotMaxLevel;
    switch (aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
    {
      case firstMinute:
        setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel.increaseToMaxLevel);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean wait1minute()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel = alarmSystemTriggerSoundEmitSoundNotMaxLevel;
    switch (aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
    {
      case increaseToMaxLevel:
        exitAlarmSystemTriggerSound();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean wait2Minutes()
  {
    boolean wasEventProcessed = false;
    
    AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel = alarmSystemTriggerSoundEmitSoundMaxLevel;
    switch (aAlarmSystemTriggerSoundEmitSoundMaxLevel)
    {
      case first2Minutes:
        exitAlarmSystemTriggerSound();
        setAlarmSystem(AlarmSystem.alarmActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitAlarmSystem()
  {
    switch(alarmSystem)
    {
      case showAlarmTime:
        exitShowAlarmTime();
        break;
      case setTime:
        exitSetTime();
        break;
      case triggerRadio:
        exitTriggerRadio();
        break;
      case triggerSound:
        exitTriggerSound();
        break;
    }
  }

  private void setAlarmSystem(AlarmSystem aAlarmSystem)
  {
    alarmSystem = aAlarmSystem;

    // entry actions and do activities
    switch(alarmSystem)
    {
      case showAlarmTime:
        if (alarmSystemShowAlarmTime == AlarmSystemShowAlarmTime.Null) { setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime.setAlarmTime); }
        break;
      case setTime:
        if (alarmSystemSetTime == AlarmSystemSetTime.Null) { setAlarmSystemSetTime(AlarmSystemSetTime.displayTime); }
        break;
      case triggerRadio:
        if (alarmSystemTriggerRadio == AlarmSystemTriggerRadio.Null) { setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio.playRadio); }
        break;
      case triggerSound:
        if (alarmSystemTriggerSound == AlarmSystemTriggerSound.Null) { setAlarmSystemTriggerSound(AlarmSystemTriggerSound.playSound); }
        break;
    }
  }

  private void setAlarmSystemShowAlarmTime(AlarmSystemShowAlarmTime aAlarmSystemShowAlarmTime)
  {
    alarmSystemShowAlarmTime = aAlarmSystemShowAlarmTime;
    if (alarmSystem != AlarmSystem.showAlarmTime && aAlarmSystemShowAlarmTime != AlarmSystemShowAlarmTime.Null) { setAlarmSystem(AlarmSystem.showAlarmTime); }
  }

  private void setAlarmSystemSetTime(AlarmSystemSetTime aAlarmSystemSetTime)
  {
    alarmSystemSetTime = aAlarmSystemSetTime;
    if (alarmSystem != AlarmSystem.setTime && aAlarmSystemSetTime != AlarmSystemSetTime.Null) { setAlarmSystem(AlarmSystem.setTime); }
  }

  private void setAlarmSystemTriggerRadio(AlarmSystemTriggerRadio aAlarmSystemTriggerRadio)
  {
    alarmSystemTriggerRadio = aAlarmSystemTriggerRadio;
    if (alarmSystem != AlarmSystem.triggerRadio && aAlarmSystemTriggerRadio != AlarmSystemTriggerRadio.Null) { setAlarmSystem(AlarmSystem.triggerRadio); }
  }

  private void exitAlarmSystemTriggerSound()
  {
    switch(alarmSystemTriggerSound)
    {
      case emitSoundNotMaxLevel:
        exitTriggerSound();
        break;
      case emitSoundMaxLevel:
        exitTriggerSound();
        break;
    }
  }

  private void setAlarmSystemTriggerSound(AlarmSystemTriggerSound aAlarmSystemTriggerSound)
  {
    alarmSystemTriggerSound = aAlarmSystemTriggerSound;
    if (alarmSystem != AlarmSystem.triggerSound && aAlarmSystemTriggerSound != AlarmSystemTriggerSound.Null) { setAlarmSystem(AlarmSystem.triggerSound); }

    // entry actions and do activities
    switch(alarmSystemTriggerSound)
    {
      case emitSoundNotMaxLevel:
        if (alarmSystemTriggerSoundEmitSoundNotMaxLevel == AlarmSystemTriggerSoundEmitSoundNotMaxLevel.Null) { setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel.firstMinute); }
        break;
      case emitSoundMaxLevel:
        if (alarmSystemTriggerSoundEmitSoundMaxLevel == AlarmSystemTriggerSoundEmitSoundMaxLevel.Null) { setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel.first2Minutes); }
        break;
    }
  }

  private void setAlarmSystemTriggerSoundEmitSoundNotMaxLevel(AlarmSystemTriggerSoundEmitSoundNotMaxLevel aAlarmSystemTriggerSoundEmitSoundNotMaxLevel)
  {
    alarmSystemTriggerSoundEmitSoundNotMaxLevel = aAlarmSystemTriggerSoundEmitSoundNotMaxLevel;
    if (alarmSystemTriggerSound != AlarmSystemTriggerSound.emitSoundNotMaxLevel && aAlarmSystemTriggerSoundEmitSoundNotMaxLevel != AlarmSystemTriggerSoundEmitSoundNotMaxLevel.Null) { setAlarmSystemTriggerSound(AlarmSystemTriggerSound.emitSoundNotMaxLevel); }
  }

  private void setAlarmSystemTriggerSoundEmitSoundMaxLevel(AlarmSystemTriggerSoundEmitSoundMaxLevel aAlarmSystemTriggerSoundEmitSoundMaxLevel)
  {
    alarmSystemTriggerSoundEmitSoundMaxLevel = aAlarmSystemTriggerSoundEmitSoundMaxLevel;
    if (alarmSystemTriggerSound != AlarmSystemTriggerSound.emitSoundMaxLevel && aAlarmSystemTriggerSoundEmitSoundMaxLevel != AlarmSystemTriggerSoundEmitSoundMaxLevel.Null) { setAlarmSystemTriggerSound(AlarmSystemTriggerSound.emitSoundMaxLevel); }
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "[Current Time: "+
            "currentHours" + ":" + getCurrentHour()+ "," +
            "currentMinutes" + ":" + getCurrentMinutes()+ ", Alarm Time: " +
            "alarmHour" + ":" + getAlarmHour()+ "," +
            "alarmMinute" + ":" + getAlarmMinutes()+ "]"
     + outputString;
  }
}