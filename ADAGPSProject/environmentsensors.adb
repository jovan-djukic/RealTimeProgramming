with Ada.Real_Time; use Ada.Real_Time;
with Ada.Text_IO;
with Ada.Numerics.Float_Random;

package body EnvironmentSensors is
   task body SensorType is 
      randomGenerator : Ada.Numerics.Float_Random.Generator;
   begin
      Ada.Numerics.Float_Random.Reset(randomGenerator);
      loop
	 accept query(error : out Boolean; value : out Float) do
	    if (Ada.Numerics.Float_Random.Random(randomGenerator) > 0.5) then
	   	error := False;
		value := 0.0;
	    else
		error := True;
	        value := 2.0 * threshold.all * Ada.Numerics.Float_Random.Random(randomGenerator);
	    end if;
	    Ada.Text_IO.Put_Line("QUERY RECEIVED");
	    Ada.Text_IO.Put(error'Image);
	    Ada.Text_IO.Put(' ');
	    Ada.Text_IO.Put(value'Image);
	    Ada.Text_IO.New_Line;
	 end;
      end loop;
	end;
	
   task body SensorReaderType is
      nextActivation : Ada.Real_Time.Time;
      nextDeadline : Ada.Real_Time.Time;
      nextDeadlineMissed : Boolean;
      error : Boolean;
      value : Float;
      errorCount : Integer;
   begin
      nextActivation := Ada.Real_Time.Clock + Ada.Real_Time.Milliseconds(period);
      nextDeadline := Ada.Real_Time.Clock + Ada.Real_Time.Milliseconds(deadline);
      nextDeadlineMissed := False;
      errorCount := 0;
      
      loop
	 select
	    delay until nextDeadline;
	    nextDeadlineMissed := True;
	 then abort
	    sensor.query(error, value);
	    if (error = True) then
	       errorCount := errorCount + 1;
	       if (errorCount >= criticalErrorCount) then
		  Ada.Text_IO.Put_Line("ERROR COUNT EXCEEDED");
	       end if;
	    else
	       errorCount := 0;
	       if (((detectAboveThreshold = true) and (value >= threshold.all)) or ((detectAboveThreshold = False) and (value <= threshold.all))) then 
		  Ada.Text_IO.Put_Line("THRESHOLD EXCEEDED");
	       else
		  Ada.Text_IO.Put_Line("STATE NORMAL");
	       end if;
	    end if;
	    nextDeadlineMissed := False;
      	 end select;
      
         if (nextDeadlineMissed = True) then
	    Ada.Text_IO.Put_Line("DEADLINE MISSED");
      	 end if;
      
	 delay until nextActivation;
         nextActivation := nextActivation + Ada.Real_Time.Milliseconds (period);
         nextDeadline := Ada.Real_Time.Clock + Ada.Real_Time.Milliseconds(deadline);
      end loop;	
   end SensorReaderType;
end EnvironmentSensors;
