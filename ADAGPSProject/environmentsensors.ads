package EnvironmentSensors is
   task type SensorType(
		    threshold : access Float
		   ) is
      entry query(error : out Boolean; value : out Float);
   end;

   task type SensorReaderType(
			      period 		   : Integer;
			      deadline		   : Integer;
			      threshold 	   : access Float;
			      detectAboveThreshold : Boolean;
			      criticalErrorCount   : Integer;
			      sensor 		   : access SensorType
			 ) is
   end SensorReaderType;

end EnvironmentSensors;
