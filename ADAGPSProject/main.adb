with EnvironmentSensors; use EnvironmentSensors;

procedure Main is
   mthreshold : access Float := new Float'(100.0);
   msensor : access SensorType := new SensorType(threshold => mthreshold);
   sensorReader :  SensorReaderType(
				    period => 1000,
				    deadline => 1,
				    threshold => mthreshold,
				    detectAboveThreshold => True,
				    criticalErrorCount   => 2,
				    sensor               => msensor
				   );
begin
   null;
end Main;
